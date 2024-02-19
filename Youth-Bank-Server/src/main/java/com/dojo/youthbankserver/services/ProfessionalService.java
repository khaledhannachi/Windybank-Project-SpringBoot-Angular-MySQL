package com.dojo.youthbankserver.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dojo.youthbankserver.dtos.ProfessionalDTO;
import com.dojo.youthbankserver.entities.Professional;
import com.dojo.youthbankserver.exceptions.ProfessionalNotFoundException;
import com.dojo.youthbankserver.mappers.ProfessionalMapper;
import com.dojo.youthbankserver.repositories.AccountOperationRepository;
import com.dojo.youthbankserver.repositories.BankAccountRepository;
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
public class ProfessionalService {

	private ProfessionalRepository professionalRepository;
    private ProfessionalMapper professionalDtoMapper;


    public ProfessionalDTO saveProfessional(ProfessionalDTO professionalDTO) {
//        log.info("Saving new Professional");
        Professional professional=professionalDtoMapper.fromProfessionalDTO(professionalDTO);
        Professional savedProfessional = professionalRepository.save(professional);
        return professionalDtoMapper.fromProfessional(savedProfessional);
    }

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

   
    public ProfessionalDTO getProfessional(Long professionalId) throws ProfessionalNotFoundException {
        Professional professional = professionalRepository.findById(professionalId)
                .orElseThrow(() -> new ProfessionalNotFoundException("Professional Not found"));
        return professionalDtoMapper.fromProfessional(professional);
    }


    public ProfessionalDTO updateProfessional(ProfessionalDTO professionalDTO) {
//        log.info("Saving new Professional");
        Professional professional=professionalDtoMapper.fromProfessionalDTO(professionalDTO);
        Professional savedProfessional = professionalRepository.save(professional);
        return professionalDtoMapper.fromProfessional(savedProfessional);
    }

    public void deleteProfessional(Long professionalId){
        professionalRepository.deleteById(professionalId);
    }
  

//    public List<ProfessionalDTO> searchProfessionals(String keyword) {
//        List<Professional> professionals=professionalRepository.searchProfessional(keyword);
//        List<ProfessionalDTO> professionalDTOS = professionals.stream().map(cust -> professionalDtoMapper.fromProfessional(cust)).collect(Collectors.toList());
//        return professionalDTOS;
//    }
	
	
}
