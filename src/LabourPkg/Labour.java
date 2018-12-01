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
import java.awt.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.mysql.fabric.xmlrpc.base.Array;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Labour extends JFrame {

	private JPanel contentPane;
	private JTextField txtLabourName;
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
					Labour frame = new Labour();
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
					"select labour_id, (select b.contractor_name from contractor_details b where b.contractor_no=a.contractor_no) as Contractor, labour_name, address, city, country, mobile_no, email_id, zipcode, Education, joinDate, relivingDate, HourlyPay, isActive FROM labour_details a;");

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
	public Labour() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1115, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCreateContractor = new JLabel("Labour Form");
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

		JLabel lblContractorName = new JLabel("Labour Name");
		lblContractorName.setBounds(26, 6, 123, 29);
		panel_2.add(lblContractorName);
		lblContractorName.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtLabourName = new JTextField();
		txtLabourName.setBounds(159, 12, 284, 19);
		panel_2.add(txtLabourName);
		txtLabourName.setColumns(10);

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
		dtJoinDate.setBounds(159, 193, 151, 29);
		panel_2.add(dtJoinDate);

		JLabel lblNewLabel = new JLabel("Join Date");
		lblNewLabel.setBounds(26, 196, 55, 16);
		panel_2.add(lblNewLabel);

		JCheckBox chkActive = new JCheckBox("Is Active");
		chkActive.setSelected(true);
		chkActive.setBounds(323, 203, 123, 19);
		panel_2.add(chkActive);

		JComboBox cboEducation = new JComboBox();
		cboEducation.setModel(new DefaultComboBoxModel(
				new String[] { "No formal education", "Primary education", "Secondary education or high school",
						"Vocational qualification", "Bachelor's degree", "Master's degree", "Doctorate or higher" }));
		cboEducation.setBounds(550, 7, 151, 26);
		panel_2.add(cboEducation);

		JLabel lblType = new JLabel("Education");
		lblType.setBounds(466, 11, 79, 16);
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
		Date redate;

		btnUpdate.setEnabled(false);

		JButton btnNewContractor = new JButton("Add New");
		btnNewContractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnUpdate.setEnabled(false);
				btnSave.setEnabled(true);
				// new Contractor
				txtLabourName.setText("");
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

		JComboBox cboContractor = new JComboBox();
		cboContractor.setBounds(550, 45, 151, 26);
		panel_2.add(cboContractor);

		DbConnect dbc = new DbConnect();
		ResultSet rs = null;

		try {
			rs = dbc.SqlSelect(
					"select concat(contractor_name , ' , ' , contractor_no) as contractor_name  from contractor_details order by contractor_name");
			ArrayList<String> groupNames = new ArrayList<String>();
			while (rs.next()) {
				String contractor_name = rs.getString("contractor_name");
				// add group names to the array list
				groupNames.add(contractor_name);
			}
			rs.close();

			// Populate the combo box
			DefaultComboBoxModel model = new DefaultComboBoxModel(groupNames.toArray());
			// cboContractor.addItem("NA");
			model.insertElementAt("NA,5", 0);
			cboContractor.setModel(model);
			cboContractor.setSelectedIndex(0);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fetching Contractor error : " + ex.getMessage().toString());
		} finally {
			try {

				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		JLabel lblContractor = new JLabel("Contractor");
		lblContractor.setBounds(461, 50, 79, 16);
		panel_2.add(lblContractor);

		JLabel lblRelevingDate = new JLabel("Releving Date");
		lblRelevingDate.setBounds(460, 127, 85, 16);
		panel_2.add(lblRelevingDate);

		JDateChooser dtRelevingDate = new JDateChooser();
		dtRelevingDate.setDateFormatString("yyyy-MM-dd");
		dtRelevingDate.setBounds(550, 122, 151, 29);
		panel_2.add(dtRelevingDate);

		try {
			redate = new SimpleDateFormat("yyyy-MM-dd").parse("2099-01-01");
			dtRelevingDate.setDate(redate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JLabel lblPayHr = new JLabel("Pay / hr (EUR)");
		lblPayHr.setBounds(466, 84, 79, 16);
		panel_2.add(lblPayHr);

		JSpinner txtHourSpin = new JSpinner();
		txtHourSpin.setModel(new SpinnerNumberModel(8, 8, 500, 1));
		txtHourSpin.setBounds(550, 78, 69, 28);
		panel_2.add(txtHourSpin);
		
		txtIBAN = new JTextField();
		txtIBAN.setBounds(550, 160, 151, 20);
		panel_2.add(txtIBAN);
		txtIBAN.setColumns(10);
		
		JLabel lblIban = new JLabel("IBAN");
		lblIban.setBounds(461, 162, 55, 16);
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

					if (Objects.equals(txtLabourName.getText(), "") || Objects.equals(txtEmail.getText(), "")
							|| Objects.equals(txtMobileNumber.getText(), "")) {
						JOptionPane.showMessageDialog(null,
								"Labour Name or Email ID or Mobile Number is missing. You cannot save without those");

					} else {
						java.util.List<String> contractor_no = Arrays
								.asList(cboContractor.getSelectedItem().toString().split(","));
						// System.out.println(
						// , , , , , , , , Education, , relivingDate, HourlyPay,
						result = dbc.SqlInsUpdDel("update labour_details set contractor_no =" + contractor_no.get(1)
								+ ",labour_name = '" + txtLabourName.getText() + "',country = '"
								+ cboCountry.getSelectedItem().toString() + "',mobile_no = '"
								+ txtMobileNumber.getText() + "',email_id = '" + txtEmail.getText() + "',city = '"
								+ txtCity.getText() + "',address = '" + txtAddress.getText() + "',zipcode = '"
								+ txtZip.getText() + "', JoinDate ='" + df.format(dtJoinDate.getDate())
								+ "', isActive = " + (chkActive.isSelected() == true ? 1 : 0) + ",Education='"
								+ cboEducation.getSelectedItem().toString() + "',relivingDate='"
								+ df.format(dtRelevingDate.getDate()) + "',HourlyPay='" + txtHourSpin.getValue()
								+ "', IBAN='"+txtIBAN.getText()+"' where labour_id = " + editValue);

						JOptionPane.showMessageDialog(null, "Contractor Updated Successfully");
						btnUpdate.setEnabled(false);
						btnSave.setEnabled(true);
						jtpContracor.setSelectedIndex(1);
						txtLabourName.setText("");
						txtAddress.setText("");
						txtCity.setText("");
						txtEmail.setText("");
						txtMobileNumber.setText("");
						txtZip.setText("");
						txtIBAN.setText("");
						BindTable();
						MainFrame mf = new MainFrame();
						mf.setVisible(false);
						mf.setVisible(true);
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR : Updating Labour - " + ex.getMessage().toString());

				}
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DbConnect dbc = new DbConnect();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				int result = 0;
				try {

					if (Objects.equals(txtLabourName.getText(), "") || Objects.equals(txtEmail.getText(), "")
							|| Objects.equals(txtMobileNumber.getText(), "")) {
						JOptionPane.showMessageDialog(null,
								"Contractor Name or Email ID or Mobile Number is missing. You cannot save without those");

					} else {
						java.util.List<String> contractor_no = Arrays
								.asList(cboContractor.getSelectedItem().toString().split(","));

						// System.out.println(
						// df.format(dtJoinDate.getDate()) + "', " + (chkActive.isSelected() == true ? 1
						// : 0) + ") ");
						result = dbc.SqlInsUpdDel(

								"insert into labour_details (contractor_no, labour_name, address, city, country, mobile_no, email_id, zipcode, Education, joinDate, relivingDate, HourlyPay, isActive,IBAN) "
										+ "values (" + contractor_no.get(1) + ",'" + txtLabourName.getText() + "', '"
										+ txtAddress.getText() + "','" + txtCity.getText() + "', '"
										+ cboCountry.getSelectedItem().toString() + "', '" + txtMobileNumber.getText()
										+ "', '" + txtEmail.getText() + "', '" + txtZip.getText() + "', '"
										+ cboEducation.getSelectedItem().toString() + "', '"
										+ df.format(dtJoinDate.getDate()) + "', '" + df.format(dtRelevingDate.getDate())
										+ "', '" + txtHourSpin.getValue() + "',"
										+ (chkActive.isSelected() == true ? 1 : 0) + ",IBAN='"+txtIBAN.getText()+"' )");

						JOptionPane.showMessageDialog(null, "Labour Saved Successfully -" );

						if(result!=100 ) {
						txtLabourName.setText("");
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
					JOptionPane.showMessageDialog(null, "ERROR : Saving Labour - " + ex.getMessage().toString());

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

						rs = dbc.SqlSelect("select * from labour_details where labour_id = " + editValue);
						while (rs.next()) {
							// contractor_no, type_of_contractor, contractor_name, country, mobile_no,
							// email_id, city, address, zipcode, JoinDate, IsActive
							txtLabourName.setText(rs.getString("labour_name"));
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
							cboEducation.setSelectedItem(rs.getString("Education"));

							Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("JoinDate"));
							dtJoinDate.setDate(dt);
							Date dt1 = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("relivingDate"));
							dtRelevingDate.setDate(dt1);
							txtHourSpin.setValue(rs.getString("HourlyPay"));

						}

					}
				} catch (Exception ex) {
				}

			}
		});

	}
}
