package com.dojo.youthbankserver.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dojo.youthbankserver.dtos.CustomerDTO;
import com.dojo.youthbankserver.exceptions.CustomerNotFoundException;
import com.dojo.youthbankserver.services.CustomerServiceImpl;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/v1")

public class CustomerController {
	@Autowired
    private CustomerServiceImpl customerServiceImpl;
    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return customerServiceImpl.listCustomers();
    }
//    @GetMapping("/customers/search")
//    public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword",defaultValue = "") String keyword){
//        return customerService.searchCustomers("%"+keyword+"%");
//    }
    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return customerServiceImpl.getCustomer(customerId);
    }
    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return customerServiceImpl.saveCustomer(customerDTO);
    }
    @PutMapping("/customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
        return customerServiceImpl.updateCustomer(customerDTO);
    }
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
    	customerServiceImpl.deleteCustomer(id);
    }
}
