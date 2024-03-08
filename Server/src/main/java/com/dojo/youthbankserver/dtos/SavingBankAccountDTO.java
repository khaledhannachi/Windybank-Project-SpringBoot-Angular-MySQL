package com.dojo.youthbankserver.dtos;

import java.util.Date;

import com.dojo.youthbankserver.enums.AccountStatus;

import lombok.Data;

@Data
public class SavingBankAccountDTO extends BankAccountDTO {

	
	  	private String id;
	    private double balance=1000;
	    private Date createdAt;
//	    private AccountStatus status;
//	    private PersonalDTO personalDTO;
//		private BusinessDTO businessDTO;
//		private ProfessionalDTO professionalDTO;
	    private double interestRate=6.0;

}
