package com.dojo.youthbankserver.services;


import java.util.List;
import java.util.stream.Collectors;

import com.dojo.youthbankserver.dtos.PersonalDTO;
import com.dojo.youthbankserver.entities.BankAccount;
import com.dojo.youthbankserver.entities.Personal;
import com.dojo.youthbankserver.entities.User;
import com.dojo.youthbankserver.enums.AccountStatus;
import com.dojo.youthbankserver.exceptions.BankAccountNotFoundException;
import com.dojo.youthbankserver.exceptions.UserNotFoundException;
import com.dojo.youthbankserver.repositories.UserRepository;
import org.springframework.stereotype.Service;

import com.dojo.youthbankserver.dtos.BusinessDTO;
import com.dojo.youthbankserver.entities.Business;
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
    
	  @Override
    public BusinessDTO saveBusiness(BusinessDTO businessDTO, Long userId) {
        log.info("Saving new Business");
          User legalResponsible =userRepository.findById(userId).orElse(null);
        Business business=businessDtoMapper.fromBusinessDTO(businessDTO);
          business.setBusinessLegalResponsible(legalResponsible);
        Business savedBusiness = businessRepository.save(business);
        return businessDtoMapper.fromBusiness(savedBusiness);
    }
	  @Override
    public List<BusinessDTO> listBusinesss() {
        List<Business> businesss = businessRepository.findAll();
        List<BusinessDTO> businessDTOS = businesss.stream()
                .map(business -> businessDtoMapper.fromBusiness(business))
                .collect(Collectors.toList());
        /*
        List<BusinessDTO> businessDTOS=new ArrayList<>();
        for (Business business:businesss){
            BusinessDTO businessDTO=businessDtoMapper.fromBusiness(business);
            businessDTOS.add(businessDTO);
        }
        *
         */
        return businessDTOS;
    }
	  @Override
    public BusinessDTO getBusiness(Long businessId) throws BusinessNotFoundException {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new BusinessNotFoundException("Business Not found"));
        return businessDtoMapper.fromBusiness(business);
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
  
//    @Override
//	  public List<BusinessDTO> searchBusinesss(String keyword) {
//        List<Business> businesss=businessRepository.searchBusiness(keyword);
//        List<BusinessDTO> businessDTOS = businesss.stream().map(cust -> businessDtoMapper.fromBusiness(cust)).collect(Collectors.toList());
//        return businessDTOS;
//    }
//	
	
}
