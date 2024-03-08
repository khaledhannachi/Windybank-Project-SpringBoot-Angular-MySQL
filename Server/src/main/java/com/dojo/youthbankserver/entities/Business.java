package com.dojo.youthbankserver.entities;

import java.util.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


@NoArgsConstructor @AllArgsConstructor
@Entity
@Data
public class Business {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
//    @NotBlank(message="Company Legal Name is required!")
//    @Size(min=3, max=30, message="Legal Name must be between 3 and 30 characters.")
    private String companylegalName;
    
//    @NotBlank(message="Commercial Name is required!")
//    @Size(min=3, max=30, message="Company Name must be between 3 and 30 characters.")
    private String commercialName;
    
//    @NotBlank(message="Activity is required!")
//    @Size(min=3, max=30, message="Activity must be between 3 and 30 characters.")
    private String activity;
    
//    @NotBlank(message="Activity is required!")
//    @Size(min=3, max=30, message="Activity must be between 3 and 30 characters.")
    private String sectorOfActivity;
    

//    @NotBlank
//    @NotEmpty(message="Tax Identification Number is required!")
//    @Size(min=3, max=30, message="Tax Identification Number must be between 3 and 30 characters.")
    private String taxIdentificationNumber;
    
//    @NotBlank
//    @NotEmpty(message="Business registration Number is required!")
//    @Size(min=3, max=30, message="Business registration Number must be between 3 and 30 characters.")
    private String businessRegistration;
    
    
//    @NotBlank
//    @NotEmpty(message="CNSS Number is required!")
//    @Size(min=3, max=30, message="CNSS Number must be between 3 and 30 characters.")
    private String cnssNumber;

//	@NotBlank(message="Company Nationality is required!")
//	@Size(min=1, max=60, message="Company Nationality must be between 3 and 60 characters.")
	private String companyNationality;
 
//    @Past
//   	@DateTimeFormat(pattern="yyyy-MM-dd")
   	private Date creationDate;
    
//    @Past
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date activityStartDate;
    

    
//    chiffre
    
//	@NotEmpty(message="Net Pay is required!")
	private double  turnoverLatestFinancialYear;

//	@NotEmpty(message="Net Pay is required!")
	private double  resultOfTheLastFinancialYear;

// !!!!!!!!!!!!
//
//	@NotBlank(message="Street is required!")
//	@Size(min=2, max=60, message="Street must be between 2 and 60 characters.")
	private String companyStreet;
//
//	@NotBlank(message="City is required!")
//	@Size(min=1, max=60, message="Street must be between 3 and 60 characters.")
	private String companyCity;

	
//	@Range(min = 4, max= 4, message = "Postal Code should be exact 4 characters." )
	private int companyPostalCode;
	

//    @Pattern(regexp = "(\\+216|0216)[0-9]{8}")
	private String companyPhone;
    
    
//    @Pattern(regexp = "(\\+216|0216)[0-9]{8}")
    private String companyEmail;


    
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
	  @OneToMany(mappedBy = "business", fetch = FetchType.LAZY)
	  private List<BankAccount> businessBankAccounts;

		//	Getter and setter


	   //many to one
	    @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="user_id")
	    private User businessLegalResponsible;

	@OneToMany(mappedBy = "businessInvoices", fetch = FetchType.LAZY)
	private List<Invoice> invoices;

	@OneToMany(mappedBy = "businessProducts", fetch = FetchType.LAZY)
	private List<Product> products;
	  
	
}
