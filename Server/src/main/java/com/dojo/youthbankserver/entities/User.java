package com.dojo.youthbankserver.entities;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotEmpty(message="firstName is required!")
//    @Size(min=3, max=30, message="firstName must be between 3 and 30 characters")
    private String firstName;

//    @NotEmpty(message="Last name is required!")
//    @Size(min=3, max=30, message="Last name  must be between 3 and 30 characters")
    private String lastName;


//    @Past
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthDay;


//    @NotBlank(message="Country Of Birth is required!")
//    @Size(min=3, max=60, message="Country Of Birth must be between 3 and 60 characters.")
    private String countryOfBirth;


//    @NotBlank(message="Street is required!")
//    @Size(min=2, max=60, message="Street must be between 2 and 60 characters.")
    private String street;


//    @NotBlank(message="City is required!")
//    @Size(min=1, max=60, message="Street must be between 3 and 60 characters.")
    private String city;

    private int zipCode;

//    @NotBlank(message="Country of Residence is required!")
//    @Size(min=1, max=60, message="Country of Residence must be between 3 and 60 characters.")
    private String countryOfResidence;

//    @Pattern(regexp = "(\\+216|0216)[0-9]{8}")
    private String telephone;

//    @NotBlank(message="Nationality is required!")
//    @Size(min=1, max=60, message="Nationality must be between 3 and 60 characters.")
    private String nationality;


//    @NotEmpty(message="Email is required!")
//    @Email(message="Please enter a valid email!")
    private String email;

//    @NotEmpty(message="Password is required!")
//    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;

    private String role;

//    @Transient
//    @NotEmpty(message="Confirm Password is required!")
//    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
    private String confirm;

	// This will not allow the createdAt column to be updated after creation
//	@Column(updatable = false)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

//	1:M
	@OneToMany(mappedBy="businessLegalResponsible", fetch = FetchType.LAZY)
	private List<Business> userBuisnessAccounts;
//	1:M
	@OneToMany(mappedBy="userProfessional", fetch = FetchType.LAZY)
	private List<Professional> userProfessionalAccounts;
//	1:M

@OneToMany(mappedBy="userPersonal", fetch = FetchType.LAZY)
	private List<Personal> userPersonalAccounts;

//	----- methods ---
// other getters and setters removed for brevity

@PrePersist
protected void onCreate() {
	this.createdAt = new Date();
}

@PreUpdate
protected void onUpdate() {
	this.updatedAt = new Date();
}


}

