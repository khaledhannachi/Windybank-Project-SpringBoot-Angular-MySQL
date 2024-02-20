package com.dojo.youthbankserver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dojo.youthbankserver.dtos.CustomerDTO;
import com.dojo.youthbankserver.entities.Customer;
import com.dojo.youthbankserver.exceptions.CustomerNotFoundException;
import com.dojo.youthbankserver.mappers.CustomerMapper;
import com.dojo.youthbankserver.mappers.ProfessionalMapper;
import com.dojo.youthbankserver.repositories.AccountOperationRepository;
import com.dojo.youthbankserver.repositories.BankAccountRepository;
import com.dojo.youthbankserver.repositories.CustomerRepository;
import com.dojo.youthbankserver.repositories.ProfessionalRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	private CustomerRepository customerRepository;
    private CustomerMapper customerDtoMapper;

	  @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("Saving new Customer");
        Customer customer=customerDtoMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerDtoMapper.fromCustomer(savedCustomer);
    }
	  @Override
    public List<CustomerDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customer -> customerDtoMapper.fromCustomer(customer))
                .collect(Collectors.toList());
        
        /*
        List<CustomerDTO> customerDTOS=new ArrayList<>();
        for (Customer customer:customers){
            CustomerDTO customerDTO=customerDtoMapper.fromCustomer(customer);
            customerDTOS.add(customerDTO);
        }
        *
         */
        return customerDTOS;
    }
	  @Override
    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not found"));
        return customerDtoMapper.fromCustomer(customer);
    }

	  @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        log.info("Saving new Customer");
        Customer customer=customerDtoMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerDtoMapper.fromCustomer(savedCustomer);
    }
	  @Override
    public void deleteCustomer(Long customerId){
        customerRepository.deleteById(customerId);
    }


//    public List<CustomerDTO> searchCustomers(String keyword) {
//        List<Customer> customers=customerRepository.searchCustomer(keyword);
//        List<CustomerDTO> customerDTOS = customers.stream().map(cust -> customerDtoMapper.fromCustomer(cust)).collect(Collectors.toList());
//        return customerDTOS;
//    }
	
	
}