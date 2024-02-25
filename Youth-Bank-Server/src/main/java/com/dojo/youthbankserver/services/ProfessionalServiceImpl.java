package com.dojo.youthbankserver.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dojo.youthbankserver.entities.User;

import com.dojo.youthbankserver.exceptions.ProfessionalNotFoundException;
import com.dojo.youthbankserver.repositories.UserRepository;
import org.springframework.stereotype.Service;

import com.dojo.youthbankserver.dtos.ProfessionalDTO;
import com.dojo.youthbankserver.entities.Professional;

import com.dojo.youthbankserver.mappers.ProfessionalMapper;
import com.dojo.youthbankserver.repositories.ProfessionalRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@AllArgsConstructor
@Data
@Slf4j
public class ProfessionalServiceImpl implements ProfessionalService{

	private ProfessionalRepository professionalRepository;
    private UserRepository userRepository;
    private ProfessionalMapper professionalDtoMapper;

    @Override
    public ProfessionalDTO saveProfessional(ProfessionalDTO professionalDTO, Long userId) throws ProfessionalNotFoundException {
        log.info("Saving new Professional");
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            log.error("User not found for userId: {}", userId);
            throw new ProfessionalNotFoundException("User not found for userId: " + userId);
        }
        User userProfessional = userOptional.get();
        Professional professional = professionalDtoMapper.fromProfessionalDTO(professionalDTO);
        professional.setUserProfessional(userProfessional);
        Professional savedProfessional = professionalRepository.save(professional);
        return professionalDtoMapper.fromProfessional(savedProfessional);
    }


    @Override
    public List<ProfessionalDTO> listProfessionals() {
        List<Professional> professionals = professionalRepository.findAll();
        List<ProfessionalDTO> professionalDTOS = professionals.stream()
                .map(professional -> professionalDtoMapper.fromProfessional(professional))
                .collect(Collectors.toList());
        /*
        List<ProfessionalDTO> professionalDTOS=new ArrayList<>();
        for (Professional professional:professionals){
            ProfessionalDTO professionalDTO=professionalDtoMapper.fromProfessional(professional);
            professionalDTOS.add(professionalDTO);
        }
        *
         */
        return professionalDTOS;
    }

	  @Override
    public ProfessionalDTO getProfessional(Long professionalId) throws ProfessionalNotFoundException {
        Professional professional = professionalRepository.findById(professionalId)
                .orElseThrow(() -> new ProfessionalNotFoundException("Professional Not found"));
        return professionalDtoMapper.fromProfessional(professional);
    }

	  @Override
    public ProfessionalDTO updateProfessional(ProfessionalDTO professionalDTO,Long userId) {
        log.info("Update new Professional");
        User userProfessional =userRepository.findById(userId).orElse(null);
        Professional professional=professionalDtoMapper.fromProfessionalDTO(professionalDTO);
        professional.setUserProfessional(userProfessional);
        Professional savedProfessional = professionalRepository.save(professional);
        return professionalDtoMapper.fromProfessional(savedProfessional);
    }
	  @Override
    public void deleteProfessional(Long professionalId){
        professionalRepository.deleteById(professionalId);
    }
  

//    public List<ProfessionalDTO> searchProfessionals(String keyword) {
//        List<Professional> professionals=professionalRepository.searchProfessional(keyword);
//        List<ProfessionalDTO> professionalDTOS = professionals.stream().map(cust -> professionalDtoMapper.fromProfessional(cust)).collect(Collectors.toList());
//        return professionalDTOS;
//    }
	
	
}
