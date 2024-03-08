package com.dojo.youthbankserver.dtos;

import com.dojo.youthbankserver.entities.BankAccount;
import com.dojo.youthbankserver.entities.User;
import lombok.Data;

import java.util.List;

@Data
public class ProfessionalDTO {

 	private Long id;
     private String profession;
    private String taxIdentificationNumber;
    private String businessRegistration;
    private String companyName;
    private String companyAdress;
    private String cnssNumber;
    private UserDTO userProfessionalDTO;
    private List<BankAccountDTO> professionalBankAccountsDTO;










}
