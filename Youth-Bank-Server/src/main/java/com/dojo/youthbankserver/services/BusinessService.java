package com.dojo.youthbankserver.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dojo.youthbankserver.dtos.BusinessDTO;
import com.dojo.youthbankserver.entities.Business;
import com.dojo.youthbankserver.exceptions.BusinessNotFoundException;
import com.dojo.youthbankserver.mappers.BusinessMapper;
import com.dojo.youthbankserver.repositories.AccountOperationRepository;
import com.dojo.youthbankserver.repositories.BankAccountRepository;
import com.dojo.youthbankserver.repositories.BusinessRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BusinessService {

	private BusinessRepository businessRepository;
    private BusinessMapper businessDtoMapper;

    public BusinessDTO saveBusiness(BusinessDTO businessDTO) {
//        log.info("Saving new Business");
        Business business=businessDtoMapper.fromBusinessDTO(businessDTO);
        Business savedBusiness = businessRepository.save(business);
        return businessDtoMapper.fromBusiness(savedBusiness);
    }

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

    public BusinessDTO getBusiness(Long businessId) throws BusinessNotFoundException {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new BusinessNotFoundException("Business Not found"));
        return businessDtoMapper.fromBusiness(business);
    }

    public BusinessDTO updateBusiness(BusinessDTO businessDTO) {
//        log.info("Saving new Business");
        Business business=businessDtoMapper.fromBusinessDTO(businessDTO);
        Business savedBusiness = businessRepository.save(business);
        return businessDtoMapper.fromBusiness(savedBusiness);
    }

    public void deleteBusiness(Long businessId){
        businessRepository.deleteById(businessId);
    }
  
    public List<BusinessDTO> searchBusinesss(String keyword) {
        List<Business> businesss=businessRepository.searchBusiness(keyword);
        List<BusinessDTO> businessDTOS = businesss.stream().map(cust -> businessDtoMapper.fromBusiness(cust)).collect(Collectors.toList());
        return businessDTOS;
    }
	
	
}
