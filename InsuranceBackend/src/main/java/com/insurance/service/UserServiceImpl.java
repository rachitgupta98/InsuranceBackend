package com.insurance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.LoginDto;
import com.insurance.entities.Policy;
import com.insurance.entities.User;
import com.insurance.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public ApiResponse signUpUser(User user) {
		// TODO Auto-generated method stub
		long reg_userId = userRepository.signUpUser(user);

		if (reg_userId != 0) {

			ApiResponse apiResponse = new ApiResponse(200, "registered Successfully", user);
			return apiResponse;
		} else {
			ApiResponse apiResponse = new ApiResponse(400, "registration Failed ", user);
			return apiResponse;
		}

	}


	@Override
    public ApiResponse login(LoginDto loginDto) {
		
        User user = userRepository.findByEmail(loginDto.getUserEmail());
        System.out.println("in login api");
          if(user.getUserPassword().equals(loginDto.getUserPassword())) {
    
        	  return new ApiResponse(200, "SUCCESS", user) ;
          }
       
          return new ApiResponse(400, "FAILED", null) ;
        
     
    }

	@Override
	public ApiResponse viewAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse findUserById(long userId) {
		// TODO Auto-generated method stub
	return null;
	}

	@Override
	public ApiResponse deleteUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}


	
	@Override
	public ApiResponse findUserByEmail(String userEmail) {
		LoginDto loginDto=new LoginDto();
		 User user = userRepository.findByEmail(loginDto.getUserEmail());
	     
	          if(user.getUserEmail().equals(loginDto.getUserEmail())) {
	    
	        	  return new ApiResponse(200, "SUCCESS", user) ;
	          }
	       
	          return new ApiResponse(400, "FAILED", null) ;
	}



	 public ApiResponse updatePassword(String email, String newPassword) {
	       
		  User user=userRepository.findByEmail(email);
	        user.setUserPassword(newPassword);
	       userRepository.save(user);
	       return new ApiResponse(200, "UPDATED", user) ;
	    }

	

	

}
