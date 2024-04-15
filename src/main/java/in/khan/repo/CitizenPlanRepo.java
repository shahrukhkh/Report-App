package in.khan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.khan.entity.CitizenPlan;
@Repository
public interface CitizenPlanRepo extends JpaRepository<CitizenPlan, Integer> {

	@Query(value ="SELECT DISTINCT(plan_name) from citizen_plans_info", nativeQuery = true)
	public List<String> getPlansName();
	
	@Query(value="SELECT DISTINCT(plan_status) from citizen_plans_info" ,nativeQuery =true)
	public List<String> getPlanStatus();
}
