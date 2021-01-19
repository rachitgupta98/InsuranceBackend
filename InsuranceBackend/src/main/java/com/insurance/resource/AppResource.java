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
import com.insurance.dto.AdminDto;
import com.insurance.dto.ClaimApprovalDto;
import com.insurance.dto.ClaimDocumentDto;
import com.insurance.dto.ClaimDto;
import com.insurance.dto.LoginDto;
import com.insurance.dto.PaymentDto;
import com.insurance.dto.PolicyDto;
import com.insurance.dto.RenewDto;
import com.insurance.dto.ResetPasswordDto;
import com.insurance.service.VehicleService;
import com.insurance.entities.Admin;
import com.insurance.entities.Claim;
import com.insurance.entities.Policy;
import com.insurance.entities.User;
import com.insurance.entities.Vehicle;
import com.insurance.service.AdminService;
import com.insurance.service.EmailService;
import com.insurance.service.PaymentService;
import com.insurance.service.PolicyService;
import com.insurance.service.UserService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class AppResource {

	@Autowired
	EmailService emailService;

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

	@PostMapping(value = "/documentUpload/{claimId}")
	public ApiResponse docUpload(@PathVariable long claimId, @RequestParam("file") MultipartFile file) {
		String imgUploadLocation = "G:/LTI/backendData/";
		String uploadedFileName = file.getOriginalFilename();
		String newFileName = claimId + "-" + uploadedFileName;
		String targetFileName = imgUploadLocation + newFileName;
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(targetFileName));
		} catch (IOException e) {

		}
		return policyService.updateClaim(claimId, newFileName);

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
		return policyService.buyPolicy(policy);
	}

	@GetMapping(value = "/checkStatus")
	public ApiResponse checkClaimStatus(@RequestParam long claimId) {
		Claim claim = policyService.findClaimById(claimId);
		return new ApiResponse(200, "claim fetched", claim);
	}

	@PostMapping(value = "/insurance/claimPolicy")
	public ApiResponse requestClaimPolicy(@RequestBody ClaimDto claimdto) {

		return policyService.claimPolicy(claimdto);
	}

	@GetMapping(value = "/insurance/renewPolicy")
	public ApiResponse renewPolicy(@RequestParam long policyId, @RequestParam long userId) {
		return policyService.renewPolicy(policyId, userId);
	}

	@PostMapping(value = "/insurance/addAdmin")
	public ApiResponse registerAdmin(@RequestBody Admin admin) {

		return adminService.addOrUpdateAdmin(admin);
	}

	@GetMapping(value = "/insurance/findPolicyByVehicleId")
	public ApiResponse findPolicyByVehicleId(@RequestParam long vehicleId) {
		return policyService.findPolicyByVehicleId(vehicleId);
	}

	@GetMapping(value = "/insurance/findVehicleByRegNo")
	public ApiResponse findVehicleByRegNo(@RequestParam String regist) {
		return vehicleService.findVehicleByRegNo(regist);
	}

	@RequestMapping(value = "/insurance/findUser/{userId}")
	public ApiResponse findUser(@PathVariable("userId") long userId) {

		return userService.findUserById(userId);
	}

	@RequestMapping(value = "/insurance/policies/{userId}")
	public ApiResponse findPolicybyuserId(@PathVariable("userId") long userId) {
		return policyService.findPolicyByUserId(userId);
	}

	@RequestMapping(value = "/insurance/claims/{userId}")
	public ApiResponse findClaimsbyuserId(@PathVariable("userId") long userId) {
		return policyService.findPolicyByUserId(userId);
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ApiResponse forgotPassword(@RequestBody User user) {

		String subject = "Here's the link to reset your password";
		String email = user.getUserEmail();
		String text = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + "\resetPassword"
				+ "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";
		emailService.sendEmail(email, text, subject);
		System.out.println("Email Sent.....");
		return userService.findByEmail(email);

	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ApiResponse resetPassword(@RequestParam String email, String password) {

		return userService.updatePassword(email, password);
	}

	@RequestMapping(value = "/totalpolicies", method = RequestMethod.GET)
	public ApiResponse countofPolicies() {
		return adminService.countOfpolicies();
	}

	@RequestMapping(value = "/totalclaims", method = RequestMethod.GET)
	public ApiResponse countofclaims() {
		return adminService.countOfclaimes();
	}

	@RequestMapping(value = "/viewClaims", method = RequestMethod.GET)
	public ApiResponse viewClaims() {
		return adminService.viewAllClaims();
	}

	@RequestMapping(value = "/viewUsers", method = RequestMethod.GET)
	public ApiResponse viewAllusers() {
		return userService.viewAllUsers();
	}

	@RequestMapping(value = "/existingPolicies", method = RequestMethod.GET)
	public ApiResponse existingpolicyCount() {
		return adminService.findexistingPolicies();
	}

	@RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
	public ApiResponse adminlogin(@RequestBody AdminDto admindto) {
		return adminService.findadminByEmail(admindto);
	}

	@RequestMapping(value = "/claim/{claimId}", method = RequestMethod.GET)
	public ApiResponse findclaimbyId(@PathVariable("claimId") long claimId) {
		Claim claim = policyService.findClaimById(claimId);

		if (claim != null) {
			return new ApiResponse(200, "claim fetched", claim);
		}
		return new ApiResponse(400, "claim not found", null);

	}

	@RequestMapping(value = "/updateclaim", method = RequestMethod.POST)
	public ApiResponse updateClaimstatus(@RequestBody ClaimApprovalDto claimapproval) {
		return adminService.updateClaimStatus(claimapproval);
	}

	@PostMapping(value = "/insurance/policy/payment")
	public ApiResponse PolicyPayment(@RequestBody PaymentDto payDto) {

		return paymentService.pay(payDto);
	}

	@GetMapping(value = "/insurance/policy/downloads")
	public ApiResponse DownloadPolicy(@RequestParam long policyId) {
		return policyService.findPolicyByPolicyId(policyId);
	}
}
