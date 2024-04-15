package in.khan.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import in.khan.entity.CitizenPlan;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class ExcelGenerator {


	public void generator(HttpServletResponse res, List<CitizenPlan> list, File f)throws Exception {
		
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("data");
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Id");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Start Date");
		headerRow.createCell(5).setCellValue("End Date");
		headerRow.createCell(6).setCellValue("Benefit Amt");
		int dataIndex = 1;
		for (CitizenPlan c : list) {
			Row row = sheet.createRow(dataIndex);
			row.createCell(0).setCellValue(c.getCitizenId());
			row.createCell(1).setCellValue(c.getCitizenName());
			row.createCell(2).setCellValue(c.getPlanName());
			row.createCell(3).setCellValue(c.getPlanStatus());
			if (null != c.getPlanStartDate()) {
				row.createCell(4).setCellValue(c.getPlanStartDate() + "");
			} else {
				row.createCell(4).setCellValue("N/A");
			}
			if (null != c.getPlanEndDate()) {
				row.createCell(5).setCellValue(c.getPlanEndDate() + "");
			} else {
				row.createCell(5).setCellValue("N/A");
			}

			if (null != c.getBenefitAmt()) {
				row.createCell(6).setCellValue(c.getBenefitAmt());
			} else {
				row.createCell(6).setCellValue("N/A");
			}
			dataIndex++;
		}

		ServletOutputStream stream = res.getOutputStream();
		FileOutputStream fos= new FileOutputStream(f);
		workbook.write(fos);
		fos.close();

		workbook.write(stream);

		workbook.close();
		
	}
}
