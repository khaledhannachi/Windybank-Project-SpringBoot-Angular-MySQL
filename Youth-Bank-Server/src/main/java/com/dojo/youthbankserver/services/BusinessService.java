package com.dojo.youthbankserver.services;

import java.util.List;

import com.dojo.youthbankserver.dtos.BusinessDTO;
import com.dojo.youthbankserver.exceptions.BusinessNotFoundException;

public interface BusinessService {

	BusinessDTO saveBusiness(BusinessDTO businessDTO, Long userId);
  	
    List<BusinessDTO> listBusinesss();
 
    BusinessDTO getBusiness(Long businessId) throws BusinessNotFoundException;

    BusinessDTO updateBusiness(BusinessDTO businessDTO, Long userId);

    void deleteBusiness(Long businessId);

//    List<BusinessDTO> searchBusinesss(String keyword);
}
