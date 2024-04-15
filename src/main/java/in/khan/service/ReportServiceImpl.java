package in.khan.service;


import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.khan.entity.CitizenPlan;
import in.khan.repo.CitizenPlanRepo;
import in.khan.request.SearchRequest;
import in.khan.utils.EmailSender;
import in.khan.utils.ExcelGenerator;
import in.khan.utils.PdfGenerator;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepo planRepo;
	@Autowired
	private ExcelGenerator excelGenerator;
	@Autowired
	private PdfGenerator pdfGenerator;
	@Autowired
	private EmailSender emailSender;

	@Override
	public List<String> getPlanName() {
		return planRepo.getPlansName();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {

		CitizenPlan entity = new CitizenPlan();
		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}

		if (null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String date = request.getStartDate();
			DateTimeFormatter formetter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate startDate = LocalDate.parse(date, formetter);
			entity.setPlanStartDate(startDate);
		}
		if (null != request.getEndDate() && !"".equals(request.getEndDate())) {
			String date = request.getEndDate();
			DateTimeFormatter formetter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate endDate = LocalDate.parse(date, formetter);
			entity.setPlanEndDate(endDate);
		}

		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse res) throws Exception {
		File f = new File("plans.xls");
		excelGenerator.generator(res, planRepo.findAll() ,f);
		emailSender.sendEmail("text to subject", "<h1>Text to Body</h1>", "itiskhans@gmail.com",f);
		f.delete();
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse res) throws Exception {
		
		File f= new File("plans.pdf");
		pdfGenerator.generator(res, planRepo.findAll(), f);
		emailSender.sendEmail("text to subject", "<h1>Text to Body</h1>", "itiskhans@gmail.com",f);
		f.delete();
		return true;
	}

	@Override
	public List<String> getPlanStatuses() {
		return planRepo.getPlanStatus();
	}

}
