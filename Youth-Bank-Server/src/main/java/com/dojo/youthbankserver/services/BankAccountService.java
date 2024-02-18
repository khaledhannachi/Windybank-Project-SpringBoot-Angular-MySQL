package com.dojo.youthbankserver.services;

import java.util.List;

import com.dojo.youthbankserver.dtos.BankAccountDTO;
import com.dojo.youthbankserver.dtos.CheckingBankAccountDTO;
import com.dojo.youthbankserver.dtos.SavingBankAccountDTO;
import com.dojo.youthbankserver.exceptions.BalanceNotSufficientException;
import com.dojo.youthbankserver.exceptions.BankAccountNotFoundException;
import com.dojo.youthbankserver.exceptions.CustomerNotFoundException;

public interface BankAccountService {

	 
	    CheckingBankAccountDTO saveCheckingBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
	    
	    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
	   
	    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
	    
	    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
	    
	    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
	    
	    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

	    List<BankAccountDTO> bankAccountList();


	
}
