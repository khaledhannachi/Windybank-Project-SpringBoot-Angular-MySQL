package com.dojo.youthbankserver.mappers;

import com.dojo.youthbankserver.dtos.PersonalDTO;
import com.dojo.youthbankserver.entities.Personal;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dojo.youthbankserver.dtos.AccountOperationDTO;
import com.dojo.youthbankserver.dtos.CheckingBankAccountDTO;
import com.dojo.youthbankserver.dtos.SavingBankAccountDTO;
import com.dojo.youthbankserver.entities.AccountOperation;
import com.dojo.youthbankserver.entities.CheckingAccount;
import com.dojo.youthbankserver.entities.SavingAccount;

@Service
public class BankAccountMapperImpl {
	public PersonalDTO fromPersonal(Personal personal){
        PersonalDTO personalDTO =new PersonalDTO();
        BeanUtils.copyProperties(personal, personalDTO);
        return personalDTO;
    }
    public Personal fromPersonalDTO(PersonalDTO personalDTO){
        Personal personal =new Personal();
        BeanUtils.copyProperties(personalDTO, personal);
        return personal;
    }

    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount){
        SavingBankAccountDTO savingBankAccountDTO=new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);
        savingBankAccountDTO.setPersonalDTO(fromPersonal(savingAccount.getPersonal()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }

    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
        SavingAccount savingAccount=new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO,savingAccount);
        savingAccount.setPersonal(fromPersonalDTO(savingBankAccountDTO.getPersonalDTO()));
        return savingAccount;
    }

    public CheckingBankAccountDTO fromCheckingBankAccount(CheckingAccount checkingAccount){
        CheckingBankAccountDTO checkingBankAccountDTO=new CheckingBankAccountDTO();
        BeanUtils.copyProperties(checkingAccount,checkingBankAccountDTO);
        checkingBankAccountDTO.setPersonalDTO(fromPersonal(checkingAccount.getPersonal()));
        checkingBankAccountDTO.setType(checkingAccount.getClass().getSimpleName());
        return checkingBankAccountDTO;
    }

    public CheckingAccount fromCheckingBankAccountDTO(CheckingBankAccountDTO checkingBankAccountDTO){
        CheckingAccount checkingAccount=new CheckingAccount();
        BeanUtils.copyProperties(checkingBankAccountDTO,checkingAccount);
        checkingAccount.setPersonal(fromPersonalDTO(checkingBankAccountDTO.getPersonalDTO()));
        return checkingAccount;
    }

    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
        AccountOperationDTO accountOperationDTO=new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation,accountOperationDTO);
        return accountOperationDTO;
    }

}
