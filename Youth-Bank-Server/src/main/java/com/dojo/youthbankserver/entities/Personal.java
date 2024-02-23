 package com.dojo.youthbankserver.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Range;
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
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data 
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Personal {


		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

//		@NotBlank(message="idType is required!")
		private String idType;
		
//		@Range(min = 10, max= 10, message = "Id number should be exact 10 characters." )
		private int idNumber;

//		@Past
//		@DateTimeFormat(pattern="yyyy-MM-dd")
		private Date idDeliveryDate;
		
//		@NotBlank(message="City is required!")
//		@Size(min=1, max=60, message="City must be between 3 and 60 characters.")
		private String idDeliveryCity;
		

//		@NotBlank(message="Profession is required!")
//		@Size(min=1, max=60, message="Profession must be between 3 and 60 characters.")
		private String profession;

//		@NotBlank(message="Employer Name is required!")
//		@Size(min=1, max=60, message="Employer Name must be between 1 and 60 characters.")
		private String employerName;

		
//		@NotBlank(message="Employer Adress is required!")
//		@Size(min=1, max=60, message="Employer Adress must be between 1 and 60 characters.")
		private String employerAdress;

		
//		@NotEmpty(message="Net Pay is required!")
		private double netPay;


		// This will not allow the createdAt column to be updated after creation
		@Column(updatable = false)
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date createdAt;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date updatedAt;

		
//		----- methods ---

		@PrePersist
		protected void onCreate() {
			this.createdAt = new Date();
		}
		
		@PreUpdate
		protected void onUpdate() {
			this.updatedAt = new Date();
		}

//		one to many
		  @OneToMany(mappedBy = "personal", fetch = FetchType.LAZY)
		  private List<BankAccount> personalBankAccounts;

		  
//		  //many to one


@ManyToOne(fetch=FetchType.LAZY)
		    @JoinColumn(name="user_id")
		    private User userPersonal;
	
	
		  
		  
		  
		  
	
}
