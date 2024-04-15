package in.khan.service;

import java.util.List;

import in.khan.entity.CitizenPlan;
import in.khan.request.SearchRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {

	
	public List<String> getPlanName();
	public List<String> getPlanStatuses();
	public List<CitizenPlan> search(SearchRequest request);
	public boolean exportExcel(HttpServletResponse res) throws Exception;
	public boolean exportPdf(HttpServletResponse res) throws Exception;
}
