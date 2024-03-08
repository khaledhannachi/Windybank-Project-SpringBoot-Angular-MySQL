package com.dojo.youthbankserver.services;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dojo.youthbankserver.dtos.BankAccountDTO;
import com.dojo.youthbankserver.entities.*;

import com.dojo.youthbankserver.exceptions.ProfessionalNotFoundException;
import com.dojo.youthbankserver.mappers.BankAccountMapperImpl;
import com.dojo.youthbankserver.mappers.UserMapper;
import com.dojo.youthbankserver.repositories.UserRepository;
import org.springframework.stereotype.Service;

import com.dojo.youthbankserver.dtos.ProfessionalDTO;

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
    private UserMapper userMapper;
    private BankAccountMapperImpl dtoMapper;


    @Override
    public ProfessionalDTO saveProfessional(ProfessionalDTO professionalDTO, Long userId) {
        log.info("Saving new Professional");

        // Retrieve the User entity
        User professionalUser = userRepository.findById(userId).orElse(null);

        // Check for null and handle it appropriately
        if (professionalUser == null) {
            // Handle the case where the user is not found
            // You might want to throw an exception or return an error response
            // depending on your use case
            log.error("User not found with id: {}", userId);
            // Handle the error appropriately
        }

        // Set the user information in the DTO
        professionalDTO.setUserProfessionalDTO(userMapper.fromUser(professionalUser));

        // Map from PersonalDTO to Personal entity
        Professional professional = professionalDtoMapper.fromProfessionalDTO(professionalDTO);

        // Set the user information in the Personal entity
        professional.setUserProfessional(userMapper.fromUserDTO(professionalDTO.getUserProfessionalDTO()));

        // Save the Personal entity
            Professional savedProfessional  = professionalRepository.save(professional);

        // Map from Personal entity to PersonalDTO and return
        ProfessionalDTO dto=professionalDtoMapper.fromProfessional(savedProfessional);
        dto.setUserProfessionalDTO(userMapper.fromUser(savedProfessional.getUserProfessional()));
        return dto;
    }



    @Override
    public List<ProfessionalDTO> listProfessionals() {
        List<Professional> professionals = professionalRepository.findAll();
//        List<PersonalDTO> personalDTOS = personals.stream()
//                .map(personal -> personalDtoMapper.fromPersonal(personal))
//                .collect(Collectors.toList());

        List<ProfessionalDTO> professionalDTOS=new ArrayList<>();
        for (Professional professional:professionals){
            ProfessionalDTO professionalDTO=professionalDtoMapper.fromProfessional(professional);
            professionalDTO.setUserProfessionalDTO(userMapper.fromUser(professional.getUserProfessional()));
            List<BankAccount> bankAccounts =professional.getProfessionalBankAccounts();
            List<BankAccountDTO> bankAccountDTOS = bankAccounts.stream().flatMap(bankAccount -> {
                if (bankAccount instanceof SavingAccount) {
                    SavingAccount savingAccount = (SavingAccount) bankAccount;
                    return Stream.of(
                            dtoMapper.fromSavingPersonalBankAccount(savingAccount)

                    );
                } else{
                    CheckingAccount checkingAccount = (CheckingAccount) bankAccount;
                    return Stream.of(
                            dtoMapper.fromCheckingPersonalBankAccount(checkingAccount)

                    );
                }

            }).collect(Collectors.toList());
            professionalDTO.setProfessionalBankAccountsDTO(bankAccountDTOS);
            professionalDTOS.add(professionalDTO);
        }
        return professionalDTOS;
    }

    @Override
    public ProfessionalDTO getProfessional(Long professionalId) throws ProfessionalNotFoundException {
        Professional professional = professionalRepository.findById(professionalId)
                .orElseThrow(() -> new ProfessionalNotFoundException("Professional Not found"));
        ProfessionalDTO dto=professionalDtoMapper.fromProfessional(professional);
        dto.setUserProfessionalDTO(userMapper.fromUser(professional.getUserProfessional()));
        return dto;
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

  @Override
  public ProfessionalDTO getProfessionalByUserId(Long userId) {
    List <Professional> professionals = professionalRepository.findProfessionalByUserId(userId);
    ProfessionalDTO dto=professionalDtoMapper.fromProfessional(professionals.get(0));
    dto.setUserProfessionalDTO(userMapper.fromUser(professionals.get(0).getUserProfessional()));
    List<BankAccount> bankAccounts =professionals.get(0).getProfessionalBankAccounts();
    List<BankAccountDTO> bankAccountDTOS = bankAccounts.stream().flatMap(bankAccount -> {
      if (bankAccount instanceof SavingAccount) {
        SavingAccount savingAccount = (SavingAccount) bankAccount;
        return Stream.of(
          dtoMapper.fromSavingPersonalBankAccount(savingAccount)
        );
      } else{
        CheckingAccount checkingAccount = (CheckingAccount) bankAccount;
        return Stream.of(
          dtoMapper.fromCheckingPersonalBankAccount(checkingAccount)
        );
      }
    }).collect(Collectors.toList());
    dto.setProfessionalBankAccountsDTO(bankAccountDTOS);
    return dto;
  }
}
