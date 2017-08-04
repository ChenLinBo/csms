package com.bycc.ui;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TrayItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bycc.domain.IDCard;
import com.bycc.net.http.HttpServer;
import com.bycc.service.IdCardService;
import com.bycc.service.IdCardServiceImpl;

public class MainWindow extends Shell {
	private static Logger logger = LoggerFactory.getLogger(MainWindow.class);
	private Text name;
	private Text gender;
	private Text birthday;
	private Text address;
	private Text id;
	private Text issuer;
	private Text issueDate;
	private Text expireDate;
	private Text nation;
	// �̳߳�
	private static ScheduledExecutorService THREAD_POOL = null;

	// ���̶�ȡ��ʱ����
	private static ScheduledFuture<?> TRAY_TASK = null;

	// http��̨�������ڴ�������web������
	private static HttpServer httpServer = null;

	private final static TrayItem trayItem = new TrayItem(Display.getDefault().getSystemTray(), SWT.NONE);

	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			MainWindow shell = new MainWindow(display);
			shell.setImage(display.getSystemImage(SWT.ICON_WORKING));
			shell.open();
			center(shell);
			shell.layout();

			shell.addShellListener(new ShellAdapter() {
				// ��С��ʱ��������
				@Override
				public void shellIconified(ShellEvent e) {
					showMainWindow(shell);
				}

				// ���ڹر�ʱ��С��������
				@Override
				public void shellClosed(ShellEvent e) {
					e.doit = false;
					showMainWindow(shell);
				}
			});
			trayItem.setVisible(true);
			trayItem.setToolTipText(shell.getText());
			// ���������ʾ����
			trayItem.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					showMainWindow(shell);
				}
			});

			// �������̲˵�
			Menu trayMenu = new Menu(shell, SWT.POP_UP);
			// ��ԭ���ڲ˵�
			MenuItem showMenuItem = new MenuItem(trayMenu, SWT.PUSH);
			showMenuItem.setText("��ԭ����(&s)");
			showMenuItem.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					showMainWindow(shell);
				}
			});
			trayMenu.setDefaultItem(showMenuItem);
			new MenuItem(trayMenu, SWT.SEPARATOR);

			// �˳��˵�
			MenuItem exitMenuItem = new MenuItem(trayMenu, SWT.PUSH);
			exitMenuItem.setText("�˳�(&x)");
			exitMenuItem.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					if (TRAY_TASK != null) {
						TRAY_TASK.cancel(true);
						logger.info("ֹͣʶ�����֤");
					}
					if (THREAD_POOL != null) {
						THREAD_POOL.shutdownNow();
						logger.info("�ر��̳߳�");
					}

					if (httpServer != null) {
						httpServer.close();
					}
					shell.dispose();
				}
			});

			// ���̵���Ҽ�ʱ�����˵�
			trayItem.addMenuDetectListener(new MenuDetectListener() {
				public void menuDetected(MenuDetectEvent e) {
					trayMenu.setVisible(true);
				}
			});

			// ��������ͼ��
			trayItem.setImage(shell.getImage());

			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void showMainWindow(MainWindow shell) {
		shell.setVisible(!shell.isVisible());
		if (shell.getVisible()) {
			// ��������ʾ�����ϲ�
			shell.setActive();
		}
	}

	/**
	 * ���ھ�����ʾ
	 */
	private static void center(Shell shell) {
		Monitor monitor = shell.getMonitor();
		Rectangle bounds = monitor.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 */
	public MainWindow(Display display) {
		super(display, SWT.SHELL_TRIM);

		Label lblAa = new Label(this, SWT.NONE);
		lblAa.setBounds(10, 21, 32, 20);
		lblAa.setText("\u59D3\u540D");

		name = new Text(this, SWT.BORDER);
		name.setEditable(false);
		name.setBounds(48, 21, 73, 17);

		Label label = new Label(this, SWT.NONE);
		label.setBounds(10, 47, 32, 17);
		label.setText("\u6027\u522B");

		gender = new Text(this, SWT.BORDER);
		gender.setEditable(false);
		gender.setBounds(48, 47, 73, 17);

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setBounds(10, 98, 32, 17);
		label_1.setText("\u51FA\u751F");

		birthday = new Text(this, SWT.BORDER);
		birthday.setEditable(false);
		birthday.setBounds(48, 98, 73, 17);

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setBounds(10, 121, 32, 17);
		label_2.setText("\u4F4F\u5740");

		address = new Text(this, SWT.BORDER);
		address.setEditable(false);
		address.setBounds(48, 121, 150, 17);

		Label label_3 = new Label(this, SWT.NONE);
		label_3.setBounds(10, 150, 93, 20);
		label_3.setText("\u516C\u6C11\u8EAB\u4EFD\u8BC1\u53F7\u7801");

		id = new Text(this, SWT.BORDER);
		id.setEditable(false);
		id.setBounds(103, 150, 157, 17);

		Label label_4 = new Label(this, SWT.NONE);
		label_4.setBounds(10, 176, 61, 17);
		label_4.setText("\u7B7E\u53D1\u673A\u5173");

		issuer = new Text(this, SWT.BORDER);
		issuer.setEditable(false);
		issuer.setBounds(84, 173, 114, 17);

		Label label_5 = new Label(this, SWT.NONE);
		label_5.setBounds(10, 202, 61, 17);
		label_5.setText("\u6709\u6548\u671F\u9650");

		issueDate = new Text(this, SWT.BORDER);
		issueDate.setEditable(false);
		issueDate.setBounds(66, 202, 73, 17);

		expireDate = new Text(this, SWT.BORDER);
		expireDate.setEditable(false);
		expireDate.setBounds(152, 202, 85, 17);

		Label label_6 = new Label(this, SWT.NONE);
		label_6.setBounds(10, 73, 32, 17);
		label_6.setText("\u6C11\u65CF");

		nation = new Text(this, SWT.BORDER);
		nation.setEditable(false);
		nation.setBounds(48, 72, 73, 17);

		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u8EAB\u4EFD\u8BC1\u8BC6\u8BFB");
		setSize(450, 300);

		// �����̳߳�
		THREAD_POOL = Executors.newScheduledThreadPool(2);
		// ���������̣߳�ÿ500�����һ�ο�
		TRAY_TASK = THREAD_POOL.scheduleWithFixedDelay(createRecognizeIdCardTask(), 1000, 500, TimeUnit.MILLISECONDS);
		// ������̨http�̣߳���������web������
		THREAD_POOL.execute(() -> {
			try {
				httpServer = new HttpServer(8088);
				httpServer.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * Ϊ������������
	 * 
	 * @param idCard
	 */
	private void setValues(IDCard idCard) {
		this.name.setText(idCard.getName() == null ? "" : idCard.getName());
		this.gender.setText(idCard.getGender() == null ? "" : idCard.getGender().getValue());
		this.nation.setText(idCard.getNation() == null ? "" : idCard.getNation().getValue());
		this.birthday.setText(idCard.getBirthday() == null ? "" : idCard.getBirthday());
		this.address.setText(idCard.getAddress() == null ? "" : idCard.getAddress());
		this.id.setText(idCard.getId() == null ? "" : idCard.getId());
		this.issuer.setText(idCard.getIssuer() == null ? "" : idCard.getIssuer());
		this.issueDate.setText(idCard.getIssueDate() == null ? "" : idCard.getIssueDate());
		this.expireDate.setText(idCard.getExpireDate() == null ? "" : idCard.getExpireDate());
	}

	/**
	 * ������������
	 * 
	 * @return
	 */
	private Runnable createRecognizeIdCardTask() {
		return () -> {
			IdCardService service = new IdCardServiceImpl();
			try {
				IDCard idCard = service.read();

				// ���½�������ʱ����ʹ��Display�����и���,�Է�ֹ�����޸��쳣
				Display.getDefault().asyncExec(() -> {
					setValues(idCard);
				});
			} catch (Exception e) {
				Display.getDefault().asyncExec(() -> {
					setValues(new IDCard());
				});
			}
		};
	}
}
