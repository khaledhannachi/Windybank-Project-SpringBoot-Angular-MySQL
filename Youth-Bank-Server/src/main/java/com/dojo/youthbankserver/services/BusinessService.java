package com.dojo.youthbankserver.services;

import java.util.List;

import com.dojo.youthbankserver.dtos.BusinessDTO;
import com.dojo.youthbankserver.entities.BankAccount;
import com.dojo.youthbankserver.exceptions.BankAccountNotFoundException;
import com.dojo.youthbankserver.exceptions.BusinessNotFoundException;
import com.dojo.youthbankserver.exceptions.UserNotFoundException;

public interface BusinessService {

	BusinessDTO saveBusiness(BusinessDTO businessDTO, Long userId);
  	
    List<BusinessDTO> listBusinesses();
 
    BusinessDTO getBusiness(Long businessId) throws BusinessNotFoundException;

    BusinessDTO updateBusiness(BusinessDTO businessDTO, Long userId);


    void deleteBusiness(Long businessId);


    BusinessDTO getBusinessByUserId(Long userId) throws UserNotFoundException;
}
