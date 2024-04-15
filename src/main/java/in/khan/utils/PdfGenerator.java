package in.khan.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.khan.entity.CitizenPlan;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class PdfGenerator {

	public void generator(HttpServletResponse res, List<CitizenPlan> plans, File f) throws Exception {

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, res.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();
		Font headerFont = new Font(Font.TIMES_ROMAN, 20, Font.BOLD, Color.magenta);
		Paragraph p = new Paragraph("Citizen Plan Info", headerFont);
		p.setAlignment(Element.ALIGN_CENTER);
		p.setSpacingBefore(20.0f);
		p.setSpacingAfter(25.0f);
		document.add(p);
		Font tableHead = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, Color.blue);

		PdfPTable table = new PdfPTable(7);

		table.setSpacingAfter(6);
		table.addCell(new Phrase("ID", tableHead));
		table.addCell(new Phrase("Name", tableHead));
		table.addCell(new Phrase("Plan Name", tableHead));
		table.addCell(new Phrase("Plan Status", tableHead));
		table.addCell(new Phrase("Plan End Date", tableHead));
		table.addCell(new Phrase("Plan Start Date", tableHead));
		table.addCell(new Phrase("Benefit Amt", tableHead));

		for (CitizenPlan pl : plans) {
			table.addCell(pl.getCitizenId().toString());
			table.addCell(pl.getCitizenName());
			table.addCell(pl.getPlanName());
			table.addCell(pl.getPlanStatus());
			if (null != pl.getPlanStartDate()) {
				table.addCell(pl.getPlanStartDate() + "");
			} else {
				table.addCell("N/A");
			}
			if (null != pl.getPlanEndDate()) {
				table.addCell(pl.getPlanEndDate() + "");
			} else {
				table.addCell("N/A");
			}
			if (null != pl.getBenefitAmt()) {
				table.addCell(pl.getBenefitAmt() + "");
			} else {
				table.addCell("N/A");
			}

		}

		document.add(table);
		document.close();
	}

}
