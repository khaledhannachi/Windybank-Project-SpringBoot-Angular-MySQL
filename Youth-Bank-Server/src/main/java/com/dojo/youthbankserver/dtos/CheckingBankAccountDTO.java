package com.dojo.youthbankserver.dtos;

import java.util.Date;

import com.dojo.youthbankserver.enums.AccountStatus;

import lombok.Data;

import static com.dojo.youthbankserver.enums.AccountStatus.CREATED;

@Data
public class CheckingBankAccountDTO extends BankAccountDTO {
	 private String id;
	    private double balance=0.00;
	    private Date createdAt;
	    private AccountStatus status;
	    private CustomerDTO customerDTO;
	    private double overDraft=0.00;
		
	    
	    
	    
	    
	    
	    
	    
	    

}
