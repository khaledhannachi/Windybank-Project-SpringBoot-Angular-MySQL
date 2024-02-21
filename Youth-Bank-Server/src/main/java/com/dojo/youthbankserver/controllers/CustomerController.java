package com.dojo.youthbankserver.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dojo.youthbankserver.dtos.CustomerDTO;
import com.dojo.youthbankserver.exceptions.CustomerNotFoundException;
import com.dojo.youthbankserver.services.CustomerServiceImpl;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/v1")
@AllArgsConstructor
public class CustomerController {

    private CustomerServiceImpl customerServiceImpl;
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> customers(){
        return ResponseEntity.ok().body(customerServiceImpl.listCustomers());
    }
//    @GetMapping("/customers/search")
//    public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword",defaultValue = "") String keyword){
//        return customerService.searchCustomers("%"+keyword+"%");
//    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return ResponseEntity.ok().body(customerServiceImpl.getCustomer(customerId));
    }
    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok().body(customerServiceImpl.saveCustomer(customerDTO));
    }
    @PutMapping("/customers/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
        return ResponseEntity.ok().body(customerServiceImpl.updateCustomer(customerDTO));
    }
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
    	customerServiceImpl.deleteCustomer(id);
    }
}
