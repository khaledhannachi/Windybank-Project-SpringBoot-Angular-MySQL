package com.dojo.youthbankserver.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@NoArgsConstructor @AllArgsConstructor
public class CheckingAccount extends BankAccount{
	private double overDraft;

	
	
	
}
