
package com.dojo.youthbankserver.controllers;

import java.util.List;

import com.dojo.youthbankserver.dtos.*;
import com.dojo.youthbankserver.exceptions.PersonalNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dojo.youthbankserver.exceptions.BalanceNotSufficientException;
import com.dojo.youthbankserver.exceptions.BankAccountNotFoundException;
import com.dojo.youthbankserver.services.BankAccountService;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class BankAccountController {

    private BankAccountService bankAccountService;


    @GetMapping("/{accountId}")
    public ResponseEntity<BankAccountDTO> getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return ResponseEntity.ok().body(bankAccountService.getBankAccount(accountId));
    }

    @GetMapping("")
    public ResponseEntity<List<BankAccountDTO>> listAccounts(){
        return ResponseEntity.ok().body(bankAccountService.bankAccountList());
    }
    @PostMapping("/checking/{personalId}")
    public ResponseEntity<CheckingBankAccountDTO> saveCheckingBankAccountDTO( CheckingBankAccountDTO checkingBankAccountDTO ,@PathVariable Long personalId  ) throws PersonalNotFoundException {
        double initialBalance=checkingBankAccountDTO.getBalance();
        double overDraft=checkingBankAccountDTO.getOverDraft();
        return ResponseEntity.ok().body(bankAccountService.saveCheckingBankAccount(initialBalance,overDraft,personalId));
    }
    @PostMapping("/saving/{personalId}")
    public ResponseEntity<SavingBankAccountDTO> saveSavingBankAccountDTO( SavingBankAccountDTO savingBankAccountDTO ,@PathVariable Long personalId  ) throws PersonalNotFoundException {
        double initialBalance=savingBankAccountDTO.getBalance();
        double interestRate=savingBankAccountDTO.getInterestRate();
        return ResponseEntity.ok().body(bankAccountService.saveSavingBankAccount(initialBalance,interestRate,personalId));
    }

<<<<<<< HEAD:Youth-Bank-Server/src/main/java/com/dojo/youthbankserver/controllers/BankAccountRestAPI.java
=======

>>>>>>> e5ffc088f15e3d2b775cb8a606b4e706530aff0e:Youth-Bank-Server/src/main/java/com/dojo/youthbankserver/controllers/BankAccountController.java
    @GetMapping("/{accountId}/operations")
    public ResponseEntity<List<AccountOperationDTO>> getHistory(@PathVariable String accountId){
        return ResponseEntity.ok().body(bankAccountService.accountHistory(accountId));
    }

    @GetMapping("/{accountId}/pageOperations")
    public ResponseEntity<AccountHistoryDTO> getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name="page",defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "5")int size) throws BankAccountNotFoundException {
        return ResponseEntity.ok().body(bankAccountService.getAccountHistory(accountId,page,size));
    }
    @PostMapping("/debit")
    public ResponseEntity<DebitDTO> debit(@Valid @RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.debit(debitDTO.getAccountId(),debitDTO.getAmount(),debitDTO.getDescription());
        return ResponseEntity.ok().body(debitDTO);
    }
    @PostMapping("/credit")
    public ResponseEntity<CreditDTO> credit(@Valid @RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
        this.bankAccountService.credit(creditDTO.getAccountId(),creditDTO.getAmount(),creditDTO.getDescription());
        return ResponseEntity.ok().body(creditDTO);
    }
    @PostMapping("/transfer")
    public void transfer(@Valid @RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.transfer(
                transferRequestDTO.getAccountSource(),
                transferRequestDTO.getAccountDestination(),
                transferRequestDTO.getAmount());
    }
}
