package com.dojo.youthbankserver.services;

import java.util.List;

import com.dojo.youthbankserver.dtos.BusinessDTO;
import com.dojo.youthbankserver.dtos.PersonalDTO;
import com.dojo.youthbankserver.exceptions.PersonalNotFoundException;



public interface PersonalService {
	   PersonalDTO savePersonal(PersonalDTO personalDTO, Long userId);
	    List<PersonalDTO> listPersonals();

	    PersonalDTO getPersonal(Long personalId) throws PersonalNotFoundException;

	    PersonalDTO updatePersonal(PersonalDTO personalDTO ,Long userId);

	    void deletePersonal(Long personalId);

  //    public List<PersonalDTO> searchPersonals(String keyword) {
  //        List<Personal> personals=personalRepository.searchPersonal(keyword);
  //        List<PersonalDTO> personalDTOS = personals.stream().map(cust -> personalDtoMapper.fromPersonal(cust)).collect(Collectors.toList());
  //        return personalDTOS;
  //    }
  PersonalDTO getPersonalByUserId(Long userId);

  //    public List<PersonalDTO> searchPersonals(String keyword) {
  //        List<Personal> personals=personalRepository.searchPersonal(keyword);
  //        List<PersonalDTO> personalDTOS = personals.stream().map(cust -> personalDtoMapper.fromPersonal(cust)).collect(Collectors.toList());
  //        return personalDTOS;
  //    }


//	    List<PersonalDTO> searchPersonals(String keyword);
}
