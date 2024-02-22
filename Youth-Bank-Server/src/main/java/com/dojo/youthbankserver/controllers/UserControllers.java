package com.dojo.youthbankserver.controllers;


import com.dojo.youthbankserver.dtos.PersonalDTO;
import com.dojo.youthbankserver.entities.LoginUser;
import com.dojo.youthbankserver.entities.User;
import com.dojo.youthbankserver.exceptions.PersonalNotFoundException;
import com.dojo.youthbankserver.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Controller
public class UserControllers {

	    // Add once service is implemented:

	     private UserService userServ;
	    @GetMapping("/")
	    public String index(Model model) {
	        // Bind empty User and LoginUser objects to the JSP
	        // to capture the form input
	        model.addAttribute("newUser", new User());
	        model.addAttribute("newLogin", new LoginUser());
	        return "index.jsp";
	    }

//	@GetMapping("/{id}")
//	public ResponseEntity<PersonalDTO> getPersonal(@PathVariable(name = "id") Long personalId) throws PersonalNotFoundException {
//		return ResponseEntity.ok().body(personalServiceImpl.getPersonal(personalId));
//	}


//	    @GetMapping("/projects")
//	    public String welcome(Model model, HttpSession session) {
//	        Long userId = (Long) session.getAttribute("user_id");
//	        User savedUser = userServ.findUserById(userId);
//	        model.addAttribute("user", savedUser);
//	        return "dashboard.jsp";
//	    }

	    

	    @PostMapping("/register")
	    public String register(@Valid @ModelAttribute("newUser") User newUser, 
	            BindingResult result, Model model, HttpSession session) {

	        // TO-DO Later -- call a register method in the service 
	        // to do some extra validations and create a new user!
	        userServ.register(newUser, result);
	        if(result.hasErrors()) {
	            // Be sure to send in the empty LoginUser before 
	            // re-rendering the page.
	            model.addAttribute("newLogin", new LoginUser());
	            return "index.jsp";
	        }
	        
	        // No errors! 
	        // TO-DO Later: Store their ID from the DB in session, 
	        // in other words, log them in.
	        session.setAttribute("user_id", newUser.getId());
	        return "redirect:/projects";
	    }
	    
	    @PostMapping("/login")
	    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
	            BindingResult result, Model model, HttpSession session) {
	        // Add once service is implemented:
	         User user = userServ.login(newLogin, result);
	        if(result.hasErrors()) {
	            model.addAttribute("newUser", new User());
	            return "index.jsp";
	        }
	    
	        session.setAttribute("user_id", user.getId());
	        return "redirect:/projects";
	    }
	    
	    @GetMapping("/logout")
	    public String logout(HttpSession s) {
	    	s.invalidate();
	    	return "redirect:/";
	    }
	  
	}



