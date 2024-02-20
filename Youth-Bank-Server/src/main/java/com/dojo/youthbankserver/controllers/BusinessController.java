package com.dojo.youthbankserver.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dojo.youthbankserver.dtos.BusinessDTO;
import com.dojo.youthbankserver.exceptions.BusinessNotFoundException;
import com.dojo.youthbankserver.services.BusinessServiceImpl;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class BusinessController {
	@Autowired
    private BusinessServiceImpl businessServiceImpl;
    @GetMapping("/businesss")
    public List<BusinessDTO> businesss(){
        return businessServiceImpl.listBusinesss();
    }
//    @GetMapping("/businesss/search")
//    public List<BusinessDTO> searchBusinesss(@RequestParam(name = "keyword",defaultValue = "") String keyword){
//        return businessService.searchBusinesss("%"+keyword+"%");
//    }
    @GetMapping("/businesss/{id}")
    public BusinessDTO getBusiness(@PathVariable(name = "id") Long businessId) throws BusinessNotFoundException {
        return businessServiceImpl.getBusiness(businessId);
    }
    @PostMapping("/businesss")
    public BusinessDTO saveBusiness(@RequestBody BusinessDTO businessDTO){
        return businessServiceImpl.saveBusiness(businessDTO);
    }
    @PutMapping("/businesss/{businessId}")
    public BusinessDTO updateBusiness(@PathVariable Long businessId, @RequestBody BusinessDTO businessDTO){
        businessDTO.setId(businessId);
        return businessServiceImpl.updateBusiness(businessDTO);
    }
    @DeleteMapping("/businesss/{id}")
    public void deleteBusiness(@PathVariable Long id){
    	businessServiceImpl.deleteBusiness(id);
    }
}
