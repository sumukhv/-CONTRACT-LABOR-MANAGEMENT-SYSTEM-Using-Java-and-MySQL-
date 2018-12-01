package LabourPkg;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.jasperreports.engine.JRResultSetDataSource;


public class report {

  public static void main(String[] args) {
	Connection connection = null;
	ResultSet rs = null;
	
	try {
		DbConnect dbc= new DbConnect();
		rs = dbc.SqlSelect("SELECT contractor_no,contractor_name, mobile_no, JoinDate FROM contractor_details");
		
	} catch (Exception e) {
		e.printStackTrace();
		return;
	} 
	  System.out.println("A");
	JasperReportBuilder report = DynamicReports.report();//a new report
	  System.out.println("B");
	  JRResultSetDataSource resultsetdatasource = new JRResultSetDataSource(rs);
	report
	 
	.columns(
	      Columns.column("contractor_no", "contractor_no", DataTypes.integerType()),
	      Columns.column("contractor_name", "contractor_name", DataTypes.stringType()),
	      Columns.column("mobile_no", "mobile_no", DataTypes.stringType()),
	      Columns.column("JoinDate", "JoinDate", DataTypes.dateType()))
	  .title(//title of the report
	   //   Components.text("SimpleReportExample")
			  Templates.createTitleComponent("SimpleReportExample"))
	  .title(Templates.createTitleComponent("SimpleReportExample"))

		.pageFooter(Templates.footerComponent)
		  //show page number on the page footer
	  .setDataSource(resultsetdatasource );
			  //"SELECT contractor_name, country, mobile_no, email_id, city, address, zipcode, JoinDate FROM contractor_details",
	System.out.println("c");           //                  connection);

	try {
              //show the report
		report.show();
		  
               
		report.toPdf(new FileOutputStream("D:/report.pdf"));
	} catch (Exception ex) {
		
		ex.printStackTrace();
	} 
  }
  
 

}