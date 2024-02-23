package com.dojo.youthbankserver.services;

import java.util.List;

import java.util.stream.Collectors;

import com.dojo.youthbankserver.dtos.PersonalDTO;
import com.dojo.youthbankserver.dtos.UserDTO;
import com.dojo.youthbankserver.entities.User;
import com.dojo.youthbankserver.mappers.UserMapper;
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
    private UserMapper userMapper;
	  @Override
      public PersonalDTO savePersonal(PersonalDTO personalDTO, Long userId) {
          log.info("Saving new Personal");

          // Retrieve the User entity
          User personalUser = userRepository.findById(userId).orElse(null);

          // Check for null and handle it appropriately
          if (personalUser == null) {
              // Handle the case where the user is not found
              // You might want to throw an exception or return an error response
              // depending on your use case
              log.error("User not found with id: {}", userId);
              // Handle the error appropriately
          }

          // Set the user information in the DTO
          personalDTO.setUserPersonalDTO(userMapper.fromUser(personalUser));

          // Map from PersonalDTO to Personal entity
          Personal personal = personalDtoMapper.fromPersonalDTO(personalDTO);

          // Set the user information in the Personal entity
          personal.setUserPersonal(userMapper.fromUserDTO(personalDTO.getUserPersonalDTO()));

          // Save the Personal entity
          Personal savedPersonal = personalRepository.save(personal);

          // Map from Personal entity to PersonalDTO and return
          PersonalDTO dto=personalDtoMapper.fromPersonal(savedPersonal);
          dto.setUserPersonalDTO(userMapper.fromUser(savedPersonal.getUserPersonal()));
          return dto;
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
        PersonalDTO dto=personalDtoMapper.fromPersonal(personal);
        dto.setUserPersonalDTO(userMapper.fromUser(personal.getUserPersonal()));
        return dto;
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
