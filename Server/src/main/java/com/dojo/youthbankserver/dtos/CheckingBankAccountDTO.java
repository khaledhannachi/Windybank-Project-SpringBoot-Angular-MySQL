package com.dojo.youthbankserver.dtos;

import java.util.Date;

import com.dojo.youthbankserver.enums.AccountStatus;

import lombok.Data;

@Data
public class CheckingBankAccountDTO extends BankAccountDTO {
	 private String id;
	    private double balance=0.00;
	    private Date createdAt;
//	    private AccountStatus status;
//	    private PersonalDTO personalDTO;
//		private BusinessDTO businessDTO;
//		private ProfessionalDTO professionalDTO;
	    private double overDraft=0.00;
		

}
