package com.dojo.youthbankserver.entities;

import java.util.Date;

import com.dojo.youthbankserver.enums.OperationType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AccountOperation {
	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private Date operationDate;
	    private double amount;
	    @Enumerated(EnumType.STRING)
	    private OperationType type;
	    @ManyToOne
	    private BankAccount bankAccount;
	    private String description;
}
