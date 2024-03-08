
package com.dojo.youthbankserver.controllers;

import java.util.List;

import com.dojo.youthbankserver.dtos.*;
import com.dojo.youthbankserver.entities.BankAccount;
import com.dojo.youthbankserver.exceptions.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dojo.youthbankserver.services.BankAccountService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class BankAccountController {
    private BankAccountService bankAccountService;

    @GetMapping("/business/{accountId}")
    public ResponseEntity<BankAccountDTO> getBusinessBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return ResponseEntity.ok().body(bankAccountService.getBusinessBankAccount(accountId));
    }
    @GetMapping("/personal/{accountId}")
    public ResponseEntity<BankAccountDTO> getPersonalBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return ResponseEntity.ok().body(bankAccountService.getPersonalBankAccount(accountId));
    }
    @GetMapping("/professional/{accountId}")
    public ResponseEntity<BankAccountDTO> getProfessionalBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return ResponseEntity.ok().body(bankAccountService.getProfessionalBankAccount(accountId));
    }
    @GetMapping("")
    public ResponseEntity<List<BankAccountDTO>> listAccounts(){
        return ResponseEntity.ok().body(bankAccountService.bankAccountList());
    }


    //    Checking Accounts
    @PostMapping("/checking/personal/{personalId}")
    public ResponseEntity<CheckingBankAccountDTO> saveCheckingPersonalBankAccountDTO( @PathVariable Long personalId  ) throws PersonalNotFoundException {
        CheckingBankAccountDTO checkingBankAccountDTO=new CheckingBankAccountDTO();
        double initialBalance=checkingBankAccountDTO.getBalance();
        double overDraft=checkingBankAccountDTO.getOverDraft();
        return ResponseEntity.ok().body(bankAccountService.saveCheckingPersonalBankAccount(initialBalance,overDraft,personalId));
    }
    @PostMapping("/checking/business/{businessId}")
    public ResponseEntity<CheckingBankAccountDTO> saveCheckingBusinessBankAccountDTO( @PathVariable Long businessId  ) throws BusinessNotFoundException {
        CheckingBankAccountDTO checkingBankAccountDTO=new CheckingBankAccountDTO();
        double initialBalance=checkingBankAccountDTO.getBalance();
        double overDraft=checkingBankAccountDTO.getOverDraft();
        return ResponseEntity.ok().body(bankAccountService.saveCheckingBusinessBankAccount(initialBalance,overDraft,businessId));
    }
    @PostMapping("/checking/professional/{professionalId}")
    public ResponseEntity<CheckingBankAccountDTO> saveCheckingProfessionalBankAccountDTO( @PathVariable Long professionalId  ) throws ProfessionalNotFoundException, PersonalNotFoundException {
      CheckingBankAccountDTO checkingBankAccountDTO=new CheckingBankAccountDTO();
      double initialBalance=checkingBankAccountDTO.getBalance();
        double overDraft=checkingBankAccountDTO.getOverDraft();
        return ResponseEntity.ok().body(bankAccountService.saveCheckingProfessionalBankAccount(initialBalance,overDraft,professionalId));
    }
    //    Saving Accounts
    @PostMapping("/saving/personal/{personalId}")
    public ResponseEntity<SavingBankAccountDTO> saveSavingPersonalBankAccountDTO( @PathVariable Long personalId  ) throws PersonalNotFoundException {
      SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
      double initialBalance=savingBankAccountDTO.getBalance();
        double interestRate=savingBankAccountDTO.getInterestRate();
        return ResponseEntity.ok().body(bankAccountService.saveSavingPersonalBankAccount(initialBalance,interestRate,personalId));
    }
    @PostMapping("/saving/business/{businessId}")
    public ResponseEntity<SavingBankAccountDTO> saveSavingBusinessBankAccountDTO( @PathVariable Long businessId  ) throws BusinessNotFoundException{
      SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
      double initialBalance=savingBankAccountDTO.getBalance();
        double interestRate=savingBankAccountDTO.getInterestRate();
        return ResponseEntity.ok().body(bankAccountService.saveSavingBusinessBankAccount(initialBalance,interestRate,businessId));
    }
    @PostMapping("/saving/professional/{professionalId}")
    public ResponseEntity<SavingBankAccountDTO> saveSavingProfessionalBankAccountDTO(@PathVariable Long professionalId  ) throws ProfessionalNotFoundException {
      SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        double initialBalance=savingBankAccountDTO.getBalance();
        double interestRate=savingBankAccountDTO.getInterestRate();
        return ResponseEntity.ok().body(bankAccountService.saveSavingProfessionalBankAccount(initialBalance,interestRate,professionalId));
    }

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

    @PutMapping("/{bankAccountId}/activate")
    public ResponseEntity<BankAccountDTO> activate(@PathVariable String bankAccountId) {

        return ResponseEntity.ok().body(bankAccountService.activate(bankAccountId));
    }
    @PutMapping("/{bankAccountId}/suspend")
    public ResponseEntity<BankAccountDTO> suspend(@PathVariable String bankAccountId) {

        return ResponseEntity.ok().body(bankAccountService.suspend(bankAccountId));
    }


    @PutMapping("/{bankAccountId}/delete")
    public ResponseEntity<BankAccountDTO> delete(@PathVariable String bankAccountId) {

        return ResponseEntity.ok().body(bankAccountService.delete(bankAccountId));
    }
}

