package LabourPkg;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTabbedPane;
import javax.swing.JSlider;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;

public class Contractor extends JFrame {

	private JPanel contentPane;
	private JTextField txtContractorName;
	private JTextField txtMobileNumber;
	private JTextField txtEmail;
	private JTextField txtCity;
	private JTextField txtAddress;
	private JTextField txtZip;
	private JTable table;
	public String editValue;
	private JTextField txtIBAN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contractor frame = new Contractor();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public void BindTable() {

		DbConnect dbc = new DbConnect();
		ResultSet rs = null;

		try {
			rs = dbc.SqlSelect(
					"SELECT contractor_no,contractor_name, type_of_contractor, country, mobile_no, email_id, city, address, zipcode, JoinDate, isActive FROM contractor_details;");

			table.setModel(DbUtils.resultSetToTableModel(rs));

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

	/**
	 * Create the frame.
	 */
	public Contractor() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1115, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCreateContractor = new JLabel("Contractor Form");
		lblCreateContractor.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		lblCreateContractor.setBounds(40, 12, 417, 24);
		contentPane.add(lblCreateContractor);

		JTabbedPane jtpContracor = new JTabbedPane(JTabbedPane.TOP);
		jtpContracor.setBounds(18, 48, 1080, 294);
		contentPane.add(jtpContracor);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		jtpContracor.addTab("Form", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblContractorName = new JLabel("Contractor Name");
		lblContractorName.setBounds(26, 6, 123, 29);
		panel_2.add(lblContractorName);
		lblContractorName.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtContractorName = new JTextField();
		txtContractorName.setBounds(159, 12, 284, 19);
		panel_2.add(txtContractorName);
		txtContractorName.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(586, 210, 79, 27);
		panel_2.add(btnSave);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(675, 208, 91, 27);
		panel_2.add(btnUpdate);

		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(26, 97, 85, 16);
		panel_2.add(lblCountry);
		lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblMobileNo = new JLabel("Mobile No.");
		lblMobileNo.setBounds(26, 127, 85, 29);
		panel_2.add(lblMobileNo);
		lblMobileNo.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtMobileNumber = new JTextField();
		txtMobileNumber.setBounds(159, 132, 284, 19);
		panel_2.add(txtMobileNumber);
		txtMobileNumber.setColumns(10);

		JLabel lblEmailId = new JLabel("Email Id");
		lblEmailId.setBounds(26, 154, 96, 33);
		panel_2.add(lblEmailId);
		lblEmailId.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtEmail = new JTextField();
		txtEmail.setBounds(159, 162, 284, 19);
		panel_2.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(26, 62, 85, 24);
		panel_2.add(lblCity);
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtCity = new JTextField();
		txtCity.setBounds(159, 66, 96, 19);
		panel_2.add(txtCity);
		txtCity.setColumns(10);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(26, 37, 54, 20);
		panel_2.add(lblAddress);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtAddress = new JTextField();
		txtAddress.setBounds(159, 38, 284, 19);
		panel_2.add(txtAddress);
		txtAddress.setColumns(10);

		JLabel lblZipCode = new JLabel("Zip Code");
		lblZipCode.setBounds(260, 65, 69, 19);
		panel_2.add(lblZipCode);
		lblZipCode.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtZip = new JTextField();
		txtZip.setBounds(323, 65, 123, 19);
		panel_2.add(txtZip);
		txtZip.setColumns(10);

		JComboBox cboCountry = new JComboBox();
		cboCountry.setBounds(159, 93, 284, 24);
		panel_2.add(cboCountry);
		cboCountry.setModel(new DefaultComboBoxModel(new String[] { "Afghanistan", "Albania", "Algeria", "Andorra",
				"Angola", "Anguilla", "Antigua & Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan",
				"Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda",
				"Bhutan", "Bolivia", "Bosnia & Herzegovina", "Botswana", "Brazil", "Brunei Darussalam", "Bulgaria",
				"Burkina Faso", "Myanmar/Burma", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
				"Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo",
				"Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Democratic Republic of the Congo",
				"Denmark", "Djibouti", "Dominican Republic", "Dominica", "Ecuador", "Egypt", "El Salvador",
				"Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "French Guiana",
				"Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Great Britain", "Greece", "Grenada", "Guadeloupe",
				"Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India",
				"Indonesia", "Iran", "Iraq", "Israel and the Occupied Territories", "Italy",
				"Ivory Coast (Cote d'Ivoire)", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kosovo", "Kuwait",
				"Kyrgyz Republic (Kyrgyzstan)", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya",
				"Liechtenstein", "Lithuania", "Luxembourg", "Republic of Macedonia", "Madagascar", "Malawi", "Malaysia",
				"Maldives", "Mali", "Malta", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico",
				"Moldova, Republic of", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique",
				"Namibia", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria",
				"Korea, Democratic Republic of (North Korea)", "Norway", "Oman", "Pacific Islands", "Pakistan",
				"Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Puerto Rico",
				"Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia",
				"Saint Vincent's & Grenadines", "Samoa", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia",
				"Seychelles", "Sierra Leone", "Singapore", "Slovak Republic (Slovakia)", "Slovenia", "Solomon Islands",
				"Somalia", "South Africa", "Korea, Republic of (South Korea)", "South Sudan", "Spain", "Sri Lanka",
				"Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Tajikistan", "Tanzania",
				"Thailand", "Timor Leste", "Togo", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan",
				"Turks & Caicos Islands", "Uganda", "Ukraine", "United Arab Emirates", "United States of America (USA)",
				"Uruguay", "Uzbekistan", "Venezuela", "Vietnam", "Virgin Islands (UK)", "Virgin Islands (US)", "Yemen",
				"Zambia", "Zimbabwe" }));

		JDateChooser dtJoinDate = new JDateChooser();
		dtJoinDate.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		dtJoinDate.setDateFormatString("yyyy-MM-dd");
		dtJoinDate.setBounds(552, 6, 151, 29);
		panel_2.add(dtJoinDate);

		JLabel lblNewLabel = new JLabel("Join Date");
		lblNewLabel.setBounds(468, 12, 55, 16);
		panel_2.add(lblNewLabel);

		JCheckBox chkActive = new JCheckBox("Is Active");
		chkActive.setSelected(true);
		chkActive.setBounds(552, 102, 123, 33);
		panel_2.add(chkActive);

		JComboBox cboType = new JComboBox();
		cboType.setModel(new DefaultComboBoxModel(
				new String[] { "Labour < 10", "Labour > 10 < 50", "Labour > 50 <150", "Labour > 200" }));
		cboType.setBounds(552, 37, 151, 26);
		panel_2.add(cboType);

		JLabel lblType = new JLabel("Type");
		lblType.setBounds(468, 42, 55, 16);
		panel_2.add(lblType);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		jtpContracor.addTab("Data", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 1068, 252);
		panel.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		Date date = new Date();
		dtJoinDate.setDate(date);
		btnUpdate.setEnabled(false);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DbConnect dbc = new DbConnect();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				int result = 0;
				try {

					if (Objects.equals(txtContractorName.getText(), "") || Objects.equals(txtEmail.getText(), "")
							|| Objects.equals(txtMobileNumber.getText(), "")) {
						JOptionPane.showMessageDialog(null,
								"Contactor Name or Email ID or Mobile Number is missing. You cannot save without those");

					} else {
						// System.out.println(

						result = dbc.SqlInsUpdDel("delete from contractor_details  where contractor_no = " + editValue);

						
						if (result != 100) {
							JOptionPane.showMessageDialog(null, "Contractor Deleted Successfully");
							btnUpdate.setEnabled(false);
							btnSave.setEnabled(true);
							jtpContracor.setSelectedIndex(1);
							txtContractorName.setText("");
							txtAddress.setText("");
							txtCity.setText("");
							txtEmail.setText("");
							txtMobileNumber.setText("");
							txtZip.setText("");
							txtIBAN.setText("");
							BindTable();
						}else {
							JOptionPane.showMessageDialog(null, "Cannot Delete Assigned Contractor. Please Inactive it and update");
							
						}
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR : Saving contractor - " + ex.getMessage().toString());

				}
				
			}
		});
		btnDelete.setBounds(778, 207, 90, 28);
		panel_2.add(btnDelete);
		btnDelete.setEnabled(false);
		
		JButton btnNewContractor = new JButton("Add New");
		btnNewContractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnUpdate.setEnabled(false);
				btnSave.setEnabled(true);
				btnDelete.setEnabled(false);
				// new Contractor
				txtContractorName.setText("");
				txtAddress.setText("");
				txtCity.setText("");
				txtEmail.setText("");
				txtMobileNumber.setText("");
				txtZip.setText("");
				txtIBAN.setText("");
				BindTable();
			}

		});
		btnNewContractor.setBounds(485, 210, 91, 28);
		panel_2.add(btnNewContractor);

		txtIBAN = new JTextField();
		txtIBAN.setBounds(552, 75, 151, 20);
		panel_2.add(txtIBAN);
		txtIBAN.setColumns(10);

		JLabel lblIban = new JLabel("IBAN");
		lblIban.setBounds(468, 77, 55, 16);
		panel_2.add(lblIban);
	
		
		
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.GRAY));
		table.setFillsViewportHeight(true);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DbConnect dbc = new DbConnect();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				int result = 0;
				try {

					if (Objects.equals(txtContractorName.getText(), "") || Objects.equals(txtEmail.getText(), "")
							|| Objects.equals(txtMobileNumber.getText(), "")) {
						JOptionPane.showMessageDialog(null,
								"Contactor Name or Email ID or Mobile Number is missing. You cannot save without those");

					} else {
						// System.out.println(

						result = dbc.SqlInsUpdDel("update contractor_details set contractor_name ='"
								+ txtContractorName.getText() + "',type_of_contractor = '"
								+ cboType.getSelectedItem().toString() + "',country = '"
								+ cboCountry.getSelectedItem().toString() + "',mobile_no = '"
								+ txtMobileNumber.getText() + "',email_id = '" + txtEmail.getText() + "',city = '"
								+ txtCity.getText() + "',address = '" + txtAddress.getText() + "',zipcode = '"
								+ txtZip.getText() + "', JoinDate ='" + df.format(dtJoinDate.getDate())
								+ "', isActive = " + (chkActive.isSelected() == true ? 1 : 0) + " ,IBAN='"
								+ txtIBAN.getText() + "' where contractor_no = " + editValue);

						JOptionPane.showMessageDialog(null, "Contractor Updated Successfully");
						if (result != 100) {
							btnUpdate.setEnabled(false);
							btnSave.setEnabled(true);
							jtpContracor.setSelectedIndex(1);
							txtContractorName.setText("");
							txtAddress.setText("");
							txtCity.setText("");
							txtEmail.setText("");
							txtMobileNumber.setText("");
							txtZip.setText("");
							txtIBAN.setText("");
							BindTable();
						}
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR : Saving contractor - " + ex.getMessage().toString());

				}
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DbConnect dbc = new DbConnect();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				int result = 0;
				try {

					if (Objects.equals(txtContractorName.getText(), "") || Objects.equals(txtEmail.getText(), "")
							|| Objects.equals(txtMobileNumber.getText(), "")) {
						JOptionPane.showMessageDialog(null,
								"Contractor Name or Email ID or Mobile Number is missing. You cannot save without those");

					} else {
						// System.out.println(
						result = dbc.SqlInsUpdDel(
								"insert into contractor_details (contractor_name, type_of_contractor, country, mobile_no, email_id, city, address, zipcode, JoinDate, isActive,IBAN) values ('"
										+ txtContractorName.getText() + "', '" + cboType.getSelectedItem().toString()
										+ "', '" + cboCountry.getSelectedItem().toString() + "', '"
										+ txtMobileNumber.getText() + "', '" + txtEmail.getText() + "', '"
										+ txtCity.getText() + "', '" + txtAddress.getText() + "', '" + txtZip.getText()
										+ "', '" + df.format(dtJoinDate.getDate()) + "', "
										+ (chkActive.isSelected() == true ? 1 : 0) + ",'" + txtIBAN.getText().trim()
										+ "') ");

						JOptionPane.showMessageDialog(null, "Contractor Saved Successfully");

						if(result!=100) {
						txtContractorName.setText("");
						txtAddress.setText("");
						txtCity.setText("");
						txtEmail.setText("");
						txtMobileNumber.setText("");
						txtZip.setText("");
						txtIBAN.setText("");
						BindTable();
						}
						
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR : Saving contractor - " + ex.getMessage().toString());

				}

			}
		});
		BindTable();
		ListSelectionModel model = table.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				try {
					DbConnect dbc = new DbConnect();
					ResultSet rs = null;
					editValue = "";
					if (!model.isSelectionEmpty()) {
						int selectedRow = model.getMinSelectionIndex();

						int column = 0;
						int row = table.getSelectedRow();
						editValue = table.getModel().getValueAt(row, column).toString();
						jtpContracor.setSelectedIndex(0);
						btnSave.setEnabled(false);
						btnUpdate.setEnabled(true);
						btnDelete.setEnabled(true);

						rs = dbc.SqlSelect("select * from contractor_details where contractor_no = " + editValue);
						while (rs.next()) {
						
							txtContractorName.setText(rs.getString("contractor_name"));
							txtAddress.setText(rs.getString("address"));
							cboCountry.setSelectedItem(rs.getString("country"));
							txtCity.setText(rs.getString("city"));
							txtEmail.setText(rs.getString("email_id"));
							txtMobileNumber.setText(rs.getString("mobile_no"));
							txtZip.setText(rs.getString("zipcode"));
							txtIBAN.setText(rs.getString("IBAN"));
							boolean act = false;
							if (Objects.equals(rs.getString("IsActive"), "1")) {
								act = true;

							}
							chkActive.setSelected(act);
							cboType.setSelectedItem(rs.getString("type_of_contractor"));

							Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("JoinDate"));
							dtJoinDate.setDate(dt);

						}

					}
				} catch (Exception ex) {
				}

			}
		});

	}
}
