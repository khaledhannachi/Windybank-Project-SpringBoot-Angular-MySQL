package com.dojo.youthbankserver.entities;

import java.util.Date;
import java.util.List;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Professional {

    @NotEmpty(message="Password is required!")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;
    
    
    @NotBlank
	@NotEmpty(message="Email is required!")
	@Email(message="Please enter a valid email!")
	private String email;
    
    
    @NotBlank
    @NotEmpty(message="Tax Identification Number is required!")
    @Size(min=3, max=30, message="Tax Identification Number must be between 3 and 30 characters.")
    private String taxIdentificationNumber;
    
    
    @NotBlank
    @NotEmpty(message="Business registration Number is required!")
    @Size(min=3, max=30, message="Business registration Number must be between 3 and 30 characters.")
    private String businessRegistration;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message="First Name is required!")
    @Size(min=3, max=30, message="First Name must be between 3 and 30 characters.")
    private String firstName;

    @NotBlank(message="Last Name is required!")
    @Size(min=3, max=30, message="Last Name must be between 3 and 30 characters.")
    private String lastName;
    
    @Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthDay;
 
	@NotBlank(message="Country Of Birth is required!")
	@Size(min=3, max=60, message="Country Of Birth must be between 3 and 60 characters.")
	private String countryOfBirth;
	
	@NotBlank(message="Street is required!")
	@Size(min=2, max=60, message="Street must be between 2 and 60 characters.")
	private String street;
	
	@NotBlank(message="City is required!")
	@Size(min=1, max=60, message="Street must be between 3 and 60 characters.")
	private String city;
	
	@Range(min = 4, max= 4, message = "Postal Code should be exact 4 characters." )
	private int postalCode;
	
	@NotBlank(message="Country of Residence is required!")
	@Size(min=1, max=60, message="Country of Residence must be between 3 and 60 characters.")
	private String countryOfResidence;

	@Pattern(regexp = "(\\+216|0216)[0-9]{8}")
	private String telephone;
	
	@NotBlank(message=" Marital Status is required!")
	private String  maritalStatus;
	
	@NotBlank(message="idType is required!")
	private String idType;
	
	@Range(min = 10, max= 10, message = "Id number should be exact 10 characters." )
	private int idNumber;

	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date idDeliveryDate;
	
	@NotBlank(message="City is required!")
	@Size(min=1, max=60, message="City must be between 3 and 60 characters.")
	private String idDeliveryCity;
	
	@NotBlank(message="Nationality is required!")
	@Size(min=1, max=60, message="Nationality must be between 3 and 60 characters.")
	private String nationality;
	
	@NotBlank(message="Profession is required!")
	@Size(min=1, max=60, message="Profession must be between 3 and 60 characters.")
	private String profession;
	
	@NotBlank(message="Professional Status is required!")
	@Size(min=1, max=60, message="Professional Status must be between 1 and 60 characters.")
	private String professionalStatus;
 
	@NotBlank(message="Employer Name is required!")
	@Size(min=1, max=60, message="Employer Name must be between 1 and 60 characters.")
	private String employerName;
 
	@NotBlank(message="Employer Adress is required!")
	@Size(min=1, max=60, message="Employer Adress must be between 1 and 60 characters.")
	private String employerAdress;

	
	@NotEmpty(message="Net Pay is required!")
	private double netPay;

	
	
	
    @Transient
    @NotEmpty(message="Confirm Password is required!")
    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
    private String confirm;

    
    
    
	// This will not allow the createdAt column to be updated after creation
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

//	----- methods ---
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	//	one to many
	  @OneToMany(mappedBy = "professional", fetch = FetchType.LAZY)
	  private List<BankAccount> professionalBankAccounts;


}
