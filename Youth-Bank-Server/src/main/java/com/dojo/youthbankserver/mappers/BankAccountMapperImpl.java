package com.dojo.youthbankserver.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dojo.youthbankserver.dtos.AccountOperationDTO;
import com.dojo.youthbankserver.dtos.CheckingBankAccountDTO;
import com.dojo.youthbankserver.dtos.CustomerDTO;
import com.dojo.youthbankserver.dtos.SavingBankAccountDTO;
import com.dojo.youthbankserver.entities.AccountOperation;
import com.dojo.youthbankserver.entities.CheckingAccount;
import com.dojo.youthbankserver.entities.Customer;
import com.dojo.youthbankserver.entities.SavingAccount;

@Service
public class BankAccountMapperImpl {
	public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO=new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        return  customerDTO;
    }
    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return  customer;
    }

    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount){
        SavingBankAccountDTO savingBankAccountDTO=new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);
        savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }

    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
        SavingAccount savingAccount=new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO,savingAccount);
        savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
        return savingAccount;
    }

    public CheckingBankAccountDTO fromCheckingBankAccount(CheckingAccount checkingAccount){
        CheckingBankAccountDTO checkingBankAccountDTO=new CheckingBankAccountDTO();
        BeanUtils.copyProperties(checkingAccount,checkingBankAccountDTO);
        checkingBankAccountDTO.setCustomerDTO(fromCustomer(checkingAccount.getCustomer()));
        checkingBankAccountDTO.setType(checkingAccount.getClass().getSimpleName());
        return checkingBankAccountDTO;
    }

    public CheckingAccount fromCheckingBankAccountDTO(CheckingBankAccountDTO checkingBankAccountDTO){
        CheckingAccount checkingAccount=new CheckingAccount();
        BeanUtils.copyProperties(checkingBankAccountDTO,checkingAccount);
        checkingAccount.setCustomer(fromCustomerDTO(checkingBankAccountDTO.getCustomerDTO()));
        return checkingAccount;
    }

    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
        AccountOperationDTO accountOperationDTO=new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation,accountOperationDTO);
        return accountOperationDTO;
    }

}
