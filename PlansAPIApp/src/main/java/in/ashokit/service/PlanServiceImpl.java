package in.ashokit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Plan;
import in.ashokit.entity.PlanCategory;
import in.ashokit.repo.PlanCategoryRepo;
import in.ashokit.repo.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	PlanRepo planRepo;

	@Autowired
	PlanCategoryRepo planCategoryRepo;

	@Override
	public Map<Integer, String> getAllPlanCategories() {
		List<PlanCategory> planCategories = planCategoryRepo.findAll();
		Map<Integer, String> planCategoryMap = new HashMap<Integer, String>();
		planCategories.forEach(category -> {
			planCategoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		return planCategoryMap;
	}

	@Override
	public boolean savePlan(Plan plan) {
		Plan savePlan = planRepo.save(plan);
		return savePlan.getPlanId() != null;
	}

	@Override
	public List<Plan> getAllPlans() {
		return planRepo.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> plan = planRepo.findById(planId);
		if (plan.isPresent())
			return plan.get();
		else
			return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		Plan savePlan = planRepo.save(plan);
		return savePlan.getPlanId() != null;
	}

	@Override
	public boolean deletePlan(Integer planId) {
		boolean status = false;
		try {
			planRepo.deleteById(planId);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean chnagePlanStatus(Integer planId, String status) {
		Optional<Plan> plan = planRepo.findById(planId);
		if (plan.isPresent()) {
			Plan toUpPlan = plan.get();
			toUpPlan.setActive(status);
			planRepo.save(toUpPlan);
			return true;
		}
		return false;
	}

}
