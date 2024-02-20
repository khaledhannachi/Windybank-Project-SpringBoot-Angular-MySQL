
package com.dojo.youthbankserver.controllers;

import java.util.List;

import com.dojo.youthbankserver.dtos.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dojo.youthbankserver.exceptions.BalanceNotSufficientException;
import com.dojo.youthbankserver.exceptions.BankAccountNotFoundException;
import com.dojo.youthbankserver.services.BankAccountService;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/accounts")

public class BankAccountRestAPI {

    private BankAccountService bankAccountService;


    @GetMapping("/{accountId}")
    public ResponseEntity<BankAccountDTO> getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return ResponseEntity.ok().body(bankAccountService.getBankAccount(accountId));
    }



    @GetMapping("")
    public ResponseEntity<List<BankAccountDTO>> listAccounts(){
        return ResponseEntity.ok().body(bankAccountService.bankAccountList());
    }
    @PostMapping("/saving/customerId")
    public CheckingBankAccountDTO saveCheckingBankAccountDTO(@Valid @RequestBody @PathVariable String customerId ){
        return bankAccountService.saveCheckingBankAccount();
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
}
