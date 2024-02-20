package com.dojo.youthbankserver.services;

import java.util.List;

import com.dojo.youthbankserver.dtos.CustomerDTO;
import com.dojo.youthbankserver.exceptions.CustomerNotFoundException;



public interface CustomerService {
	   CustomerDTO saveCustomer(CustomerDTO customerDTO);
	    List<CustomerDTO> listCustomers();
	 
	    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

	    CustomerDTO updateCustomer(CustomerDTO customerDTO);

	    void deleteCustomer(Long customerId);

//	    List<CustomerDTO> searchCustomers(String keyword);
}
