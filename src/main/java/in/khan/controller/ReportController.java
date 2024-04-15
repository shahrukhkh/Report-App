package in.khan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.khan.entity.CitizenPlan;
import in.khan.request.SearchRequest;
import in.khan.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {

	@Autowired
	private ReportService service;
	@GetMapping("/pdf")
	public void exportPdf(HttpServletResponse res) throws Exception
	{
		res.setContentType("application/pdf");
		res.addHeader("Content-Disposition", "attachment;filename=plans.pdf");
		service.exportPdf(res);
	}
	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse res) throws Exception
	{
		res.setContentType("application/octet-stream");
		res.addHeader("Content-Disposition", "attachment;filename=plans.xls");
		service.exportExcel(res);
	}
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest request,Model model)
	{
		
		System.out.println(request);
		List<CitizenPlan> list = service.search(request);
		model.addAttribute("lists", list);
		init(model);
		return "index";
	}
	@GetMapping("/")
	public String getIndex(Model model)
	{
		
		model.addAttribute("search", new SearchRequest());
		init(model);
		return "index";
	}
	private void init(Model model) {
		model.addAttribute("planName",service.getPlanName());
		model.addAttribute("planStatus", service.getPlanStatuses());
	}
}
