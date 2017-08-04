package com.bycc.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bycc.domain.IDCard;
import com.bycc.utils.IDCardParser;
import com.sun.jna.Library;
import com.sun.jna.Native;

public class IdCardServiceImpl implements IdCardService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	// USB1�˿ں�
	private static final int PORT_USB1 = 1001;

	// �ҿ��ɹ�״̬��
	private static final int FIND_CARD_SUCCESS = 0x9f;

	// �򿪶˿ڳɹ�״̬��
	private static final int OPEN_PORT_SUCCESS = 0x90;

	// �رն˿ڳɹ�״̬��
	private static final int CLOSE_PORT_SUCCESS = 0x90;

	// ѡ���ɹ�״̬��
	private static final int SELECT_CARD_SUCCESS = 0x90;

	// �����ɹ�״̬��
	private static final int READ_CARD_SUCCESS = 0x90;

	// ��Ƭ����ɹ�״̬��
	private static final int PHOTO_DECODE_SUCCESS = 1;

	// ���֤��Ƭ��ʱ����
	private static final String PHOTO_TMP_NAME = "photo.wlt";

	// ���֤��Ƭ����������
	private static final String PHOTO_NAME = "photo.bmp";

	@Override
	public IDCard read() throws Exception {
		// ���ض�̬��
		System.load(this.getClass().getClassLoader().getResource("sdtapi.dll").getPath());
		System.load(this.getClass().getClassLoader().getResource("WltRS.dll").getPath());

		// �򿪶˿�
		int code = SDTAPI.SYNC_INSTANCE.SDT_OpenPort(PORT_USB1);
		if (code != OPEN_PORT_SUCCESS) {
			logger.debug("�򿪶˿�ʧ�ܣ������룺{}", code);
			SDTAPI.SYNC_INSTANCE.SDT_ClosePort(PORT_USB1);
			throw new Exception("�򿪶˿�ʧ�ܣ�������:" + code);
		}
		logger.debug("�򿪶˿ڳɹ�");

		// �ҿ�
		byte[] msg = new byte[8];
		code = SDTAPI.SYNC_INSTANCE.SDT_StartFindIDCard(PORT_USB1, msg, 0);
		if (code != FIND_CARD_SUCCESS) {
			logger.debug("�ҿ�ʧ�ܣ������룺{}", code);
			SDTAPI.SYNC_INSTANCE.SDT_ClosePort(PORT_USB1);
			throw new Exception("�ҿ�ʧ�ܣ�������:" + code);
		}
		logger.debug("�ҿ��ɹ�");

		// ѡ��
		code = SDTAPI.SYNC_INSTANCE.SDT_SelectIDCard(PORT_USB1, new byte[8], 0);
		if (code != SELECT_CARD_SUCCESS) {
			logger.debug("ѡ��ʧ�ܣ������룺{}", code);
			SDTAPI.SYNC_INSTANCE.SDT_ClosePort(PORT_USB1);
			throw new Exception("ѡ��ʧ�ܣ�������:" + code);
		}
		logger.debug("ѡ���ɹ�");

		// ����
		char[] content = new char[256];
		byte[] photoBytes = new byte[1024];
		code = SDTAPI.SYNC_INSTANCE.SDT_ReadBaseMsg(PORT_USB1, content, new int[256], photoBytes, new int[1024], 0);
		if (code != READ_CARD_SUCCESS) {
			logger.debug("����ʧ�ܣ������룺{}", code);
			SDTAPI.SYNC_INSTANCE.SDT_ClosePort(PORT_USB1);
			throw new Exception("����ʧ�ܣ�������:" + code);
		}
		logger.debug("�����ɹ�");

		// �رն˿�
		code = SDTAPI.SYNC_INSTANCE.SDT_ClosePort(PORT_USB1);
		if (code != CLOSE_PORT_SUCCESS) {
			logger.debug("�رն˿�ʧ�ܣ������룺{}", code);
			throw new Exception("�رն˿�ʧ�ܣ�������:" + code);
		}
		logger.debug("�رն˿ڳɹ�");

		// ��ȡ�����Ŀ¼
		String baseDir = System.getProperty("user.dir");
		// ������ʱĿ¼
		String tmpDir = baseDir + "\\tmp";
		if (!Files.exists(new File(tmpDir).toPath())) {
			logger.debug("������ʱĿ¼��{}", tmpDir);
			Files.createDirectory(new File(tmpDir).toPath());
		}

		// ɾ���ɵ���Ƭ��Ϣ
		File tmpPhoto = new File(tmpDir + "\\" + PHOTO_TMP_NAME);
		if (tmpPhoto.exists()) {
			if (!tmpPhoto.delete()) {
				logger.error("�޷�ɾ����Ƭ�ļ�{}", tmpDir + "\\" + PHOTO_TMP_NAME);
			}
		}

		// ������Ƭ��Ϣ
		FileOutputStream fStream = new FileOutputStream(tmpPhoto);
		BufferedOutputStream bos = new BufferedOutputStream(fStream);
		bos.write(photoBytes);
		bos.close();

		// �������֤��Ϣ
		IDCard idCard = IDCardParser.parseIdCard(new String(content));
		logger.debug("����������Ϣ��{}", idCard);

		// ������Ƭ
		code = WltRS.SYNC_INSTANCE.GetBmp(tmpDir + "\\" + PHOTO_TMP_NAME, 2);
		if (code != PHOTO_DECODE_SUCCESS) {
			logger.debug("��Ƭ����ʧ�ܣ������룺{}", code);
			throw new Exception("��Ƭ����ʧ�ܣ�������:" + code);
		}
		logger.debug("��Ƭ����ɹ�");
		File photo = new File(tmpDir + "\\" + PHOTO_NAME);
		logger.debug("base64������Ƭ: {}", Base64.getEncoder().encodeToString(Files.readAllBytes(photo.toPath())));
		idCard.setPhoto(Base64.getEncoder().encodeToString(Files.readAllBytes(photo.toPath())));
		return idCard;
	}

	// ����API
	public interface SDTAPI extends Library {
		// �򿪶˿�
		int SDT_OpenPort(int port);

		// �رն˿�
		int SDT_ClosePort(int port);

		// �ҿ�
		int SDT_StartFindIDCard(int port, byte[] pucManaInfo, int ifOpen);

		// ѡ��
		int SDT_SelectIDCard(int port, byte[] pucManaMsg, int ifOpen);

		// ����
		int SDT_ReadBaseMsg(int port, char[] content, int[] contentLength, byte[] photo, int[] photoLength, int ifOpen);

		SDTAPI INSTANCE = Native.loadLibrary("sdtapi", SDTAPI.class);

		SDTAPI SYNC_INSTANCE = (SDTAPI) Native.synchronizedLibrary(INSTANCE);
	}

	// ���֤��Ƭת��API
	public interface WltRS extends Library {
		// ��Ƭ����
		// ����1��wlt�ļ�·��������2���ӿڣ�1-com�ڣ�2-usb
		int GetBmp(String Wlt_File, int intf);

		WltRS INSTANCE = Native.loadLibrary("WltRS", WltRS.class);

		WltRS SYNC_INSTANCE = (WltRS) Native.synchronizedLibrary(INSTANCE);
	}
}
