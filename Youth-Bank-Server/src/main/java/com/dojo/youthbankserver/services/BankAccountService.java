package com.dojo.youthbankserver.services;

import com.dojo.youthbankserver.entities.Business;
import com.dojo.youthbankserver.entities.Customer;
import com.dojo.youthbankserver.entities.Professional;

public interface BankAccountService {

	Customer saveCustomer(Customer customer);
	Business saveBusiness(Business business);
	Professional saveProfessional(Professional customer);
	
	
	
}
