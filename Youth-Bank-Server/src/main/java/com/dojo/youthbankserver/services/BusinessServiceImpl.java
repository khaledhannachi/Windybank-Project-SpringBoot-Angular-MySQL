package com.dojo.youthbankserver.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dojo.youthbankserver.dtos.BankAccountDTO;
import com.dojo.youthbankserver.dtos.PersonalDTO;
import com.dojo.youthbankserver.entities.*;
import com.dojo.youthbankserver.enums.AccountStatus;
import com.dojo.youthbankserver.exceptions.BankAccountNotFoundException;
import com.dojo.youthbankserver.exceptions.PersonalNotFoundException;
import com.dojo.youthbankserver.exceptions.UserNotFoundException;
import com.dojo.youthbankserver.mappers.BankAccountMapperImpl;
import com.dojo.youthbankserver.mappers.UserMapper;
import com.dojo.youthbankserver.repositories.UserRepository;
import org.springframework.stereotype.Service;

import com.dojo.youthbankserver.dtos.BusinessDTO;
import com.dojo.youthbankserver.exceptions.BusinessNotFoundException;
import com.dojo.youthbankserver.mappers.BusinessMapper;
import com.dojo.youthbankserver.repositories.BusinessRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class BusinessServiceImpl implements BusinessService{
	private BusinessRepository businessRepository;
    private UserRepository userRepository;
    private BusinessMapper businessDtoMapper;
    private UserMapper userMapper;
    private BankAccountMapperImpl dtoMapper;
    @Override
    public BusinessDTO saveBusiness(BusinessDTO businessDTO, Long userId) {
        log.info("Saving new Business");

        // Retrieve the User entity
        User businessUser = userRepository.findById(userId).orElse(null);

        // Check for null and handle it appropriately
        if (businessUser == null) {
            // Handle the case where the user is not found
            // You might want to throw an exception or return an error response
            // depending on your use case
            log.error("User not found with id: {}", userId);
            // Handle the error appropriately
        }

        // Set the user information in the DTO
        businessDTO.setBusinessLegalResponsibleDTO(userMapper.fromUser(businessUser));

        // Map from PersonalDTO to Personal entity
        Business business = businessDtoMapper.fromBusinessDTO(businessDTO);

        // Set the user information in the  Business entity
        business.setBusinessLegalResponsible(userMapper.fromUserDTO(businessDTO.getBusinessLegalResponsibleDTO()));

        // Save the Business entity
        Business savedBusiness = businessRepository.save(business);

        // Map from Personal entity to Business and return
        BusinessDTO dto=businessDtoMapper.fromBusiness(savedBusiness);
        dto.setBusinessLegalResponsibleDTO(userMapper.fromUser(savedBusiness.getBusinessLegalResponsible()));
        return businessDTO;
    }
    @Override
    public List<BusinessDTO> listBusinesses() {
        List<Business> businesses = businessRepository.findAll();
//        List<BusinessDTO> businessDTOS = businesses.stream()
//                .map(business -> businessDtoMapper.fromBusiness(business))
//                .collect(Collectors.toList());

        List<BusinessDTO> businessDTOS=new ArrayList<>();
        for (Business business:businesses){
            BusinessDTO businessDTO=businessDtoMapper.fromBusiness(business);
            businessDTO.setBusinessLegalResponsibleDTO(userMapper.fromUser(business.getBusinessLegalResponsible()));
            List<BankAccount> bankAccounts =business.getBusinessBankAccounts();
            List<BankAccountDTO> bankAccountDTOS = bankAccounts.stream().flatMap(bankAccount -> {
                if (bankAccount instanceof SavingAccount) {
                    SavingAccount savingAccount = (SavingAccount) bankAccount;
                    return Stream.of(
                            dtoMapper.fromSavingBusinessBankAccount(savingAccount)
                    );
                } else{
                    CheckingAccount checkingAccount = (CheckingAccount) bankAccount;
                    return Stream.of(
                            dtoMapper.fromCheckingBusinessBankAccount(checkingAccount)
                    );
                }
            }).collect(Collectors.toList());
            businessDTO.setBusinessBankAccountsDTO(bankAccountDTOS);
            businessDTOS.add(businessDTO);
        }
        return businessDTOS;
    }
    @Override
    public BusinessDTO getBusiness(Long businessId) throws BusinessNotFoundException {
        Business business = businessRepository.findById(businessId).orElseThrow(() -> new BusinessNotFoundException("Business Not found"));
        BusinessDTO dto=businessDtoMapper.fromBusiness(business);
        dto.setBusinessLegalResponsibleDTO(userMapper.fromUser(business.getBusinessLegalResponsible()));
        return dto;
    }
    @Override
    public BusinessDTO updateBusiness(BusinessDTO businessDTO , Long userId) {
        log.info("update new Business");
        User businessUser =userRepository.findById(userId).orElse(null);
        Business business =businessDtoMapper.fromBusinessDTO(businessDTO);
        business.setBusinessLegalResponsible(businessUser);
        Business savedBusiness = businessRepository.save(business);
        return businessDtoMapper.fromBusiness(savedBusiness);
    }
    @Override
    public BusinessDTO activateBusiness(BusinessDTO businessDTO, Long userId, BankAccount bankAccount) throws BankAccountNotFoundException, UserNotFoundException  {
        log.info("activate Business");

        // Retrieve the user who is the legal responsible for the business
        User legalResponsible = userRepository.findById(userId).orElse(null);
        if (legalResponsible == null) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        // Retrieve the business associated with the user
        Business business = businessDtoMapper.fromBusinessDTO(businessDTO);

        // Ensure that the provided bank account belongs to the specified business
        if (!business.getBusinessBankAccounts().contains(bankAccount)) {
            throw new BankAccountNotFoundException("Bank account not associated with the provided business.");
        }
        // Set the status of the provided bank account to ACTIVATED
        bankAccount.setStatus(AccountStatus.ACTIVATED);
        // Set the legal responsible user for the business
        business.setBusinessLegalResponsible(legalResponsible);
        // Save the updated business entity
        Business savedBusiness = businessRepository.save(business);
        // Convert the saved business entity back to DTO and return
        return businessDtoMapper.fromBusiness(savedBusiness);
    }

    @Override
    public void deleteBusiness(Long businessId){
        businessRepository.deleteById(businessId);
    }
  

}
