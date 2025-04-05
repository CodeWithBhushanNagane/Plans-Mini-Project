package in.ashokit.service;

import java.util.List;
import java.util.Map;

import in.ashokit.entity.Plan;

public interface PlanService {

	public Map<Integer, String> getAllPlanCategories();

	public boolean savePlan(Plan plan);

	public List<Plan> getAllPlans();

	public Plan getPlanById(Integer planId);

	public boolean updatePlan(Plan plan);

	public boolean deletePlan(Integer planId);

	public boolean chnagePlanStatus(Integer planId, String status);

}
