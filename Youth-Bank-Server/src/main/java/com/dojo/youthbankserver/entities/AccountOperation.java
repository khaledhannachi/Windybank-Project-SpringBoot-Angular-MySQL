package com.dojo.youthbankserver.entities;

import java.util.Date;


import com.dojo.youthbankserver.enums.OperationType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor @AllArgsConstructor
public class AccountOperation {
	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private Date operationDate;
	    private double amount;
	    @Enumerated(EnumType.STRING)
	    private OperationType type;
	    private String description;

	    //many to one
	    @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="bankAccount_id")
	    private BankAccount bankAccount;

	    //getter and setter
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date getOperationDate() {
			return operationDate;
		}

		public void setOperationDate(Date operationDate) {
			this.operationDate = operationDate;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public OperationType getType() {
			return type;
		}

		public void setType(OperationType type) {
			this.type = type;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public BankAccount getBankAccount() {
			return bankAccount;
		}

		public void setBankAccount(BankAccount bankAccount) {
			this.bankAccount = bankAccount;
		}
	    
		
	    
	    
}
