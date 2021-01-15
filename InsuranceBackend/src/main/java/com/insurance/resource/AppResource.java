package com.insurance.resource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.ClaimDocumentDto;
import com.insurance.dto.ClaimDto;
import com.insurance.dto.LoginDto;
import com.insurance.dto.PaymentDto;
import com.insurance.dto.PolicyDto;
import com.insurance.dto.RenewDto;
import com.insurance.service.VehicleService;
import com.insurance.entities.Admin;
import com.insurance.entities.Policy;
import com.insurance.entities.User;
import com.insurance.entities.Vehicle;
import com.insurance.service.AdminService;
import com.insurance.service.PaymentService;
import com.insurance.service.PolicyService;
import com.insurance.service.UserService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class AppResource {

	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	PolicyService policyService;

	@Autowired
	AdminService adminService;

	@Autowired
	UserService userService;
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping(value="/documentUpload/{claimId}")
	public ApiResponse docUpload(@PathVariable long claimId,@RequestParam("file") MultipartFile file) {
		//long claimId = claimDocumentDto.getClaimId();
		//System.out.println(claimDocumentDto.getDocFile().getOriginalFilename());
		String imgUploadLocation = "D:/CLaimImages/";
		String uploadedFileName = file.getOriginalFilename();
		String newFileName = claimId + "-" + uploadedFileName;
		String targetFileName = imgUploadLocation + newFileName;
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(targetFileName));
		} catch(IOException e) {
//			e.printStackTrace(); //hoping no error would occur
//			Status status = new Status();
//			status.setStatus(StatusType.FAILED);
//			status.setMessage("File upload failed!");
//			return status;
		}
		return policyService.updateClaim(claimId, newFileName);
		
//		customerService.updateProfilePic(customerId, newFileName);
//		Status status = new Status();
//		status.setStatus(StatusType.SUCCESS);
//		status.setMessage("Profile pic updated!");
//		return status;
	}

	@RequestMapping(value = "/addorUpdateUser", method = RequestMethod.POST)
	public ApiResponse addAUser(@RequestBody User user) {
		ApiResponse apiResponse = userService.signUpUser(user);
		return apiResponse;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ApiResponse login(@RequestBody LoginDto loginDto) {

		return userService.login(loginDto);
	}

	@RequestMapping(value = "/insurance/addOrUpdateVehicle", method = RequestMethod.POST)
	public ApiResponse addOrUpdateVehicleDetails(@RequestBody Vehicle vehicle) {

		return vehicleService.addOrUpdateVehicle(vehicle);
	}
	
	@PostMapping(value = "/insurance/buyPolicy")
	public ApiResponse buyPolicy(@RequestBody PolicyDto policy) {
		policy.setPurchaseDate(LocalDate.now());
		policy.setPolicyStartDate(LocalDate.now());
		policy.setPolicyEndDate(LocalDate.now().plusYears(policy.getPlanYear()));
		System.out.println(policy.getPolicyStartDate());
		System.out.println(policy.getPolicyEndDate());
		return policyService.buyPolicy(policy);
	}
	
//	@GetMapping(value = "/insurance/findUserById")
//	public ApiResponse findUser(@RequestParam long userId) {
//
//		return userService.findUserById(userId);
//	}
	
	@PostMapping(value = "/insurance/claimPolicy")
	public ApiResponse requestClaimPolicy(@RequestBody ClaimDto claimdto) {

		return policyService.claimPolicy(claimdto);
	}
	@GetMapping(value="/insurance/renewPolicy")
	public ApiResponse renewPolicy(@RequestParam long policyId) {
		return policyService.renewPolicy(policyId);
	}
	
	@PostMapping(value = "/insurance/addAdmin")
	public ApiResponse registerAdmin(@RequestBody Admin admin) {

		return adminService.addOrUpdateAdmin(admin);
	}
	
	@GetMapping(value="/insurance/findPolicyByVehicleId")
	public ApiResponse findPolicyByVehicleId(@RequestParam long vehicleId) {
		return policyService.findPolicyByVehicleId(vehicleId);
	}
	
	@GetMapping(value="/insurance/findVehicleByRegNo")
	public ApiResponse findVehicleByRegNo(@RequestParam String regist) {
		return vehicleService.findVehicleByRegNo(regist);
	}
	
	@RequestMapping(value = "/insurance/findUser/{userId}")
	public ApiResponse findUser(@PathVariable("userId") long userId) {
		System.out.println("entering");
		return userService.findUserById(userId);
	}
	
	@RequestMapping(value="/insurance/policies/{userId}")
	public ApiResponse findPolicybyuserId(@PathVariable("userId") long userId)
	{
		return policyService.findPolicyByUserId(userId);
	}
	
	@PostMapping(value="/insurance/policy/payment")
	public ApiResponse PolicyPayment(@RequestBody PaymentDto payDto) {
		
		return paymentService.pay(payDto);
	}
}

