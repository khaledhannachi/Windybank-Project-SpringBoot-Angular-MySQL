package com.dojo.youthbankserver.services;

import java.util.List;

import com.dojo.youthbankserver.dtos.BusinessDTO;
import com.dojo.youthbankserver.entities.BankAccount;
import com.dojo.youthbankserver.exceptions.BankAccountNotFoundException;
import com.dojo.youthbankserver.exceptions.BusinessNotFoundException;
import com.dojo.youthbankserver.exceptions.UserNotFoundException;

public interface BusinessService {

	BusinessDTO saveBusiness(BusinessDTO businessDTO, Long userId);
  	
    List<BusinessDTO> listBusinesss();
 
    BusinessDTO getBusiness(Long businessId) throws BusinessNotFoundException;

    BusinessDTO updateBusiness(BusinessDTO businessDTO, Long userId);

    BusinessDTO activateBusiness(BusinessDTO businessDTO, Long userId, BankAccount bankAccount) throws BankAccountNotFoundException, UserNotFoundException;

    void deleteBusiness(Long businessId);

//    List<BusinessDTO> searchBusinesss(String keyword);
}
