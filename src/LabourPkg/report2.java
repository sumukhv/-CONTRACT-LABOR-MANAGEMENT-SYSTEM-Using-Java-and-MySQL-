package LabourPkg;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;

import java.util.Date;

import net.sf.dynamicreports.examples.Templates;

import net.sf.dynamicreports.report.builder.style.StyleBuilder;

import net.sf.dynamicreports.report.constant.ListType;

import net.sf.dynamicreports.report.datasource.DRDataSource;

import net.sf.dynamicreports.report.exception.DRException;

import net.sf.jasperreports.engine.JRDataSource;

public class report2 {

	public report2() {

		build();

	}

	private void build() {

		StyleBuilder textStyle = stl.style(Templates.columnStyle).setBorder(stl.pen1Point());

		try {

			report()

					.setTemplate(Templates.reportTemplate)

					.setColumnStyle(textStyle)

					.columnGrid(ListType.HORIZONTAL_FLOW)

					.columns(

							col.column("ID", "id", type.integerType()),

							col.column("Item", "item", type.stringType()),

							col.column("Quantity", "quantity", type.integerType()),

							col.column("Unit price", "unitprice", type.bigDecimalType()),

							col.column("Order date", "orderdate", type.dateType()),

							col.column("Order date", "orderdate", type.dateYearToFractionType()),

							col.column("Order year", "orderdate", type.dateYearType()),

							col.column("Order month", "orderdate", type.dateMonthType()),

							col.column("Order day", "orderdate", type.dateDayType()))

					.title(Templates.createTitleComponent("ManyColumns"))

					.pageFooter(Templates.footerComponent)

					.setDataSource(createDataSource())

					.show();

		} catch (DRException e) {

			e.printStackTrace();

		}

	}

	private JRDataSource createDataSource() {

		DRDataSource dataSource = new DRDataSource("id", "item", "orderdate", "quantity", "unitprice");

		dataSource.add(5, "Notebook", new Date(), 1, new BigDecimal(500));

		dataSource.add(8, "Book", new Date(), 7, new BigDecimal(300));

		dataSource.add(15, "PDA", new Date(), 2, new BigDecimal(250));

		return dataSource;

	}

	public static void main(String[] args) {

		new report2();

	}

}
