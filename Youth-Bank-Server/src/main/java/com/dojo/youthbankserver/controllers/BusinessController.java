package com.dojo.youthbankserver.controllers;


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
@RequestMapping("/api/v1/businesss")
public class BusinessController {

    private BusinessServiceImpl businessServiceImpl;
    @GetMapping("")
    public ResponseEntity<List<BusinessDTO>> businesss(){
        return ResponseEntity.ok().body(businessServiceImpl.listBusinesss());
    }
//    @GetMapping("/businesss/search")
//    public List<BusinessDTO> searchBusinesss(@RequestParam(name = "keyword",defaultValue = "") String keyword){
//        return businessService.searchBusinesss("%"+keyword+"%");
//    }
    @GetMapping("/{id}")
    public ResponseEntity<BusinessDTO> getBusiness(@PathVariable(name = "id") Long businessId) throws BusinessNotFoundException {
        return ResponseEntity.ok().body(businessServiceImpl.getBusiness(businessId));
    }
    @PostMapping("")
    public ResponseEntity<BusinessDTO> saveBusiness(@RequestBody BusinessDTO businessDTO){
        return ResponseEntity.ok().body(businessServiceImpl.saveBusiness(businessDTO));
    }
    @PutMapping("/{businessId}")
    public ResponseEntity<BusinessDTO> updateBusiness(@PathVariable Long businessId, @RequestBody BusinessDTO businessDTO){
        businessDTO.setId(businessId);
        return ResponseEntity.ok().body(businessServiceImpl.updateBusiness(businessDTO));
    }
    @DeleteMapping("/{id}")
    public void deleteBusiness(@PathVariable Long id){
    	businessServiceImpl.deleteBusiness(id);
    }
}
