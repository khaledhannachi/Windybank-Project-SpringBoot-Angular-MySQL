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
@NoArgsConstructor @AllArgsConstructor @Data
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


	    
		
	    
	    
}
