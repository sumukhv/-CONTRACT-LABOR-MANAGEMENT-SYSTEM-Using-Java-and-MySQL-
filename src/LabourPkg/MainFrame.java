package LabourPkg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField txtAttEmpID;
	private JTable table_2;
	private JTextField txtIBANLabour;
	private JTextField txtIBANCont;
	private JTextField txtAmountLabour;
	private JTextField txtAmountContractor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void buildTable() {
		DbConnect dbc = new DbConnect();

		ResultSet rsTask = null;
		ResultSet rsAssigned = null;
		ResultSet rsAttendence = null;

		try {
			rsTask = dbc.SqlSelect(
					"select task_id, task_name, task_description, start_date, end_date, RequiredLabours FROM task_details where isActive =1 ");
			rsAssigned = dbc
					.SqlSelect("select t.task_name, t.requiredlabours,l.labour_name, l.HourlyPay from assigntask a "
							+ " inner join  labour_details l on l.labour_id = a.labour_id "
							+ " inner join  task_details t on t.task_id = a.task_id");
			rsAttendence = dbc.SqlSelect(
					"select lbd.labour_name,lba.att_datetime,lbd.HourlyPay from lab_attendance lba  inner join labour_details lbd  on lba.labour_id = lbd.labour_id where isActive = 1");

			table.setModel(DbUtils.resultSetToTableModel(rsTask));
			table_1.setModel(DbUtils.resultSetToTableModel(rsAssigned));
			table_2.setModel(DbUtils.resultSetToTableModel(rsAttendence));

		} catch (Exception ex) {
			System.out.println(ex.getMessage().toString());
		} finally {

		}

	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1410, 864);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);

		DbConnect dbc = new DbConnect();

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1384, 32);
		panel.add(menuBar);

		JMenu mnNewMenu = new JMenu("Contractor");
		menuBar.add(mnNewMenu);

		JButton btnContractor = new JButton("Add / Edit / Update");
		mnNewMenu.add(btnContractor);
		btnContractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Contractor cont = new Contractor();
				cont.setVisible(true);
			}
		});

		JMenu mnLabour = new JMenu("Labour");
		menuBar.add(mnLabour);

		JButton btnOpenLabour = new JButton("Add / Edit / Update");
		btnOpenLabour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Labour lab = new Labour();
				lab.setVisible(true);
			}
		});
		mnLabour.add(btnOpenLabour);

		JMenu mnTask = new JMenu("Task");
		menuBar.add(mnTask);

		JButton brnTask = new JButton("Add / Edit / Update");
		brnTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Task tsk = new Task();
				tsk.setVisible(true);
			}
		});
		mnTask.add(brnTask);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(25, 171, 1334, 574);
		panel.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Active Task", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(19, 67, 1298, 406);
		panel_1.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		JLabel lblActiveTasks = new JLabel("Active Tasks");
		lblActiveTasks.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		lblActiveTasks.setBounds(600, 28, 113, 26);
		panel_1.add(lblActiveTasks);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Assign Task", null, panel_2, null);
		panel_2.setLayout(null);

		JComboBox cboTaskList = new JComboBox();
		cboTaskList.setBounds(201, 41, 367, 26);
		panel_2.add(cboTaskList);

		JLabel lblTaskList = new JLabel("Task List");
		lblTaskList.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTaskList.setBounds(211, 6, 141, 26);
		panel_2.add(lblTaskList);

		JLabel lblAssignedTo = new JLabel("--- Assigned To ---> ");
		lblAssignedTo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblAssignedTo.setBounds(580, 37, 226, 31);
		panel_2.add(lblAssignedTo);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Attendence", null, panel_3, null);
		panel_3.setLayout(null);

		JComboBox cboLabour = new JComboBox();
		cboLabour.setBounds(746, 41, 351, 26);
		panel_2.add(cboLabour);
		JComboBox cboAttLabourList = new JComboBox();
		cboAttLabourList.setBounds(190, 59, 309, 26);
		panel_3.add(cboAttLabourList);
		JLabel lblLabourList = new JLabel("Labour List");
		lblLabourList.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblLabourList.setBounds(1004, 6, 93, 26);
		panel_2.add(lblLabourList);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(26, 117, 1288, 430);
		panel_2.add(scrollPane_1);
		/// Combobox for Assignment
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		tabbedPane.addTab("Payout", null, panel_5, null);
		panel_5.setLayout(null);

		JComboBox cboPaylabour = new JComboBox();
		cboPaylabour.setBounds(58, 78, 331, 26);
		panel_5.add(cboPaylabour);

		JComboBox cboPayContractor = new JComboBox();
		cboPayContractor.setBounds(728, 78, 304, 26);
		panel_5.add(cboPayContractor);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(38, 136, 1258, 335);
		panel_3.add(scrollPane_2);

		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);

		// ResultSet rs = null;
		ResultSet rsAttLabour = null;
		ResultSet rsAtask = null;
		ResultSet rsALabour = null;
		ResultSet rsPayLabour = null;
		ResultSet rsPayContractor = null;
		tabbedPane.setSelectedIndex(1);
		try {
			rsAtask = dbc.SqlSelect(
					"select concat(task_name , ' , ' , task_id) as task_name  from task_details b where isActive=1 and task_id = (select count(task_id) from assigntask) < RequiredLabours ");
			rsALabour = dbc.SqlSelect(
					"select concat(labour_name , ' , ' , labour_id) as labour_name  from labour_details where isActive=1 and labour_id not in(select labour_id from assigntask)  order by labour_name");
			rsAttLabour = dbc.SqlSelect(
					"select concat(labour_name , ' , ' , labour_id) as labour_name  from labour_details where isActive=1");
			rsPayLabour = dbc.SqlSelect(
					"select concat(labour_name , ' , ' , labour_id) as labour_name  from labour_details where isActive=1 and contractor_no=5");
			rsPayContractor = dbc.SqlSelect(
					"SELECT concat( contractor_name , ',' ,contractor_no, ',L:', COALESCE(x.CountVal,0) )  as Contractor"
							+ " FROM contractor_details LEFT OUTER JOIN (SELECT labour_id, count(contractor_no) as CountVal,contractor_no cnt FROM labour_details GROUP BY contractor_no) x ON contractor_details.contractor_no = x.cnt "
							+ " where contractor_no <> 5 and isActive=1	ORDER BY contractor_no DESC ");
			ArrayList<String> groupNamesT = new ArrayList<String>();
			ArrayList<String> groupNamesL = new ArrayList<String>();
			ArrayList<String> groupNamesAtt = new ArrayList<String>();

			ArrayList<String> groupNamesPayL = new ArrayList<String>();
			ArrayList<String> groupNamesPayC = new ArrayList<String>();
			while (rsAtask.next()) {
				String task_name = rsAtask.getString("task_name");
				// add group names to the array list
				groupNamesT.add(task_name);
			}
			rsAtask.close();
			while (rsALabour.next()) {
				String labour_name = rsALabour.getString("labour_name");
				// add group names to the array list
				groupNamesL.add(labour_name);
			}
			rsALabour.close();
			while (rsAttLabour.next()) {
				String labour_name2 = rsAttLabour.getString("labour_name");
				// add group names to the array list
				groupNamesAtt.add(labour_name2);
			}
			rsAttLabour.close();

			while (rsPayLabour.next()) {
				String labour_name1 = rsPayLabour.getString("labour_name");
				// add group names to the array list
				groupNamesPayL.add(labour_name1);
			}
			rsPayLabour.close();

			while (rsPayContractor.next()) {
				String Contractor2 = rsPayContractor.getString("Contractor");
				// add group names to the array list
				groupNamesPayC.add(Contractor2);
			}
			rsPayContractor.close();

			// Populate the combo box
			DefaultComboBoxModel model1 = new DefaultComboBoxModel(groupNamesT.toArray());
			cboTaskList.setModel(model1);
			DefaultComboBoxModel model2 = new DefaultComboBoxModel(groupNamesL.toArray());
			cboLabour.setModel(model2);
			DefaultComboBoxModel model3 = new DefaultComboBoxModel(groupNamesAtt.toArray());
			cboAttLabourList.setModel(model3);

			DefaultComboBoxModel model4 = new DefaultComboBoxModel(groupNamesPayL.toArray());
			cboPaylabour.setModel(model4);
			DefaultComboBoxModel model5 = new DefaultComboBoxModel(groupNamesPayC.toArray());
			cboPayContractor.setModel(model5);
			// cboContractor.setSelectedIndex(0);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fetching Contractor error : " + ex.getMessage().toString());
		}
		JButton btnAssignTask = new JButton("Assign Task");
		btnAssignTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DbConnect dbc = new DbConnect();
				ResultSet rs = null;
				int taskcount = 0;
				int requiredLabours = 0;
				try {
					java.util.List<String> valTask = Arrays.asList(cboTaskList.getSelectedItem().toString().split(","));
					java.util.List<String> valLabour = Arrays.asList(cboLabour.getSelectedItem().toString().split(","));

					rs = dbc.SqlSelect(
							"select (select count(AssignTask_ID) from  assigntask where task_id =" + valTask.get(1)
									+ ") as taskcount , (select RequiredLabours from task_details where task_id="
									+ valTask.get(1) + ") as RequredLabours");

					while (rs.next()) {
						taskcount = Integer.parseInt(rs.getString("taskcount"));
						requiredLabours = Integer.parseInt(rs.getString("RequredLabours"));
					}

					if (taskcount < requiredLabours) {
						int res = dbc.SqlInsUpdDel("insert into assigntask (task_id,labour_id) values ("
								+ valTask.get(1) + "," + valLabour.get(1) + ")");
						if (res != 100) {
							JOptionPane.showMessageDialog(null, "Task is Assigned");

						}
						buildTable();
					} else {
						JOptionPane.showMessageDialog(null, "Task is full no more labours can be assigned");

					}
					MainFrame mf = new MainFrame();
					mf.dispose();
					mf.setVisible(true);

				} catch (Exception ex) {

				}

			}
		});
		btnAssignTask.setBounds(590, 80, 120, 28);
		panel_2.add(btnAssignTask);
		buildTable();

		JLabel lblLabourList_1 = new JLabel("Labour list");
		lblLabourList_1.setBounds(200, 35, 89, 16);
		panel_3.add(lblLabourList_1);

		txtAttEmpID = new JTextField();
		txtAttEmpID.setBounds(700, 57, 109, 28);
		panel_3.add(txtAttEmpID);
		txtAttEmpID.setColumns(10);

		JLabel lblEmpid = new JLabel("RFID / Labour / Employee ID");
		lblEmpid.setBounds(700, 35, 119, 16);
		panel_3.add(lblEmpid);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.BLACK);
		separator.setBounds(637, 24, 43, 100);
		panel_3.add(separator);

		JButton btnAttTxtID = new JButton("Attendance");
		btnAttTxtID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DbConnect dbc = new DbConnect();
				try {
					// java.util.List<String> valAttLab =
					// Arrays.asList(cboAttLabourList.getSelectedItem().toString().split(","));
					if (Objects.equals(txtAttEmpID.getText(), "")) {
						JOptionPane.showMessageDialog(null, "Please provide Employee Id or RFID number");
					} else {
						dbc.SqlInsUpdDel(
								"insert into lab_attendance (labour_id) value (" + txtAttEmpID.getText() + ")");
						JOptionPane.showMessageDialog(null, "Saved Successfully - ");
						buildTable();
					}
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "ERROR in Saving Attendance - " + e.getMessage());
				}
			}
		});
		btnAttTxtID.setBounds(842, 58, 90, 28);
		panel_3.add(btnAttTxtID);

		JButton btnAttCbo = new JButton("Attendance");
		btnAttCbo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DbConnect dbc = new DbConnect();
				try {
					java.util.List<String> valAttLab = Arrays
							.asList(cboAttLabourList.getSelectedItem().toString().split(","));

					dbc.SqlInsUpdDel("insert into lab_attendance (labour_id) value (" + valAttLab.get(1) + ")");
					JOptionPane.showMessageDialog(null, "Saved Successfully - ");
					buildTable();
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "ERROR in Saving Attendance - " + e.getMessage());
				}
			}
		});
		btnAttCbo.setBounds(510, 57, 90, 28);
		panel_3.add(btnAttCbo);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		tabbedPane.addTab("Reports", null, panel_4, null);
		panel_4.setLayout(null);

		JButton btnReport = new JButton("Contractor Details");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection connection = null;
				ResultSet rs = null;

				try {
					DbConnect dbc = new DbConnect();
					rs = dbc.SqlSelect(
							"SELECT contractor_name, mobile_no,email_id, JoinDate FROM contractor_details where contractor_name<>'NA'");

				} catch (Exception e) {
					e.printStackTrace();
					return;
				}

				JasperReportBuilder report = DynamicReports.report();// a new report

				JRResultSetDataSource resultsetdatasource = new JRResultSetDataSource(rs);
				StyleBuilder textStyle = stl.style(Templates.columnStyle).setBorder(stl.pen1Point());
				report.setTemplate(Templates.reportTemplate)

						.setColumnStyle(textStyle)

						.columnGrid(ListType.HORIZONTAL_FLOW).columns(
								// Columns.column("contractor_no", "contractor_no", DataTypes.integerType()),
								Columns.column("Contractor Name", "contractor_name", DataTypes.stringType()),
								Columns.column("Contact No", "mobile_no", DataTypes.stringType()),
								Columns.column("Email Id", "email_id", DataTypes.stringType()),
								Columns.column("JoinDate", "JoinDate", DataTypes.dateType()))

						.title(Templates.createTitleComponent("Contractor Details"))

						.pageFooter(Templates.footerComponent)
						// show page number on the page footer
						.setDataSource(resultsetdatasource);
				// "SELECT contractor_name, country, mobile_no, email_id, city, address,
				// zipcode, JoinDate FROM contractor_details",
				System.out.println("c"); // connection);

				try {
					// show the report
					report.show();

					report.toPdf(new FileOutputStream("D:/ContractorDetail.pdf"));
				} catch (Exception ex) {

					ex.printStackTrace();
				}
			}
		});
		btnReport.setBounds(39, 25, 168, 28);
		panel_4.add(btnReport);

		JButton btnLabourTasks = new JButton("Labour & Tasks");
		btnLabourTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				Connection connection = null;
				ResultSet rs = null;

				try {
					DbConnect dbc = new DbConnect();
					rs = dbc.SqlSelect(
							"select task_name,labour_name,start_date,end_date,assigned_Date  from task_details b "
									+ "				left JOIN assigntask a ON b.task_id = a.task_id "
									+ "						left JOIN labour_details l ON l.labour_id= a.labour_id");

				} catch (Exception ex) {
					ex.printStackTrace();
					return;
				}

				JasperReportBuilder report = DynamicReports.report();// a new report

				JRResultSetDataSource resultsetdatasource = new JRResultSetDataSource(rs);
				StyleBuilder textStyle = stl.style(Templates.columnStyle).setBorder(stl.pen1Point());
				report.setTemplate(Templates.reportTemplate)

						.setColumnStyle(textStyle)

						.columnGrid(ListType.HORIZONTAL_FLOW).columns(
								// Columns.column("contractor_no", "contractor_no", DataTypes.integerType()),
								Columns.column("Contractor Name", "task_name", DataTypes.stringType()),
								Columns.column("Labour Name", "labour_name", DataTypes.stringType()),
								//Columns.column("Email Id", "email_id", DataTypes.stringType()),
								Columns.column("Start Date", "start_date", DataTypes.dateType()),
								Columns.column("End Date", "end_date", DataTypes.dateType()),
								Columns.column("Assigned Date", "assigned_Date", DataTypes.dateType())
								
								)

						.title(Templates.createTitleComponent("Assigned Task Report"))

						.pageFooter(Templates.footerComponent)
						// show page number on the page footer
						.setDataSource(resultsetdatasource);
				// "SELECT contractor_name, country, mobile_no, email_id, city, address,
				// zipcode, JoinDate FROM contractor_details",
				System.out.println("c"); // connection);

				try {
					// show the report
					report.show();

					report.toPdf(new FileOutputStream("D:/AssignedTaskReport.pdf"));
				} catch (Exception ex) {

					ex.printStackTrace();
				}
			}
		});
		btnLabourTasks.setBounds(256, 25, 168, 28);
		panel_4.add(btnLabourTasks);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(651, 16, 12, 503);
		panel_5.add(separator_1);

		JLabel lblLabourPayout = new JLabel("Labour Payout");
		lblLabourPayout.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblLabourPayout.setBounds(226, 27, 127, 34);
		panel_5.add(lblLabourPayout);

		JLabel lblContractorPayout = new JLabel("Contractor Payout");
		lblContractorPayout.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblContractorPayout.setBounds(898, 27, 161, 34);
		panel_5.add(lblContractorPayout);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(32, 120, 584, 9);
		panel_5.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(706, 116, 584, 9);
		panel_5.add(separator_3);

		JLabel Ldays = new JLabel("");
		Ldays.setBounds(360, 180, 55, 16);
		panel_5.add(Ldays);
		JLabel Cdays = new JLabel("");
		Cdays.setBounds(1011, 180, 55, 16);
		panel_5.add(Cdays);

		JButton btnSelectLabour = new JButton("Select Labour");
		btnSelectLabour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DbConnect dbc = new DbConnect();
				ResultSet rs = null;
				ResultSet rs2 = null;
				String inputString1 = "";
				String inputString2 = "";
				long inputdays;
				int days;
				try {
					java.util.List<String> valAttLab = Arrays
							.asList(cboPaylabour.getSelectedItem().toString().split(","));
					// JOptionPane.showMessageDialog(null, "Saved Successfully - " +
					// valAttLab.get(1));
					rs = dbc.SqlSelect(
							"select IBAN,Hourlypay from labour_details where labour_id = " + valAttLab.get(1));
					rs2 = dbc.SqlSelect(
							"Select min(att_datetime) as min ,max(att_datetime) as max from lab_attendance where labour_id ="
									+ valAttLab.get(1));
					SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-DD");
					System.out.println("Days: " + inputString1 + inputString2);
					while (rs2.next()) {
						// txtIBANLabour.setText(rs.getString("IBAN"));
						// txtAmountLabour.setText(rs.getString("Hourlypay"));
						inputString1 = String.valueOf(rs2.getString("min"));
						inputString2 = String.valueOf(rs2.getString("max"));

					}

					Date date1 = myFormat.parse(inputString1);
					Date date2 = myFormat.parse(inputString2);
					long diff = date2.getTime() - date1.getTime();
					System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
					inputdays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
					days = (int) inputdays;
					Ldays.setText(String.valueOf(days) + " Days");
					while (rs.next()) {
						txtIBANLabour.setText(rs.getString("IBAN"));
						txtAmountLabour
								.setText(String.valueOf((Integer.parseInt(rs.getString("Hourlypay")) * 8 * days)));
					}
					JOptionPane.showMessageDialog(null, "Saved Successfully - " + rs.getString("IBAN"));
				} catch (Exception e) {
					// TODO: handle exception
					// JOptionPane.showMessageDialog(null, "ERROR in Payout - " + e.getMessage());
				}
			}
		});
		btnSelectLabour.setBounds(421, 77, 116, 28);
		panel_5.add(btnSelectLabour);

		JButton btnSelectContractor = new JButton("Select Contractor");
		btnSelectContractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DbConnect dbc = new DbConnect();
				ResultSet rs = null;
				ResultSet rs1 = null;
				ResultSet rs2 = null;
				String inputString1 = "";
				String inputString2 = "";
				long inputdays;
				int days;
				try {
					java.util.List<String> valAttLab = Arrays
							.asList(cboPayContractor.getSelectedItem().toString().split(","));

					rs = dbc.SqlSelect("select IBAN from contractor_details where contractor_no =" + valAttLab.get(1));
					rs1 = dbc.SqlSelect(
							"Select min(att_datetime) as min ,max(att_datetime) as max from lab_attendance where labour_id in (select labour_id from labour_details where contractor_no="
									+ valAttLab.get(1) + ") ");
					rs2 = dbc.SqlSelect("select sum(HourlyPay) as HourlyPay from labour_details where contractor_no="
							+ valAttLab.get(1));

					while (rs1.next()) {

						inputString1 = String.valueOf(rs1.getString("min"));
						inputString2 = String.valueOf(rs1.getString("max"));

					}
					SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-DD");
					System.out.println("Days: " + inputString1 + inputString2);

					Date date1 = myFormat.parse(inputString1);
					Date date2 = myFormat.parse(inputString2);
					long diff = date2.getTime() - date1.getTime();
					System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
					inputdays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
					days = (int) inputdays;
					Cdays.setText(String.valueOf(days) + " Days");
					while (rs.next()) {
						txtIBANCont.setText(rs.getString("IBAN"));

					}
					while (rs2.next()) {
						txtAmountContractor
								.setText(String.valueOf((Integer.parseInt(rs2.getString("Hourlypay")) * 8 * days)));
					}

					JOptionPane.showMessageDialog(null, "Saved Successfully - " + rs.getString("IBAN"));
				} catch (Exception e) {
					// TODO: handle exception
					// JOptionPane.showMessageDialog(null, "ERROR in Payout - " +
					// e.getMessage().toString());
				}
			}
		});
		btnSelectContractor.setBounds(1044, 77, 134, 28);
		panel_5.add(btnSelectContractor);

		txtIBANLabour = new JTextField();
		txtIBANLabour.setBounds(226, 134, 122, 28);
		panel_5.add(txtIBANLabour);
		txtIBANLabour.setColumns(10);

		txtIBANCont = new JTextField();
		txtIBANCont.setColumns(10);
		txtIBANCont.setBounds(877, 137, 122, 28);
		panel_5.add(txtIBANCont);

		JLabel lblIban = new JLabel("IBAN");
		lblIban.setBounds(155, 141, 55, 16);
		panel_5.add(lblIban);

		JLabel lblIban_1 = new JLabel("IBAN");
		lblIban_1.setBounds(799, 143, 55, 16);
		panel_5.add(lblIban_1);

		txtAmountLabour = new JTextField();
		txtAmountLabour.setBounds(226, 174, 122, 28);
		panel_5.add(txtAmountLabour);
		txtAmountLabour.setColumns(10);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(155, 180, 55, 16);
		panel_5.add(lblAmount);

		JLabel label = new JLabel("Amount");
		label.setBounds(799, 180, 55, 16);
		panel_5.add(label);

		txtAmountContractor = new JTextField();
		txtAmountContractor.setColumns(10);
		txtAmountContractor.setBounds(877, 174, 122, 28);
		panel_5.add(txtAmountContractor);

		JButton btnPayLabour = new JButton("PAY Labour");
		btnPayLabour.setBounds(226, 226, 127, 28);
		panel_5.add(btnPayLabour);

		JButton btnPayContractor = new JButton("PAY Contractor");
		btnPayContractor.setBounds(877, 226, 127, 28);
		panel_5.add(btnPayContractor);

		JLabel lblGroupNo = new JLabel("Group No. 6 | Sunny Londhe, Arti Kapade, Shivani Borkar, Sumukh Venkatesh");
		lblGroupNo.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 19));
		lblGroupNo.setBounds(6, 757, 827, 52);
		panel.add(lblGroupNo);

		JLabel lblContractLabourManagement = new JLabel("Contract Labour Management System");
		lblContractLabourManagement.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
		lblContractLabourManagement.setBounds(458, 65, 425, 94);
		panel.add(lblContractLabourManagement);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame mf = new MainFrame();
				mf.dispose();
				mf.setVisible(true);
			}
		});
		btnRefresh.setBounds(1231, 772, 90, 28);
		panel.add(btnRefresh);
	}
}
