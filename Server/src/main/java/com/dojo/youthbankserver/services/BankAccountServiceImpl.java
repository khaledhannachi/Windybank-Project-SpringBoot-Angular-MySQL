package com.dojo.youthbankserver.services;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dojo.youthbankserver.dtos.*;
import com.dojo.youthbankserver.entities.*;
import com.dojo.youthbankserver.enums.AccountStatus;
import com.dojo.youthbankserver.exceptions.*;
import com.dojo.youthbankserver.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dojo.youthbankserver.entities.Personal;
import com.dojo.youthbankserver.enums.OperationType;
import com.dojo.youthbankserver.mappers.BankAccountMapperImpl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService{


	    private BankAccountRepository bankAccountRepository;
	    private PersonalRepository personalRepository;
		private ProfessionalRepository professionalRepository;
		private BusinessRepository businessRepository;
	    private AccountOperationRepository accountOperationRepository;
	    private BankAccountMapperImpl dtoMapper;

//Checking Accounts



	    @Override
	    public CheckingBankAccountDTO saveCheckingPersonalBankAccount(double initialBalance, double overDraft, Long personalId) throws PersonalNotFoundException {
	        Personal personal = personalRepository.findById(personalId).orElse(null);
	        if(personal ==null)
	            throw new PersonalNotFoundException("Personal not found");
	        CheckingAccount checkingAccount=new CheckingAccount();
        int randomNumber = new Random().nextInt(90000000) + 10000000;
        String randomId = String.valueOf(randomNumber);

        checkingAccount.setId(randomId);
	        checkingAccount.setCreatedAt(new Date());
	        checkingAccount.setBalance(initialBalance);
	        checkingAccount.setOverDraft(overDraft);
	        checkingAccount.setPersonal(personal);
			checkingAccount.setStatus(AccountStatus.CREATED);
	        CheckingAccount savedBankAccount = bankAccountRepository.save(checkingAccount);
	        return dtoMapper.fromCheckingPersonalBankAccount(savedBankAccount);
	    }

	@Override
	public CheckingBankAccountDTO saveCheckingProfessionalBankAccount(double initialBalance, double overDraft, Long professionalId) throws ProfessionalNotFoundException {
		Professional professional = professionalRepository.findById(professionalId).orElse(null);
		if(professional ==null)
			throw new ProfessionalNotFoundException("Professional not found");
		CheckingAccount checkingAccount=new CheckingAccount();
    int randomNumber = new Random().nextInt(90000000) + 10000000;
    String randomId = String.valueOf(randomNumber);

    checkingAccount.setId(randomId);
		checkingAccount.setCreatedAt(new Date());
		checkingAccount.setBalance(initialBalance);
		checkingAccount.setOverDraft(overDraft);

		checkingAccount.setProfessional(professional);
		checkingAccount.setStatus(AccountStatus.CREATED);
		CheckingAccount savedBankAccount = bankAccountRepository.save(checkingAccount);
		return dtoMapper.fromCheckingProfessionalBankAccount(savedBankAccount);
	}
	@Override
	public CheckingBankAccountDTO saveCheckingBusinessBankAccount(double initialBalance, double overDraft, Long businessId) throws BusinessNotFoundException {
		Business business = businessRepository.findById(businessId).orElse(null);
		if(business ==null)
			throw new BusinessNotFoundException("Personal not found");
		CheckingAccount checkingAccount=new CheckingAccount();

    int randomNumber = new Random().nextInt(90000000) + 10000000;
    String randomId = String.valueOf(randomNumber);

    checkingAccount.setId(randomId);
		checkingAccount.setCreatedAt(new Date());
		checkingAccount.setBalance(initialBalance);
		checkingAccount.setOverDraft(overDraft);
		checkingAccount.setBusiness(business);
		checkingAccount.setStatus(AccountStatus.CREATED);
		CheckingAccount savedBankAccount = bankAccountRepository.save(checkingAccount);
		return dtoMapper.fromCheckingBusinessBankAccount(savedBankAccount);
	}

	//Saving Accounts

	@Override
	public SavingBankAccountDTO saveSavingPersonalBankAccount(double initialBalance, double interestRate, Long personalId) throws PersonalNotFoundException {
		Personal personal = personalRepository.findById(personalId).orElse(null);
		if(personal == null)
			throw new PersonalNotFoundException("Personal not found");

		SavingAccount savingAccount = new SavingAccount();

		int randomNumber = new Random().nextInt(90000000) + 10000000;
		String randomId = String.valueOf(randomNumber);

		savingAccount.setId(randomId);
		savingAccount.setCreatedAt(new Date());
		savingAccount.setBalance(initialBalance);
		savingAccount.setInterestRate(interestRate);
		savingAccount.setPersonal(personal);
		savingAccount.setStatus(AccountStatus.CREATED);

		SavingAccount savedBankAccount = bankAccountRepository.save(savingAccount);

		return dtoMapper.fromSavingPersonalBankAccount(savedBankAccount);
	}
	@Override
	public SavingBankAccountDTO saveSavingBusinessBankAccount(double initialBalance, double interestRate, Long businessId) throws BusinessNotFoundException {
		Business business = businessRepository .findById(businessId).orElse(null);
		if(business ==null)

			throw new BusinessNotFoundException("Business not found");
		SavingAccount savingAccount=new SavingAccount();
    int randomNumber = new Random().nextInt(90000000) + 10000000;
    String randomId = String.valueOf(randomNumber);

    savingAccount.setId(randomId);
		savingAccount.setCreatedAt(new Date());
		savingAccount.setBalance(initialBalance);
		savingAccount.setInterestRate(interestRate);
		savingAccount.setBusiness(business);
		savingAccount.setStatus(AccountStatus.CREATED);
		SavingAccount savedBankAccount = bankAccountRepository.save(savingAccount);

		// Log the savedBankAccount object to check if it's null
		System.out.println("Saved Bank Account: " + savedBankAccount);


		return dtoMapper.fromSavingBusinessBankAccount(savedBankAccount);
	}
	@Override
	public SavingBankAccountDTO saveSavingProfessionalBankAccount(double initialBalance, double interestRate, Long professionalId) throws ProfessionalNotFoundException {
		Professional professional = professionalRepository.findById(professionalId).orElse(null);
		if(professional ==null)

			throw new ProfessionalNotFoundException("Professional not found");
		SavingAccount savingAccount=new SavingAccount();

    int randomNumber = new Random().nextInt(90000000) + 10000000;
    String randomId = String.valueOf(randomNumber);

    savingAccount.setId(randomId);
		savingAccount.setCreatedAt(new Date());
		savingAccount.setBalance(initialBalance);
		savingAccount.setInterestRate(interestRate);
		savingAccount.setProfessional(professional);
		savingAccount.setStatus(AccountStatus.CREATED);
		SavingAccount savedBankAccount = bankAccountRepository.save(savingAccount);
		return dtoMapper.fromSavingProfessionalBankAccount(savedBankAccount);
	}


	    @Override
	    public BankAccountDTO getBusinessBankAccount(String accountId) throws BankAccountNotFoundException {
	        BankAccount bankAccount=bankAccountRepository.findById(accountId)
	                .orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
	        if(bankAccount instanceof SavingAccount){
	            SavingAccount savingAccount= (SavingAccount) bankAccount;
	            return dtoMapper.fromSavingBusinessBankAccount(savingAccount);
	        } else {
	            CheckingAccount checkingAccount= (CheckingAccount) bankAccount;
	            return dtoMapper.fromCheckingBusinessBankAccount(checkingAccount);
	        }
	    }
	@Override
	public BankAccountDTO getPersonalBankAccount(String accountId) throws BankAccountNotFoundException {
		BankAccount bankAccount=bankAccountRepository.findById(accountId)
				.orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
		if(bankAccount instanceof SavingAccount){
			SavingAccount savingAccount= (SavingAccount) bankAccount;
			return dtoMapper.fromSavingPersonalBankAccount(savingAccount);
		} else {
			CheckingAccount checkingAccount= (CheckingAccount) bankAccount;
			return dtoMapper.fromCheckingPersonalBankAccount(checkingAccount);
		}
	}
	@Override
	public BankAccountDTO getProfessionalBankAccount(String accountId) throws BankAccountNotFoundException {
		BankAccount bankAccount=bankAccountRepository.findById(accountId)
				.orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
		if(bankAccount instanceof SavingAccount){
			SavingAccount savingAccount= (SavingAccount) bankAccount;
			return dtoMapper.fromSavingProfessionalBankAccount(savingAccount);
		} else {
			CheckingAccount checkingAccount= (CheckingAccount) bankAccount;
			return dtoMapper.fromCheckingProfessionalBankAccount(checkingAccount);
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
		List<BankAccountDTO> bankAccountDTOS = bankAccounts.stream().flatMap(bankAccount -> {
			if (bankAccount instanceof SavingAccount) {
				SavingAccount savingAccount = (SavingAccount) bankAccount;
				return Stream.of(
						dtoMapper.fromSavingPersonalBankAccount(savingAccount),
						dtoMapper.fromSavingProfessionalBankAccount(savingAccount),
						dtoMapper.fromSavingBusinessBankAccount(savingAccount)
				);
			} else {
				CheckingAccount checkingAccount = (CheckingAccount) bankAccount;
				return Stream.of(
						dtoMapper.fromCheckingPersonalBankAccount(checkingAccount),
						dtoMapper.fromCheckingProfessionalBankAccount(checkingAccount),
						dtoMapper.fromCheckingBusinessBankAccount(checkingAccount)
				);
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
	@Override
	public BankAccountDTO activate(String BankAccountId)   {
		log.info("activate Business");

		BankAccount bankAccount=bankAccountRepository.findById(BankAccountId).orElseThrow(null);
		// Set the status of the provided bank account to ACTIVATED
		bankAccount.setStatus(AccountStatus.ACTIVATED);
		if(bankAccount instanceof SavingAccount){
			SavingAccount savingAccount= (SavingAccount) bankAccount;
			return dtoMapper.fromSavingProfessionalBankAccount(savingAccount);
		} else {
			CheckingAccount checkingAccount= (CheckingAccount) bankAccount;
			return dtoMapper.fromCheckingProfessionalBankAccount(checkingAccount);
		}
	}
	@Override
	public BankAccountDTO suspend( String BankAccountId )   {
		log.info("suspend Business");
		BankAccount bankAccount=bankAccountRepository.findById(BankAccountId).orElseThrow(null);
		// Set the status of the provided bank account to SUSPENDED
		bankAccount.setStatus(AccountStatus.SUSPENDED);
		if(bankAccount instanceof SavingAccount){
			SavingAccount savingAccount= (SavingAccount) bankAccount;
			return dtoMapper.fromSavingProfessionalBankAccount(savingAccount);
		} else {
			CheckingAccount checkingAccount= (CheckingAccount) bankAccount;
			return dtoMapper.fromCheckingProfessionalBankAccount(checkingAccount);
		}
	}
	@Override
	public BankAccountDTO delete(String BankAccountId)   {
		log.info("delete Business");
		BankAccount bankAccount=bankAccountRepository.findById(BankAccountId).orElseThrow(null);
		// Set the status of the provided bank account to DELETED
		bankAccount.setStatus(AccountStatus.DELETED);
		if(bankAccount instanceof SavingAccount){
			SavingAccount savingAccount= (SavingAccount) bankAccount;
			return dtoMapper.fromSavingProfessionalBankAccount(savingAccount);
		} else {
			CheckingAccount checkingAccount= (CheckingAccount) bankAccount;
			return dtoMapper.fromCheckingProfessionalBankAccount(checkingAccount);
		}
	}
}
