package com.dojo.youthbankserver.controllers;
import com.dojo.youthbankserver.dtos.UserDTO;
import com.dojo.youthbankserver.entities.LoginUser;
import com.dojo.youthbankserver.entities.User;
import com.dojo.youthbankserver.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<List<UserDTO>> users(){
		return ResponseEntity.ok().body(userService.allUsers());
	}

//	    @GetMapping("/search")
//    public List<User> searchUser(@RequestParam(name = "keyword",defaultValue = "") String keyword){
//        return userService.searchUser("%"+keyword+"%");
//    }
@PostMapping("/register")
public ResponseEntity<Object> register(@Valid @RequestBody User newUser, BindingResult result) {
	if (result.hasErrors()) {
		// Construct a response entity with detailed error messages
		return ResponseEntity.badRequest().body("Validation errors: " + result.getAllErrors());
	}

	User registeredUser = userService.register(newUser, result);
	if (registeredUser == null) {
		return ResponseEntity.badRequest().body("User registration failed.");
	}

	String token = generateToken(registeredUser);
	HttpHeaders headers = new HttpHeaders();
	headers.add("Authorization", token);
	return ResponseEntity.ok().headers(headers).body("Registration successful.");
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
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		return ResponseEntity.ok().headers(headers).body("login successful.");
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
				.signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
				.compact();
	}
	// Helper method to check if user is authenticated (example implementation)
//	private boolean isAuthenticated() {
//		// Example implementation: check if token is present in request header
//		// You might have a better method of authentication depending on your application
//		String token = request.getBody("Authorization");
//		return token != null && !token.isEmpty();
//	}

}
