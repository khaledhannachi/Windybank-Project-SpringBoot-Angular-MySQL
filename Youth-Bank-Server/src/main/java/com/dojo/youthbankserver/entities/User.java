//package com.dojo.youthbankserver.entities;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.format.annotation.DateTimeFormat;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.PrePersist;
//import jakarta.persistence.PreUpdate;
//import jakarta.persistence.Table;
//import jakarta.persistence.Transient;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//
//@Entity
//@AllArgsConstructor
//@Table(name="users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotEmpty(message="firstName is required!")
//    @Size(min=3, max=30, message="firstName must be between 3 and 30 characters")
//    private String firstName;
//
//    @NotEmpty(message="Last name is required!")
//    @Size(min=3, max=30, message="Last name  must be between 3 and 30 characters")
//    private String lastName;
//
//    @NotEmpty(message="Email is required!")
//    @Email(message="Please enter a valid email!")
//    private String email;
//
//    @NotEmpty(message="Password is required!")
//    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
//    private String password;
//
//    @Transient
//    @NotEmpty(message="Confirm Password is required!")
//    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
//    private String confirm;
//
//	// This will not allow the createdAt column to be updated after creation
//	@Column(updatable = false)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private Date createdAt;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private Date updatedAt;
//
////	1:M
//	@OneToMany(mappedBy="userProfessional", fetch = FetchType.LAZY)
//	private List<Business> userBuisnessAccounts;
////	1:M
//	@OneToMany(mappedBy="userBuisness", fetch = FetchType.LAZY)
//	private List<Professional> userProfessionalAccounts;
////	1:M
//	@OneToMany(mappedBy="userCustomer", fetch = FetchType.LAZY)
//	private List<Customer> userPersonalAccounts;
//
////	----- methods ---
//// other getters and setters removed for brevity
//
//@PrePersist
//protected void onCreate() {
//	this.createdAt = new Date();
//}
//
//@PreUpdate
//protected void onUpdate() {
//	this.updatedAt = new Date();
//}
//
//
//}
//
