package com.dojo.youthbankserver.controllers;

import com.dojo.youthbankserver.dtos.BusinessDTO;
import com.dojo.youthbankserver.entities.LoginUser;
import com.dojo.youthbankserver.entities.User;

import com.dojo.youthbankserver.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/users")
public class UserController {
	@Autowired
	private UserService userService;

	@Value("${jwt.secret}")
	private String secretKey;

	@GetMapping("")
	public ResponseEntity<List<User>> users(){
		return ResponseEntity.ok().body(userService.allUsers());
	}

//	    @GetMapping("/search")
//    public List<User> searchUser(@RequestParam(name = "keyword",defaultValue = "") String keyword){
//        return userService.searchUser("%"+keyword+"%");
//    }
	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody User newUser, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Validation errors: " + result.getAllErrors());
		}
//		if (!isAuthenticated()) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
//		}
		User registeredUser = userService.register(newUser, result);
		if (registeredUser == null) {
			return ResponseEntity.badRequest().body("User registration failed.");
		}

		String token = generateToken(registeredUser);
		return ResponseEntity.ok().body(token);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginUser newLogin, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Validation errors: " + result.getAllErrors());
		}
//		if (!isAuthenticated()) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
//		}
		User user = userService.login(newLogin, result);
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed. Invalid credentials.");
		}
		String token = generateToken(user);
		return ResponseEntity.ok().body(token);
	}


	// In a JWT-based system, there's no need for explicit logout on the server side.
	// The client can simply discard the token to "logout".
	// However, you can still keep this endpoint for consistency or other future purposes.
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
