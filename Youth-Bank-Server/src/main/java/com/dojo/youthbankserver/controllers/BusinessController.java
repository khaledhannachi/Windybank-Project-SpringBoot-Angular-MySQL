package com.dojo.youthbankserver.controllers;



import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import com.dojo.youthbankserver.dtos.BusinessDTO;
import com.dojo.youthbankserver.exceptions.BusinessNotFoundException;
import com.dojo.youthbankserver.services.BankAccountService;
import com.dojo.youthbankserver.services.BusinessService;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class BusinessController {
    private BusinessService businessService;
    @GetMapping("/businesss")
    public List<BusinessDTO> businesss(){
        return businessService.listBusinesss();
    }
    @GetMapping("/businesss/search")
    public List<BusinessDTO> searchBusinesss(@RequestParam(name = "keyword",defaultValue = "") String keyword){
        return businessService.searchBusinesss("%"+keyword+"%");
    }
    @GetMapping("/businesss/{id}")
    public BusinessDTO getBusiness(@PathVariable(name = "id") Long businessId) throws BusinessNotFoundException {
        return businessService.getBusiness(businessId);
    }
    @PostMapping("/businesss")
    public BusinessDTO saveBusiness(@RequestBody BusinessDTO businessDTO){
        return businessService.saveBusiness(businessDTO);
    }
    @PutMapping("/businesss/{businessId}")
    public BusinessDTO updateBusiness(@PathVariable Long businessId, @RequestBody BusinessDTO businessDTO){
        businessDTO.setId(businessId);
        return businessService.updateBusiness(businessDTO);
    }
    @DeleteMapping("/businesss/{id}")
    public void deleteBusiness(@PathVariable Long id){
    	businessService.deleteBusiness(id);
    }
}
