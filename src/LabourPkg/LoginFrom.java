package LabourPkg;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.util.Objects;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

public class LoginFrom {
	protected static Component frmLoginSystem = null;
	private JFrame frame;
	private JTextField txtUserName;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrom window = new LoginFrom();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginFrom() {
		initialize();
		// try {
		// Step 2: Load MySQL Java driver
		// Class.forName(DRIVER_CLASS);
		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setType(Type.UTILITY);
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setResizable(false);
		frame.setBounds(200, 200, 370, 204);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		JLabel lblNewLabel = new JLabel("Labour Management System");
		lblNewLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		lblNewLabel.setBounds(33, 24, 292, 31);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(42, 80, 80, 14);
		frame.getContentPane().add(lblUserName);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(42, 106, 80, 14);
		frame.getContentPane().add(lblPassword);

		txtUserName = new JTextField();
		txtUserName.setBounds(122, 77, 176, 20);
		frame.getContentPane().add(txtUserName);
		txtUserName.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(122, 103, 176, 20);
		frame.getContentPane().add(txtPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DbConnect dbCon = new DbConnect();
				ResultSet rs = null;
				String password = new String(txtPassword.getPassword());
				try {

					rs = dbCon.SqlSelect("SELECT * FROM loginuser where UserName='" + txtUserName.getText()
							+ "' and UserPassword='" + password + "' and isActive=1"); // SQL Injection can be done .. So not good way to do this
					int count = 0;
					while (rs.next()) {
						count = count + 1;
					}					
					if (count == 1) {

//						JOptionPane.showMessageDialog(null, "Valid User Login ");
						frame.dispose();
						MainFrame fMain = new MainFrame();
						fMain.setVisible(true);

					} else if (count > 1) {

						JOptionPane.showMessageDialog(null, "Duplicate User Cannot Login ");
					} else {

						JOptionPane.showMessageDialog(null, "Invalid Credentials ");
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try {
						if (rs != null)
							rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

			}
		});
		btnLogin.setBounds(33, 146, 89, 23);
		frame.getContentPane().add(btnLogin);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUserName.setText(null);
				txtPassword.setText(null);
			}
		});
		btnReset.setBounds(132, 146, 89, 23);
		frame.getContentPane().add(btnReset);

		JSeparator separator = new JSeparator();
		separator.setBounds(-20, 132, 436, 2);
		frame.getContentPane().add(separator);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLoginSystem = new JFrame("Exit");
				// if(JOptionPane.showConfirmDialog(frmLoginSystem,"Confirm if you want to
				// exit","Login System",JOptionPane.YES_NO_OPTION)) {
				// System.exit(0);

				// }
				if (JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to exit", "Login Systems",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(1);
				}
			}
		});
		btnExit.setBounds(233, 146, 89, 23);
		frame.getContentPane().add(btnExit);
	}
}
