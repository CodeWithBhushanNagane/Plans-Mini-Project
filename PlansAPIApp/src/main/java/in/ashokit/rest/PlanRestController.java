package in.ashokit.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.constants.AppConstants;
import in.ashokit.entity.Plan;
import in.ashokit.props.AppProperties;
import in.ashokit.service.PlanService;

@RestController
public class PlanRestController {

	private PlanService planService;

	private Map<String, String> messages;

	public PlanRestController(PlanService planService, AppProperties appProperties) {
		this.planService = planService;
		this.messages = appProperties.getMessages();
		System.out.println(this.messages);
	}

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> getAllPlanCategories() {
		return new ResponseEntity<>(planService.getAllPlanCategories(), HttpStatus.OK);
	}

	@PostMapping("/createPlan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
		String responseMsg = planService.savePlan(plan) ? messages.get(AppConstants.PLAN_SAVED_SUCCESS)
				: messages.get(AppConstants.PLAN_SAVE_FAILED);
		return new ResponseEntity<String>(responseMsg, HttpStatus.CREATED);
	}

	@GetMapping("/allPlans")
	public ResponseEntity<List<Plan>> getAllPlans() {
		return new ResponseEntity<List<Plan>>(planService.getAllPlans(), HttpStatus.OK);
	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> getPlanById(@PathVariable Integer planId) {
		return new ResponseEntity<Plan>(planService.getPlanById(planId), HttpStatus.OK);
	}

	@PutMapping("/updatePlan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
		String responseMsg = planService.updatePlan(plan) ? messages.get(AppConstants.PLAN_UPDATE_SUCCESS)
				: messages.get(AppConstants.PLAN_UPDATE_FAILED);
		return new ResponseEntity<String>(responseMsg, HttpStatus.OK);
	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		String responseMsg = planService.deletePlan(planId) ? messages.get(AppConstants.PLAN_DELETE_SUCCESS)
				: messages.get(AppConstants.PLAN_DELETE_FAILED);
		return new ResponseEntity<String>(responseMsg, HttpStatus.OK);
	}

	@PutMapping("/statusChange/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status) {
		String responseMsg = planService.chnagePlanStatus(planId, status)
				? messages.get(AppConstants.PLAN_STATUS_CHANGE_SUCCESS)
				: messages.get(AppConstants.PLAN_STATUS_CHANGE_FAILED);
		return new ResponseEntity<String>(responseMsg, HttpStatus.OK);
	}

}
