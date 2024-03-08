package com.dojo.youthbankserver.services;
import com.dojo.youthbankserver.dtos.BusinessDTO;
import com.dojo.youthbankserver.dtos.UserDTO;
import com.dojo.youthbankserver.entities.Business;
import com.dojo.youthbankserver.entities.LoginUser;
import com.dojo.youthbankserver.entities.SavingAccount;
import com.dojo.youthbankserver.entities.User;
import com.dojo.youthbankserver.exceptions.BusinessNotFoundException;
import com.dojo.youthbankserver.exceptions.UserNotFoundException;
import com.dojo.youthbankserver.mappers.UserMapper;
import com.dojo.youthbankserver.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepo;
	private UserMapper userMapper;
    // TO-DO: Write register and login methods!
		// READ ALL
		public List<UserDTO> allUsers(int page, int size){

			List<User> users=userRepo.findAll();
			List<UserDTO> userDTOs= users .stream()
					.map(user -> userMapper.fromUser(user))
					.collect(Collectors.toList());
			return userDTOs;
		}
//    public List<UserDTO> allUsers(int page, int size) {
//      Pageable pageable = PageRequest.of(page, size);
//      Page<User> usersPage = (Page<User>) userRepo.findAll(pageable);
//
//      List<UserDTO> userDTOs = usersPage.getContent().stream()
//        .map(user -> userMapper.fromUser(user))
//        .collect(Collectors.toList());
//
//      return userDTOs;
//    }
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
        newUser.setRole("User");
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


  public List<UserDTO> searchUser(String keyword) {
    List<User> usersList=userRepo.searchUser(keyword);
    List<UserDTO>userDTOSList= usersList.stream().map(user->userMapper.fromUser(user)).collect(Collectors.toList());
    return userDTOSList;
  }



}








