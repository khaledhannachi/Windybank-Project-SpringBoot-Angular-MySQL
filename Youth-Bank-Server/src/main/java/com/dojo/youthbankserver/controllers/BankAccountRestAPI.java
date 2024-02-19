package com.dojo.youthbankserver.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dojo.youthbankserver.dtos.AccountHistoryDTO;
import com.dojo.youthbankserver.dtos.AccountOperationDTO;
import com.dojo.youthbankserver.dtos.BankAccountDTO;
import com.dojo.youthbankserver.dtos.CreditDTO;
import com.dojo.youthbankserver.dtos.DebitDTO;
import com.dojo.youthbankserver.dtos.TransferRequestDTO;
import com.dojo.youthbankserver.exceptions.BalanceNotSufficientException;
import com.dojo.youthbankserver.exceptions.BankAccountNotFoundException;
import com.dojo.youthbankserver.services.BankAcountServiceImpl;

import lombok.AllArgsConstructor;
@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/accounts")
public class BankAccountRestAPI {
	  private BankAcountServiceImpl bankAccountService;

	    public BankAccountRestAPI(BankAcountServiceImpl bankAccountService) {
	        this.bankAccountService = bankAccountService;
	    }

	    @GetMapping("/{accountId}")
	    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
	        return bankAccountService.getBankAccount(accountId);
	    }
	    @GetMapping("")
	    public List<BankAccountDTO> listAccounts(){
	        return bankAccountService.bankAccountList();
	    }
	    @GetMapping("/{accountId}/operations")
	    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
	        return bankAccountService.accountHistory(accountId);
	    }

	    @GetMapping("/{accountId}/pageOperations")
	    public AccountHistoryDTO getAccountHistory(
	            @PathVariable String accountId,
	            @RequestParam(name="page",defaultValue = "0") int page,
	            @RequestParam(name="size",defaultValue = "5")int size) throws BankAccountNotFoundException {
	        return bankAccountService.getAccountHistory(accountId,page,size);
	    }
	    @PostMapping("/debit")
	    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
	        this.bankAccountService.debit(debitDTO.getAccountId(),debitDTO.getAmount(),debitDTO.getDescription());
	        return debitDTO;
	    }
	    @PostMapping("/credit")
	    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
	        this.bankAccountService.credit(creditDTO.getAccountId(),creditDTO.getAmount(),creditDTO.getDescription());
	        return creditDTO;
	    }
	    @PostMapping("/transfer")
	    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
	        this.bankAccountService.transfer(
	                transferRequestDTO.getAccountSource(),
	                transferRequestDTO.getAccountDestination(),
	                transferRequestDTO.getAmount());
	    }
}
