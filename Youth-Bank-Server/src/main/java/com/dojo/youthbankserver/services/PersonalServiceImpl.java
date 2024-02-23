package com.dojo.youthbankserver.services;

import java.util.List;
import java.util.stream.Collectors;

import com.dojo.youthbankserver.dtos.PersonalDTO;
import com.dojo.youthbankserver.entities.User;
import com.dojo.youthbankserver.repositories.UserRepository;
import org.springframework.stereotype.Service;

import com.dojo.youthbankserver.entities.Personal;
import com.dojo.youthbankserver.exceptions.PersonalNotFoundException;
import com.dojo.youthbankserver.mappers.PersonalMapper;
import com.dojo.youthbankserver.repositories.PersonalRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class PersonalServiceImpl implements PersonalService {
	private PersonalRepository personalRepository;
    private UserRepository userRepository;
    private PersonalMapper personalDtoMapper;

	  @Override
    public PersonalDTO savePersonal(PersonalDTO personalDTO, Long userId) {
        log.info("Saving new Personal");
        User personalUser =userRepository.findById(userId).orElse(null);
        Personal personal =personalDtoMapper.fromPersonalDTO(personalDTO);
        personal.setUserPersonal(personalUser);
        Personal savedPersonal = personalRepository.save(personal);
        return personalDtoMapper.fromPersonal(savedPersonal);
    }
	  @Override
    public List<PersonalDTO> listPersonals() {
        List<Personal> personals = personalRepository.findAll();
        List<PersonalDTO> personalDTOS = personals.stream()
                .map(personal -> personalDtoMapper.fromPersonal(personal))
                .collect(Collectors.toList());
        
        /*
        List<PersonalDTO> personalDTOS=new ArrayList<>();
        for (Personal personal:personals){
            PersonalDTO personalDTO=personalrDtoMapper.fromPersonal(personal);
            personalDTOS.add(personalDTO);
        }
        *
         */
        return personalDTOS;
    }
	  @Override
    public PersonalDTO getPersonal(Long personalId) throws PersonalNotFoundException {
        Personal personal = personalRepository.findById(personalId)
                .orElseThrow(() -> new PersonalNotFoundException("Personal Not found"));
        return personalDtoMapper.fromPersonal(personal);
    }

	  @Override
    public PersonalDTO updatePersonal(PersonalDTO personalDTO ,Long userId) {
        log.info("update new Personal");
        User personalUser =userRepository.findById(userId).orElse(null);
        Personal personal =personalDtoMapper.fromPersonalDTO(personalDTO);
        personal.setUserPersonal(personalUser);
        Personal savedPersonal = personalRepository.save(personal);
        return personalDtoMapper.fromPersonal(savedPersonal);
    }
	  @Override
    public void deletePersonal(Long personalId){
        personalRepository.deleteById(personalId);
    }


//    public List<PersonalDTO> searchPersonals(String keyword) {
//        List<Personal> personals=personalRepository.searchPersonal(keyword);
//        List<PersonalDTO> personalDTOS = personals.stream().map(cust -> personalDtoMapper.fromPersonal(cust)).collect(Collectors.toList());
//        return personalDTOS;
//    }
	
	
}
