package com.dojo.youthbankserver.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dojo.youthbankserver.dtos.BankAccountDTO;
import com.dojo.youthbankserver.dtos.CheckingBankAccountDTO;
import com.dojo.youthbankserver.dtos.SavingBankAccountDTO;
import com.dojo.youthbankserver.entities.AccountOperation;
import com.dojo.youthbankserver.entities.BankAccount;
import com.dojo.youthbankserver.entities.CheckingAccount;
import com.dojo.youthbankserver.entities.Customer;
import com.dojo.youthbankserver.entities.SavingAccount;
import com.dojo.youthbankserver.enums.OperationType;
import com.dojo.youthbankserver.exceptions.BalanceNotSufficientException;
import com.dojo.youthbankserver.exceptions.BankAccountNotFoundException;
import com.dojo.youthbankserver.exceptions.CustomerNotFoundException;
import com.dojo.youthbankserver.mappers.BankAccountMapperImpl;
import com.dojo.youthbankserver.repositories.AccountOperationRepository;
import com.dojo.youthbankserver.repositories.BankAccountRepository;
import com.dojo.youthbankserver.repositories.CustomerRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Transactional
@AllArgsConstructor

public class BankAcountServiceImpl implements BankAccountService{

	
	
	    private BankAccountRepository bankAccountRepository;
	    private CustomerRepository customerRepository;
	    private AccountOperationRepository accountOperationRepository;
	    private BankAccountMapperImpl dtoMapper;

	   

	    @Override
	    public CheckingBankAccountDTO saveCheckingBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
	        Customer customer=customerRepository.findById(customerId).orElse(null);
	        if(customer==null)
	            throw new CustomerNotFoundException("Customer not found");
	        CheckingAccount checkingAccount=new CheckingAccount();
	        checkingAccount.setId(UUID.randomUUID().toString());
	        checkingAccount.setCreatedAt(new Date());
	        checkingAccount.setBalance(initialBalance);
	        checkingAccount.setOverDraft(overDraft);
	        checkingAccount.setCustomer(customer);
	       
	        CheckingAccount savedBankAccount = bankAccountRepository.save(checkingAccount);
	        return dtoMapper.fromCheckingBankAccount(savedBankAccount);
	    }

	    @Override
	    public SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
	        Customer customer=customerRepository.findById(customerId).orElse(null);
	        if(customer==null)
	        	
	            throw new CustomerNotFoundException("Customer not found");
	        SavingAccount savingAccount=new SavingAccount();
	        savingAccount.setId(UUID.randomUUID().toString());
	        savingAccount.setCreatedAt(new Date());
	        savingAccount.setBalance(initialBalance);
	        savingAccount.setInterestRate(interestRate);
	        savingAccount.setCustomer(customer);
	        
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
	   

	   
	  
	  

	  

	  
}
