package LabourPkg;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import javax.swing.JInternalFrame;
import javax.swing.border.TitledBorder;

public class CreateLoginUser extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JTextField textField;
	private JTable table;
	// static JTable table = new JTable();

	// create a table model and set a Column Identifiers to this model

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateLoginUser frame = new CreateLoginUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateLoginUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1082, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DbConnect dbc = new DbConnect();
				ResultSet rs = null;
				try {

					// this will be same for Update and Delete
					// int res = dbc.SqlInsUpdDel("insert into
					// labour_db.loginuser(UserName,UserPassword,isActive) values
					// ('"+txtUserName.getText()+"','"+txtPassword.getText()+"',"+textField.getText()+");");
					rs = dbc.SqlSelect("SELECT shop_name, type_of_contractor, contractor_name, country, mobile_no, email_id, city, address, zipcode, fromdate, todate FROM contractor_details");

					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception ex) {
					// System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, ex);
				}
				finally {
					
				}

				// System.out.println(String.valueOf(res));
			}
		});
		btnSave.setBounds(10, 117, 89, 23);
		contentPane.add(btnSave);

		txtUserName = new JTextField();
		txtUserName.setBounds(86, 11, 114, 20);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);

		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setBounds(10, 14, 66, 14);
		contentPane.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 48, 66, 14);
		contentPane.add(lblPassword);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(86, 45, 114, 20);
		contentPane.add(txtPassword);

		textField = new JTextField();
		textField.setText("1");
		textField.setColumns(10);
		textField.setBounds(86, 73, 114, 20);
		contentPane.add(textField);

		JLabel lblIsactive = new JLabel("isActive");
		lblIsactive.setBounds(10, 76, 66, 14);
		contentPane.add(lblIsactive);
		
		table = new JTable();
		table.setBounds(10, 200, 1046, 354);
		contentPane.add(table);
	}
}
