package com.dojo.youthbankserver.services;

import java.util.List;

import com.dojo.youthbankserver.dtos.AccountHistoryDTO;
import com.dojo.youthbankserver.dtos.AccountOperationDTO;
import com.dojo.youthbankserver.dtos.BankAccountDTO;
import com.dojo.youthbankserver.dtos.CheckingBankAccountDTO;
import com.dojo.youthbankserver.dtos.SavingBankAccountDTO;
import com.dojo.youthbankserver.exceptions.*;

public interface BankAccountService {

	 


	//
	CheckingBankAccountDTO saveCheckingPersonalBankAccount(double initialBalance, double overDraft, Long personalId) throws PersonalNotFoundException;

	CheckingBankAccountDTO saveCheckingProfessionalBankAccount(double initialBalance, double overDraft, Long personalId) throws PersonalNotFoundException, ProfessionalNotFoundException;

	CheckingBankAccountDTO saveCheckingBusinessBankAccount(double initialBalance, double overDraft, Long businessId) throws BusinessNotFoundException;

	//Saving Accounts
	SavingBankAccountDTO saveSavingPersonalBankAccount(double initialBalance, double interestRate, Long personalId) throws PersonalNotFoundException;


	SavingBankAccountDTO saveSavingBusinessBankAccount(double initialBalance, double interestRate, Long businessId) throws BusinessNotFoundException;

	SavingBankAccountDTO saveSavingProfessionalBankAccount(double initialBalance, double interestRate, Long professionalId) throws ProfessionalNotFoundException;

	BankAccountDTO getBusinessBankAccount(String accountId) throws BankAccountNotFoundException;
	BankAccountDTO getPersonalBankAccount(String accountId) throws BankAccountNotFoundException;
	BankAccountDTO getProfessionalBankAccount(String accountId) throws BankAccountNotFoundException;
	    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
	    
	    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
	    
	    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

	    List<BankAccountDTO> bankAccountList();

	    List<AccountOperationDTO> accountHistory(String accountId);

	    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

    BankAccountDTO activate(String BankAccountId);

	BankAccountDTO suspend(String BankAccountId);

    BankAccountDTO delete(String BankAccountId);
}
