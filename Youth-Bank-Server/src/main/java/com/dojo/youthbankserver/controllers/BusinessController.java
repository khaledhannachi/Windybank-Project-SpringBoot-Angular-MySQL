package com.dojo.youthbankserver.controllers;


import com.dojo.youthbankserver.entities.BankAccount;
import com.dojo.youthbankserver.exceptions.BankAccountNotFoundException;
import com.dojo.youthbankserver.exceptions.UserNotFoundException;
import com.dojo.youthbankserver.services.BusinessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dojo.youthbankserver.dtos.BusinessDTO;
import com.dojo.youthbankserver.exceptions.BusinessNotFoundException;
import com.dojo.youthbankserver.services.BusinessServiceImpl;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api/v1/businesses")
public class BusinessController {

    private BusinessService businessServiceImpl;
    @GetMapping("")
    public ResponseEntity<List<BusinessDTO>> businesss(){
        return ResponseEntity.ok().body(businessServiceImpl.listBusinesss());
    }
//    @GetMapping("/businesss/search")
//    public List<BusinessDTO> searchBusinesss(@RequestParam(name = "keyword",defaultValue = "") String keyword){
//        return businessServiceImpl.searchBusinesss("%"+keyword+"%");
//    }
    @GetMapping("business/{id}")
    public ResponseEntity<BusinessDTO> getBusiness(@PathVariable(name = "id") Long businessId) throws BusinessNotFoundException {
        return ResponseEntity.ok().body(businessServiceImpl.getBusiness(businessId));
    }
    @PostMapping("newbusiness/{userId}")
    public ResponseEntity<BusinessDTO> saveBusiness(@RequestBody BusinessDTO businessDTO,@PathVariable Long userId){
        return ResponseEntity.ok().body(businessServiceImpl.saveBusiness(businessDTO, userId));
    }
    @PutMapping("/{businessId}/{userId}/editbusiness")
    public ResponseEntity<BusinessDTO> updateBusiness(@PathVariable Long businessId, @RequestBody BusinessDTO businessDTO, @PathVariable Long userId){
        businessDTO.setId(businessId);
        return ResponseEntity.ok().body(businessServiceImpl.updateBusiness(businessDTO, userId));
    }
    @PutMapping("/{businessId}/{userId}/activate")
    public ResponseEntity<BusinessDTO> activateBusiness(@PathVariable Long businessId, @RequestBody BusinessDTO businessDTO, BankAccount bankAccount, @PathVariable Long userId) throws BankAccountNotFoundException, UserNotFoundException {
        businessDTO.setId(businessId);
        return ResponseEntity.ok().body(businessServiceImpl.activateBusiness(businessDTO, userId,bankAccount ));
    }

    @DeleteMapping("/{id}")
    public void deleteBusiness(@PathVariable Long id){
    	businessServiceImpl.deleteBusiness(id);
    }
}
