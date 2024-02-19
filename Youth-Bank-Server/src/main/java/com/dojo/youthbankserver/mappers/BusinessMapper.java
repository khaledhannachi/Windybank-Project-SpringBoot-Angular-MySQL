package com.dojo.youthbankserver.mappers;

import org.springframework.beans.BeanUtils;

import com.dojo.youthbankserver.dtos.BusinessDTO;
import com.dojo.youthbankserver.entities.Business;

public class BusinessMapper {

	
	   public BusinessDTO fromBusiness(Business business){
	        BusinessDTO businessDTO=new BusinessDTO();
	        BeanUtils.copyProperties(business,businessDTO);
	        return  businessDTO;
	    }
	    public Business fromBusinessDTO(BusinessDTO businessDTO){
	        Business business=new Business();
	        BeanUtils.copyProperties(businessDTO,business);
	        return  business;
	    }
	
}
