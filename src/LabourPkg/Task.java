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

public class Task extends JFrame {

	private JPanel contentPane;
	private JTextField txtTaskName;
	private JTextField txtTaskDisc;
	private JTable table;
	public String editValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Task frame = new Task();
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
					"select task_id, task_name, task_description, start_date, end_date, RequiredLabours, isActive FROM task_details");

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
	public Task() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1125, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCreateContractor = new JLabel("TaskForm");
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

		JLabel lblContractorName = new JLabel("Task Name");
		lblContractorName.setBounds(26, 6, 123, 29);
		panel_2.add(lblContractorName);
		lblContractorName.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtTaskName = new JTextField();
		txtTaskName.setBounds(159, 12, 284, 19);
		panel_2.add(txtTaskName);
		txtTaskName.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(158, 228, 79, 27);
		panel_2.add(btnSave);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(264, 228, 91, 27);
		panel_2.add(btnUpdate);

		JLabel lblAddress = new JLabel("Task Discription");
		lblAddress.setBounds(26, 37, 108, 20);
		panel_2.add(lblAddress);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtTaskDisc = new JTextField();
		txtTaskDisc.setBounds(159, 38, 284, 62);
		panel_2.add(txtTaskDisc);
		txtTaskDisc.setColumns(10);

		JDateChooser dtTStaetDate = new JDateChooser();
		dtTStaetDate.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		dtTStaetDate.setDateFormatString("yyyy-MM-dd");
		dtTStaetDate.setBounds(159, 111, 151, 29);
		panel_2.add(dtTStaetDate);

		JLabel lblNewLabel = new JLabel("Task Start Date");
		lblNewLabel.setBounds(26, 114, 123, 16);
		panel_2.add(lblNewLabel);

		JCheckBox chkActive = new JCheckBox("Is Active");
		chkActive.setSelected(true);
		chkActive.setBounds(264, 189, 123, 19);
		panel_2.add(chkActive);

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
		dtTStaetDate.setDate(date);
		Date redate;

		btnUpdate.setEnabled(false);
		JSpinner txtReqireLabours = new JSpinner();
		txtReqireLabours.setModel(new SpinnerNumberModel(1, 1, 500, 1));
		txtReqireLabours.setBounds(159, 184, 69, 28);
		JButton btnNewContractor = new JButton("Add New");
		btnNewContractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnUpdate.setEnabled(false);
				btnSave.setEnabled(true);
				// new Contractor
				txtTaskName.setText("");
				txtTaskDisc.setText("");
				txtReqireLabours.setValue(1);
				BindTable();
			}

		});
		btnNewContractor.setBounds(26, 227, 91, 28);
		panel_2.add(btnNewContractor);

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

		JLabel lblRelevingDate = new JLabel("Task End Date");
		lblRelevingDate.setBounds(26, 144, 85, 16);
		panel_2.add(lblRelevingDate);

		JDateChooser dtTEndDate = new JDateChooser();
		dtTEndDate.setDateFormatString("yyyy-MM-dd");
		dtTEndDate.setBounds(158, 144, 151, 29);
		panel_2.add(dtTEndDate);

		redate = new Date();
		dtTEndDate.setDate(redate);

		JLabel lblPayHr = new JLabel("No. Labour Require");
		lblPayHr.setBounds(26, 190, 123, 16);
		panel_2.add(lblPayHr);

		panel_2.add(txtReqireLabours);
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

					if (Objects.equals(txtTaskName.getText(), "")) {
						JOptionPane.showMessageDialog(null, "Task Name is missing. You cannot save.");

					} else {

						result = dbc.SqlInsUpdDel("update task_details set task_name = '" + txtTaskName.getText()
								+ "',task_description = '" + txtTaskDisc.getText() + "', start_date ='"
								+ df.format(dtTStaetDate.getDate()) + "', isActive = "
								+ (chkActive.isSelected() == true ? 1 : 0) + ",end_date='"
								+ df.format(dtTEndDate.getDate()) + "',RequiredLabours='" + txtReqireLabours.getValue()
								+ "' where task_id = " + editValue);

						JOptionPane.showMessageDialog(null, "Task Updated Successfully");

						if (result != 100) {
							btnUpdate.setEnabled(false);
							btnSave.setEnabled(true);
							jtpContracor.setSelectedIndex(1);
							txtTaskName.setText("");
							txtTaskDisc.setText("");

							BindTable();
						}

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

					if (Objects.equals(txtTaskName.getText(), "")) {
						JOptionPane.showMessageDialog(null, "Task Name is missing. You cannot save.");

					} else {

						// System.out.println(
						// df.format(dtJoinDate.getDate()) + "', " + (chkActive.isSelected() == true ? 1
						// : 0) + ") ");
						result = dbc.SqlInsUpdDel(

								"insert into task_details (task_name, task_description, start_date, end_date, RequiredLabours,isActive) "
										+ "values ('" + txtTaskName.getText() + "', '" + txtTaskDisc.getText() + "', '"
										+ df.format(dtTStaetDate.getDate()) + "', '" + df.format(dtTEndDate.getDate())
										+ "', '" + txtReqireLabours.getValue() + "',"
										+ (chkActive.isSelected() == true ? 1 : 0) + " )");

						JOptionPane.showMessageDialog(null, "Task Saved Successfully");
						if (result != 100) {
							txtTaskName.setText("");
							txtTaskDisc.setText("");
							txtReqireLabours.setValue(1);

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

						rs = dbc.SqlSelect("select * from task_details where task_id = " + editValue);
						while (rs.next()) {
							// contractor_no, type_of_contractor, contractor_name, country, mobile_no,
							// email_id, city, address, zipcode, JoinDate, IsActive
							txtTaskName.setText(rs.getString("task_name"));
							txtTaskDisc.setText(rs.getString("task_description"));
							boolean act = false;
							if (Objects.equals(rs.getString("isActive"), "1")) {
								act = true;

							}
							chkActive.setSelected(act);

							Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("start_date"));
							dtTStaetDate.setDate(dt);
							Date dt1 = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("end_date"));
							dtTEndDate.setDate(dt1);
							txtReqireLabours.setValue(Integer.parseInt(rs.getString("RequiredLabours")));

						}

					}
				} catch (Exception ex) {
				}

			}
		});

	}
}
