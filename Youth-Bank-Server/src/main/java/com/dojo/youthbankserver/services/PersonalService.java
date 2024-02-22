package com.dojo.youthbankserver.services;

import java.util.List;

import com.dojo.youthbankserver.dtos.PersonalDTO;
import com.dojo.youthbankserver.exceptions.PersonalNotFoundException;



public interface PersonalService {
	   PersonalDTO savePersonal(PersonalDTO personalDTO, Long userId);
	    List<PersonalDTO> listPersonals();
	 
	    PersonalDTO getPersonal(Long personalId) throws PersonalNotFoundException;

	    PersonalDTO updatePersonal(PersonalDTO personalDTO ,Long userId);

	    void deletePersonal(Long personalId);

//	    List<PersonalDTO> searchPersonals(String keyword);
}
