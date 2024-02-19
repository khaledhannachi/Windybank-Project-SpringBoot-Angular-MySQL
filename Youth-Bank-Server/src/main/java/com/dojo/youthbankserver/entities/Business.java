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


@NoArgsConstructor @AllArgsConstructor
@Entity

public class Business {

    @NotEmpty(message="Password is required!")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;
    
    
    @NotBlank
	@NotEmpty(message="Email is required!")
	@Email(message="Please enter a valid email!")
	private String email;
    
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @NotBlank(message="Company Legal Name is required!")
    @Size(min=3, max=30, message="Legal Name must be between 3 and 30 characters.")
    private String companylegalName;
    
    @NotBlank(message="Commercial Name is required!")
    @Size(min=3, max=30, message="Company Name must be between 3 and 30 characters.")
    private String commercialName;
    
    @NotBlank(message="Activity is required!")
    @Size(min=3, max=30, message="Activity must be between 3 and 30 characters.")
    private String activity;
    
    @NotBlank(message="Activity is required!")
    @Size(min=3, max=30, message="Activity must be between 3 and 30 characters.")
    private String sectorOfActivity;
    

    @NotBlank
    @NotEmpty(message="Tax Identification Number is required!")
    @Size(min=3, max=30, message="Tax Identification Number must be between 3 and 30 characters.")
    private String taxIdentificationNumber;
    
    @NotBlank
    @NotEmpty(message="Business registration Number is required!")
    @Size(min=3, max=30, message="Business registration Number must be between 3 and 30 characters.")
    private String businessRegistration;
    
    
    @NotBlank
    @NotEmpty(message="CNSS Number is required!")
    @Size(min=3, max=30, message="CNSS Number must be between 3 and 30 characters.")
    private String cnssNumber;

	@NotBlank(message="Company Nationality is required!")
	@Size(min=1, max=60, message="Company Nationality must be between 3 and 60 characters.")
	private String companyNationality;
 
    @Past
   	@DateTimeFormat(pattern="yyyy-MM-dd")
   	private Date creationDate;
    
    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date activityStartDate;
    

    
//    chiffre
    
	@NotEmpty(message="Net Pay is required!")
	private double  turnoverLatestFinancialYear;

	@NotEmpty(message="Net Pay is required!")
	private double  resultOfTheLastFinancialYear;

// !!!!!!!!!!!!
    
	@NotBlank(message="Street is required!")
	@Size(min=2, max=60, message="Street must be between 2 and 60 characters.")
	private String companyStreet;
	
	@NotBlank(message="City is required!")
	@Size(min=1, max=60, message="Street must be between 3 and 60 characters.")
	private String companyCity;

	
	@Range(min = 4, max= 4, message = "Postal Code should be exact 4 characters." )
	private int companyPostalCode;
	

    @Pattern(regexp = "(\\+216|0216)[0-9]{8}")
	private String companyPhone;
    
    
    @Pattern(regexp = "(\\+216|0216)[0-9]{8}")
    private String companyEmail;


    
//    Legal Name																																																																			


	
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
	

	@NotBlank(message="Professional Status is required!")
	@Size(min=1, max=60, message="Professional Status must be between 1 and 60 characters.")
	private String professionalStatus;
 
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
	  @OneToMany(mappedBy = "business", fetch = FetchType.LAZY)
	  private List<BankAccount> businessBankAccounts;

		//	Getter and setter
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanylegalName() {
		return companylegalName;
	}

	public void setCompanylegalName(String companylegalName) {
		this.companylegalName = companylegalName;
	}

	public String getCommercialName() {
		return commercialName;
	}

	public void setCommercialName(String commercialName) {
		this.commercialName = commercialName;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getSectorOfActivity() {
		return sectorOfActivity;
	}

	public void setSectorOfActivity(String sectorOfActivity) {
		this.sectorOfActivity = sectorOfActivity;
	}

	public String getTaxIdentificationNumber() {
		return taxIdentificationNumber;
	}

	public void setTaxIdentificationNumber(String taxIdentificationNumber) {
		this.taxIdentificationNumber = taxIdentificationNumber;
	}

	public String getBusinessRegistration() {
		return businessRegistration;
	}

	public void setBusinessRegistration(String businessRegistration) {
		this.businessRegistration = businessRegistration;
	}

	public String getCnssNumber() {
		return cnssNumber;
	}

	public void setCnssNumber(String cnssNumber) {
		this.cnssNumber = cnssNumber;
	}

	public String getCompanyNationality() {
		return companyNationality;
	}

	public void setCompanyNationality(String companyNationality) {
		this.companyNationality = companyNationality;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getActivityStartDate() {
		return activityStartDate;
	}

	public void setActivityStartDate(Date activityStartDate) {
		this.activityStartDate = activityStartDate;
	}

	public double getTurnoverLatestFinancialYear() {
		return turnoverLatestFinancialYear;
	}

	public void setTurnoverLatestFinancialYear(double turnoverLatestFinancialYear) {
		this.turnoverLatestFinancialYear = turnoverLatestFinancialYear;
	}

	public double getResultOfTheLastFinancialYear() {
		return resultOfTheLastFinancialYear;
	}

	public void setResultOfTheLastFinancialYear(double resultOfTheLastFinancialYear) {
		this.resultOfTheLastFinancialYear = resultOfTheLastFinancialYear;
	}

	public String getCompanyStreet() {
		return companyStreet;
	}

	public void setCompanyStreet(String companyStreet) {
		this.companyStreet = companyStreet;
	}

	public String getCompanyCity() {
		return companyCity;
	}

	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}

	public int getCompanyPostalCode() {
		return companyPostalCode;
	}

	public void setCompanyPostalCode(int companyPostalCode) {
		this.companyPostalCode = companyPostalCode;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getCountryOfBirth() {
		return countryOfBirth;
	}

	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountryOfResidence() {
		return countryOfResidence;
	}

	public void setCountryOfResidence(String countryOfResidence) {
		this.countryOfResidence = countryOfResidence;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	public Date getIdDeliveryDate() {
		return idDeliveryDate;
	}

	public void setIdDeliveryDate(Date idDeliveryDate) {
		this.idDeliveryDate = idDeliveryDate;
	}

	public String getIdDeliveryCity() {
		return idDeliveryCity;
	}

	public void setIdDeliveryCity(String idDeliveryCity) {
		this.idDeliveryCity = idDeliveryCity;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getProfessionalStatus() {
		return professionalStatus;
	}

	public void setProfessionalStatus(String professionalStatus) {
		this.professionalStatus = professionalStatus;
	}

	public double getNetPay() {
		return netPay;
	}

	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<BankAccount> getBusinessBankAccounts() {
		return businessBankAccounts;
	}

	public void setBusinessBankAccounts(List<BankAccount> businessBankAccounts) {
		this.businessBankAccounts = businessBankAccounts;
	}
	
	
	  
	  
	  
	  
	
}
