package com.dojo.youthbankserver.mappers;

import com.dojo.youthbankserver.dtos.PersonalDTO;
import com.dojo.youthbankserver.entities.Personal;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dojo.youthbankserver.dtos.AccountOperationDTO;
import com.dojo.youthbankserver.dtos.CheckingBankAccountDTO;
import com.dojo.youthbankserver.dtos.SavingBankAccountDTO;
import com.dojo.youthbankserver.entities.AccountOperation;
import com.dojo.youthbankserver.entities.CheckingAccount;
import com.dojo.youthbankserver.entities.SavingAccount;

@Service
@AllArgsConstructor
public class BankAccountMapperImpl {
    public PersonalMapper personalMapper;
    public BusinessMapper businessMapper;

    public ProfessionalMapper professionalMapper;

//    Personal
    public SavingBankAccountDTO fromSavingPersonalBankAccount(SavingAccount savingAccount){
        SavingBankAccountDTO savingBankAccountDTO=new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);
//        savingBankAccountDTO.setPersonalDTO(personalMapper.fromPersonal(savingAccount.getPersonal()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }

    public SavingAccount fromSavingPersonalBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
        SavingAccount savingAccount=new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO,savingAccount);
//        savingAccount.setPersonal(personalMapper.fromPersonalDTO(savingBankAccountDTO.getPersonalDTO()));
        return savingAccount;
    }

    public CheckingBankAccountDTO fromCheckingPersonalBankAccount(CheckingAccount checkingAccount){
        CheckingBankAccountDTO checkingBankAccountDTO=new CheckingBankAccountDTO();
        BeanUtils.copyProperties(checkingAccount,checkingBankAccountDTO);
//        checkingBankAccountDTO.setPersonalDTO(personalMapper.fromPersonal(checkingAccount.getPersonal()));
        checkingBankAccountDTO.setType(checkingAccount.getClass().getSimpleName());
        return checkingBankAccountDTO;
    }

    public CheckingAccount fromCheckingPersonalBankAccountDTO(CheckingBankAccountDTO checkingBankAccountDTO){
        CheckingAccount checkingAccount=new CheckingAccount();
        BeanUtils.copyProperties(checkingBankAccountDTO,checkingAccount);
//        checkingAccount.setPersonal(personalMapper.fromPersonalDTO(checkingBankAccountDTO.getPersonalDTO()));
        return checkingAccount;
    }
//Business

    public SavingBankAccountDTO fromSavingBusinessBankAccount(SavingAccount savingAccount){
        SavingBankAccountDTO savingBankAccountDTO=new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);
//        savingBankAccountDTO.setBusinessDTO(businessMapper.fromBusiness(savingAccount.getBusiness()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }

    public SavingAccount fromSavingBusinessBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
        SavingAccount savingAccount=new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO,savingAccount);
//        savingAccount.setBusiness(businessMapper.fromBusinessDTO(savingBankAccountDTO.getBusinessDTO()));
        return savingAccount;
    }

    public CheckingBankAccountDTO fromCheckingBusinessBankAccount(CheckingAccount checkingAccount){
        CheckingBankAccountDTO checkingBankAccountDTO=new CheckingBankAccountDTO();
        BeanUtils.copyProperties(checkingAccount,checkingBankAccountDTO);
//        checkingBankAccountDTO.setBusinessDTO(businessMapper.fromBusiness(checkingAccount.getBusiness()));
        checkingBankAccountDTO.setType(checkingAccount.getClass().getSimpleName());
        return checkingBankAccountDTO;
    }

    public CheckingAccount fromCheckingBusinessBankAccountDTO(CheckingBankAccountDTO checkingBankAccountDTO){
        CheckingAccount checkingAccount=new CheckingAccount();
        BeanUtils.copyProperties(checkingBankAccountDTO,checkingAccount);
//        checkingAccount.setBusiness(businessMapper.fromBusinessDTO(checkingBankAccountDTO.getBusinessDTO()));
        return checkingAccount;
    }


//Professional

    public SavingBankAccountDTO fromSavingProfessionalBankAccount(SavingAccount savingAccount){
        SavingBankAccountDTO savingBankAccountDTO=new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);
//        savingBankAccountDTO.setProfessionalDTO(professionalMapper.fromProfessional(savingAccount.getProfessional()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }

    public SavingAccount fromSavingProfessionalBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
        SavingAccount savingAccount=new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO,savingAccount);
//        savingAccount.setProfessional(professionalMapper.fromProfessionalDTO(savingBankAccountDTO.getProfessionalDTO()));
        return savingAccount;
    }

    public CheckingBankAccountDTO fromCheckingProfessionalBankAccount(CheckingAccount checkingAccount){
        CheckingBankAccountDTO checkingBankAccountDTO=new CheckingBankAccountDTO();
        BeanUtils.copyProperties(checkingAccount,checkingBankAccountDTO);
//        checkingBankAccountDTO.setProfessionalDTO(professionalMapper.fromProfessional(checkingAccount.getProfessional()));
        checkingBankAccountDTO.setType(checkingAccount.getClass().getSimpleName());
        return checkingBankAccountDTO;
    }

    public CheckingAccount fromCheckingProfessionalBankAccountDTO(CheckingBankAccountDTO checkingBankAccountDTO){
        CheckingAccount checkingAccount=new CheckingAccount();
        BeanUtils.copyProperties(checkingBankAccountDTO,checkingAccount);
//        checkingAccount.setProfessional(professionalMapper.fromProfessionalDTO(checkingBankAccountDTO.getProfessionalDTO()));
        return checkingAccount;
    }


    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
        AccountOperationDTO accountOperationDTO=new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation,accountOperationDTO);
        return accountOperationDTO;
    }

}
