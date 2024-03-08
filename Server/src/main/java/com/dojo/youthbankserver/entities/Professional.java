package com.dojo.youthbankserver.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Entity
@Data
public class Professional {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
//    @NotBlank
//    @NotEmpty(message="Tax Identification Number is required!")
//    @Size(min=3, max=30, message="Tax Identification Number must be between 3 and 30 characters.")
    private String taxIdentificationNumber;

//    @NotBlank
//    @NotEmpty(message="Business registration Number is required!")
//    @Size(min=3, max=30, message="Business registration Number must be between 3 and 30 characters.")
    private String businessRegistration;


//	@NotBlank(message="Profession is required!")
//	@Size(min=1, max=60, message="Profession must be between 3 and 60 characters.")
	private String profession;

 
//	@NotBlank(message="company Name is required!")
//	@Size(min=1, max=60, message="company Name must be between 1 and 60 characters.")
	private String companyName;
 
//	@NotBlank(message="company Adress is required!")
//	@Size(min=1, max=60, message="company Adress must be between 1 and 60 characters.")
	private String companyAdress;

//	@NotBlank
//	@NotEmpty(message="CNSS Number is required!")
//	@Size(min=3, max=30, message="CNSS Number must be between 3 and 30 characters.")
	private String cnssNumber;

	// This will not allow the createdAt column to be updated after creation
//	@Column(updatable = false)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
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



//	  //many to one
	    @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="user_id")
	    private User userProfessional;
	
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
}
