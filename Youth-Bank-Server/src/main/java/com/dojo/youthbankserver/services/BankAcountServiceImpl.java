package com.dojo.youthbankserver.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dojo.youthbankserver.entities.*;
import com.dojo.youthbankserver.repositories.PersonalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dojo.youthbankserver.dtos.AccountHistoryDTO;
import com.dojo.youthbankserver.dtos.AccountOperationDTO;
import com.dojo.youthbankserver.dtos.BankAccountDTO;
import com.dojo.youthbankserver.dtos.CheckingBankAccountDTO;
import com.dojo.youthbankserver.dtos.SavingBankAccountDTO;
import com.dojo.youthbankserver.entities.Personal;
import com.dojo.youthbankserver.enums.OperationType;
import com.dojo.youthbankserver.exceptions.BalanceNotSufficientException;
import com.dojo.youthbankserver.exceptions.BankAccountNotFoundException;
import com.dojo.youthbankserver.exceptions.PersonalNotFoundException;
import com.dojo.youthbankserver.mappers.BankAccountMapperImpl;
import com.dojo.youthbankserver.repositories.AccountOperationRepository;
import com.dojo.youthbankserver.repositories.BankAccountRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class BankAcountServiceImpl implements BankAccountService{


	    private BankAccountRepository bankAccountRepository;
	    private PersonalRepository personalRepository;
	    private AccountOperationRepository accountOperationRepository;
	    private BankAccountMapperImpl dtoMapper;


	    @Override
	    public CheckingBankAccountDTO saveCheckingBankAccount(double initialBalance, double overDraft, Long personalId) throws PersonalNotFoundException {
	        Personal personal = personalRepository.findById(personalId).orElse(null);
	        if(personal ==null)
	            throw new PersonalNotFoundException("Personal not found");
	        CheckingAccount checkingAccount=new CheckingAccount();
	        checkingAccount.setId(UUID.randomUUID().toString());
	        checkingAccount.setCreatedAt(new Date());
	        checkingAccount.setBalance(initialBalance);
	        checkingAccount.setOverDraft(overDraft);
			
	        checkingAccount.setPersonal(personal);
	        CheckingAccount savedBankAccount = bankAccountRepository.save(checkingAccount);
	        return dtoMapper.fromCheckingBankAccount(savedBankAccount);
	    }

	    @Override
	    public SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long personalId) throws PersonalNotFoundException {
	        Personal personal = personalRepository.findById(personalId).orElse(null);
	        if(personal ==null)
	        	
	            throw new PersonalNotFoundException("Personal not found");
	        SavingAccount savingAccount=new SavingAccount();
	        savingAccount.setId(UUID.randomUUID().toString());
	        savingAccount.setCreatedAt(new Date());
	        savingAccount.setBalance(initialBalance);
	        savingAccount.setInterestRate(interestRate);
	        savingAccount.setPersonal(personal);
	        
	        SavingAccount savedBankAccount = bankAccountRepository.save(savingAccount);
	        return dtoMapper.fromSavingBankAccount(savedBankAccount);
	    }


	    @Override
	    public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException {
	        BankAccount bankAccount=bankAccountRepository.findById(accountId)
	                .orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
	        if(bankAccount instanceof SavingAccount){
	            SavingAccount savingAccount= (SavingAccount) bankAccount;
	            return dtoMapper.fromSavingBankAccount(savingAccount);
	        } else {
	            CheckingAccount checkingAccount= (CheckingAccount) bankAccount;
	            return dtoMapper.fromCheckingBankAccount(checkingAccount);
	        }
	    }

	    @Override
	    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
	        BankAccount bankAccount=bankAccountRepository.findById(accountId)
	                .orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
	        if(bankAccount.getBalance()<amount)
	            throw new BalanceNotSufficientException("Balance not sufficient");
	        AccountOperation accountOperation=new AccountOperation();
	        accountOperation.setType(OperationType.DEBIT);
	        accountOperation.setAmount(amount);
	        accountOperation.setDescription(description);
	        accountOperation.setOperationDate(new Date());
	        accountOperation.setBankAccount(bankAccount);
	        accountOperationRepository.save(accountOperation);
	        bankAccount.setBalance(bankAccount.getBalance()-amount);
	        bankAccountRepository.save(bankAccount);
	    }

	    @Override
	    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
	        BankAccount bankAccount=bankAccountRepository.findById(accountId)
	                .orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
	        AccountOperation accountOperation=new AccountOperation();
	        accountOperation.setType(OperationType.CREDIT);
	        accountOperation.setAmount(amount);
	        accountOperation.setDescription(description);
	        accountOperation.setOperationDate(new Date());
	        accountOperation.setBankAccount(bankAccount);
	        accountOperationRepository.save(accountOperation);
	        bankAccount.setBalance(bankAccount.getBalance()+amount);
	        bankAccountRepository.save(bankAccount);
	    }

	    @Override
	    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
	        debit(accountIdSource,amount,"Transfer to "+accountIdDestination);
	        credit(accountIdDestination,amount,"Transfer from "+accountIdSource);
	    }
	    @Override
	    public List<BankAccountDTO> bankAccountList(){
	        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
	        List<BankAccountDTO> bankAccountDTOS = bankAccounts.stream().map(bankAccount -> {
	            if (bankAccount instanceof SavingAccount) {
	                SavingAccount savingAccount = (SavingAccount) bankAccount;
	                return dtoMapper.fromSavingBankAccount(savingAccount);
	            } else {
	                CheckingAccount checkingAccount = (CheckingAccount) bankAccount;
	                return dtoMapper.fromCheckingBankAccount(checkingAccount);
	            }
	        }).collect(Collectors.toList());
	        return bankAccountDTOS;
	    }
	   
	    @Override
	    public List<AccountOperationDTO> accountHistory(String accountId){
	        List<AccountOperation> accountOperations = accountOperationRepository.findByBankAccountId(accountId);
	        return accountOperations.stream().map(op->dtoMapper.fromAccountOperation(op)).collect(Collectors.toList());
	    }

	    @Override
	    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException {
	        BankAccount bankAccount=bankAccountRepository.findById(accountId).orElse(null);
	        if(bankAccount==null) throw new BankAccountNotFoundException("Account not Found");
	        Page<AccountOperation> accountOperations = accountOperationRepository.findByBankAccountIdOrderByOperationDateDesc(accountId, PageRequest.of(page, size));
	        AccountHistoryDTO accountHistoryDTO=new AccountHistoryDTO();
	        List<AccountOperationDTO> accountOperationDTOS = accountOperations.getContent().stream().map(op -> dtoMapper.fromAccountOperation(op)).collect(Collectors.toList());
	        accountHistoryDTO.setAccountOperationDTOS(accountOperationDTOS);
	        accountHistoryDTO.setAccountId(bankAccount.getId());
	        accountHistoryDTO.setBalance(bankAccount.getBalance());
	        accountHistoryDTO.setCurrentPage(page);
	        accountHistoryDTO.setPageSize(size);
	        accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());
	        return accountHistoryDTO;
	    }
	   
	  
	  

	  

	  
}
