package com.dojo.youthbankserver.controllers;
import com.dojo.youthbankserver.dtos.BusinessDTO;
import com.dojo.youthbankserver.dtos.UserDTO;
import com.dojo.youthbankserver.entities.LoginUser;
import com.dojo.youthbankserver.entities.User;
import com.dojo.youthbankserver.exceptions.BusinessNotFoundException;
import com.dojo.youthbankserver.exceptions.UserNotFoundException;
import com.dojo.youthbankserver.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpHeaders;

import org.springframework.context.annotation.Bean;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	private UserService userService;

	@Value("${jwt.secret}")
	private String secretKey;

	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> users(@RequestParam(name="page",defaultValue = "0") int page,
                                             @RequestParam(name="size",defaultValue = "5")int size){
		return ResponseEntity.ok().body(userService.allUsers(page, size));
	}
//	@GetMapping("users/{id}")
//	public ResponseEntity<UserDTO> getUser(@PathVariable(name = "id") Long userId) throws UserNotFoundException {
//		return ResponseEntity.ok().body(userService.getUser(userId));
//	}

  @GetMapping("/search")
  public List<UserDTO> searchUser(@RequestParam(name = "keyword",defaultValue = "") String keyword){
    return userService.searchUser("%"+keyword+"%");
  }

	@PostMapping("/register")
	public ResponseEntity<Object> register(@Valid @RequestBody User newUser, BindingResult result) {
		// Validate the incoming User object using @Valid and BindingResult
		if (result.hasErrors()) {
			// If validation errors are present, return a bad request response with error details
			return ResponseEntity.badRequest().body("Validation errors: " + result.getAllErrors());
		}

		// Attempt to register the new user using the userService
		User registeredUser = userService.register(newUser, result);
		// Check if user registration was unsuccessful
		if (registeredUser == null) {
			return ResponseEntity.badRequest().body("User registration failed.");
		}
		String token = generateToken(registeredUser );
		Map<String, String> response = new HashMap<>();
		response.put("token", token); // Token is added as a key-value pair
		return ResponseEntity.ok().body(response); // Return the token as a JSON object
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@Valid @RequestBody LoginUser newLogin, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Validation errors: " + result.getAllErrors());
		}
		User user = userService.login(newLogin, result);
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed. Invalid credentials.");
		}
		String token = generateToken(user);
		Map<String, String> response = new HashMap<>();
		response.put("token", token); // Token is added as a key-value pair
		return ResponseEntity.ok().body(response); // Return the token as a JSON object
	}


	@PostMapping("/logout")
	public ResponseEntity<Void> logout() {
		return ResponseEntity.noContent().build();
	}

	private String generateToken(User user) {
		return Jwts.builder()
				.claim("userId", user.getId())
				.claim("email", user.getEmail())
				.claim("firstName", user.getFirstName())
				.claim("lastName", user.getLastName())
        .claim("role", user.getRole())
				.signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
				.compact();
	}



}
