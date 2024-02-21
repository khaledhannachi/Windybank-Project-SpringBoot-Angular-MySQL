package com.dojo.youthbankserver.services;

import java.util.List;

import com.dojo.youthbankserver.dtos.PersonalDTO;
import com.dojo.youthbankserver.exceptions.PersonalNotFoundException;



public interface PersonalService {
	   PersonalDTO savePersonal(PersonalDTO personalDTO);
	    List<PersonalDTO> listPersonals();
	 
	    PersonalDTO getPersonal(Long customerId) throws PersonalNotFoundException;

	    PersonalDTO updatePersonal(PersonalDTO personalDTO);

	    void deletePersonal(Long customerId);

//	    List<PersonalDTO> searchCustomers(String keyword);
}
