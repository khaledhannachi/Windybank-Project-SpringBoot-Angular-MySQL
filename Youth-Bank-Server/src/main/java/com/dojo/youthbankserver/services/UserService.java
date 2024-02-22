package com.dojo.youthbankserver.services;


import com.dojo.youthbankserver.entities.LoginUser;
import com.dojo.youthbankserver.entities.User;
import com.dojo.youthbankserver.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserService {                                                                                                  
 

    private UserRepository userRepo;
    
    // TO-DO: Write register and login methods!
    public User register(User newUser, BindingResult result) {
        // Reject if email is taken (present in the DB)
    	Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
    	if(potentialUser.isPresent()) {
    		result.rejectValue("email", "registerError", "Email is Taken !");
    	}
    	if (!newUser.getPassword().equals(newUser.getConfirm())) {
    		result.rejectValue("password", "registerError", "password does not match !");
    	}
    	if (result.hasErrors()) {
    		return null;
    	}else {
    		// Hash and set password; save user to DB
    		String hashedPw = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    		newUser.setPassword(hashedPw);
    		// save the user to the DB
    		return userRepo.save(newUser);
    	}
    }
    public User login(LoginUser newLoginObject, BindingResult result) {
        // Find if the user is in the DB
    	Optional<User>potientialUser = userRepo.findByEmail(newLoginObject.getEmail());
    	if(!potientialUser.isPresent()) {
    		result.rejectValue("email", "loginErrors", "email is not found !");
    	}else {
    		User user = potientialUser.get();
    		// check the password
    		if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
    		    result.rejectValue("password", "loginErrors", "Invalid Password!");
    		}
    		if(result.hasErrors()) {
    			return null;
    		}else {
    			return user;
    		}
    	}
        return null;
    }
    // find a user by ID from DB
    public User findUserById(Long id) {
    	Optional<User> maybeUser = userRepo.findById(id);
    	if(maybeUser.isPresent()) {
    		return maybeUser.get();
    	}else {
    		return null;
    	}
    }
}


    



   

