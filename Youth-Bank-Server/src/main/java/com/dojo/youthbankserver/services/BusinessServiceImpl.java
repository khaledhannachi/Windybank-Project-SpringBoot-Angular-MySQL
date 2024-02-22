package com.dojo.youthbankserver.services;


import java.util.List;
import java.util.stream.Collectors;

import com.dojo.youthbankserver.entities.User;
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
    public BusinessDTO updateBusiness(BusinessDTO businessDTO,Long userId) {
        log.info("update new Business");
          User legalResponsible =userRepository.findById(userId).orElse(null);
        Business business=businessDtoMapper.fromBusinessDTO(businessDTO);
          business.setBusinessLegalResponsible(legalResponsible);
        Business savedBusiness = businessRepository.save(business);
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
