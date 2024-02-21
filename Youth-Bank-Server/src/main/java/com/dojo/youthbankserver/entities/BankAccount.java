package com.dojo.youthbankserver.entities;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.dojo.youthbankserver.enums.AccountStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "TYPE",length = 4).
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class BankAccount {

		@Id
	    private String id;
	    private double balance;
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date createdAt;
	    @Enumerated(EnumType.STRING)
	    private AccountStatus status;
	    
	    
	    //many to one
	    @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="personal_id")
	    private Personal personal;
	    
	    //many to one
	    @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="business_id")
	    private Business business;
	    
	    //many to one
	    @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="professional_id")
	    private Professional professional;

	    
        //one to many
	    @OneToMany(mappedBy = "bankAccount",fetch = FetchType.LAZY)
	    private List<AccountOperation> accountOperations;
	    
	    
	    //----- methods ---

		@PrePersist
		protected void onCreate() {
			this.createdAt = new Date();
		}
		
//		//	zero constructor
//		
//		public BankAccount() {
//			
//		}
//		
//	    //----- getter setter ---
//
//		public String getId() {
//			return id;
//		}
//
//
//
//		public void setId(String id) {
//			this.id = id;
//		}
//
//
//		public double getBalance() {
//			return balance;
//		}
//
//
//		public void setBalance(double balance) {
//			this.balance = balance;
//		}
//
//
//		public Date getCreatedAt() {
//			return createdAt;
//		}
//
//
//		public void setCreatedAt(Date createdAt) {
//			this.createdAt = createdAt;
//		}
//
//
//		public AccountStatus getStatus() {
//			return status;
//		}
//
//
//		public void setStatus(AccountStatus status) {
//			this.status = status;
//		}
//
//
//		public Personal getPersonal() {
//			return personal;
//		}
//
//
//		public void setPersonal(Personal personal) {
//			this.personal = personal;
//		}
//
//
//		public Business getBusiness() {
//			return business;
//		}
//
//
//		public void setBusiness(Business business) {
//			this.business = business;
//		}
//
//
//		public Professional getProfessional() {
//			return professional;
//		}
//
//
//		public void setProfessional(Professional professional) {
//			this.professional = professional;
//		}
//
//
//		public List<AccountOperation> getAccountOperations() {
//			return accountOperations;
//		}
//
//
//		public void setAccountOperations(List<AccountOperation> accountOperations) {
//			this.accountOperations = accountOperations;
//		}




		
		
		
}
