package com.bycc.utils;

import com.bycc.domain.IDCard;
import com.bycc.enumItem.Gender;
import com.bycc.enumItem.Nation;

public class IDCardParser {

	public static IDCard parseIdCard(String cardMsg) {
		String[] tmp = cardMsg.split("\\s+");
		IDCard idCard = new IDCard();
		// ��������
		idCard.setName(tmp[0]);

		// �����Ա����塢�������ں�סַ
		Gender gender = Gender.getByCode(tmp[1].substring(0, 1));
		Nation nation = Nation.getByCode(tmp[1].substring(1, 3));
		String birthday = tmp[1].substring(3, 7) + "-" + tmp[1].substring(7, 9) + "-" + tmp[1].substring(9, 11);
		String address = tmp[1].substring(11, tmp[1].length() - 1);
		idCard.setGender(gender);
		idCard.setNation(nation);
		idCard.setBirthday(birthday);
		idCard.setAddress(address);

		// �������֤����
		String id = tmp[2].substring(0, 18);
		String issuer = tmp[2].substring(18, tmp[2].length() - 1);
		idCard.setId(id);
		idCard.setIssuer(issuer);

		// ������Ч��
		String issueDate = tmp[3].substring(0, 8);
		String expireDate = tmp[3].substring(8, 16);
		idCard.setIssueDate(issueDate);
		idCard.setExpireDate(expireDate);
		return idCard;
	}
}
