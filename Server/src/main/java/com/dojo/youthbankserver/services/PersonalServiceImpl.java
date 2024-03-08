package com.dojo.youthbankserver.services;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.dojo.youthbankserver.dtos.BankAccountDTO;
import com.dojo.youthbankserver.dtos.PersonalDTO;
import com.dojo.youthbankserver.entities.*;
import com.dojo.youthbankserver.mappers.BankAccountMapperImpl;
import com.dojo.youthbankserver.mappers.UserMapper;
import com.dojo.youthbankserver.repositories.UserRepository;
import org.springframework.stereotype.Service;
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
    private BankAccountMapperImpl dtoMapper;
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
//          personal.getUserPersonal().setRole("Personal");
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
//        List<PersonalDTO> personalDTOS = personals.stream()
//                .map(personal -> personalDtoMapper.fromPersonal(personal))
//                .collect(Collectors.toList());

        List<PersonalDTO> personalDTOS=new ArrayList<>();
        for (Personal personal:personals){
            PersonalDTO personalDTO=personalDtoMapper.fromPersonal(personal);
            personalDTO.setUserPersonalDTO(userMapper.fromUser(personal.getUserPersonal()));
          List<BankAccount> bankAccounts =personal.getPersonalBankAccounts();
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
            personalDTO.setPersonalBankAccountsDTO(bankAccountDTOS);
            personalDTOS.add(personalDTO);
        }
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
@Override
public PersonalDTO getPersonalByUserId(Long userId) {
  List <Personal> personals = personalRepository.findPersonalsByUserId(userId);
  PersonalDTO dto=personalDtoMapper.fromPersonal(personals.get(0));
  dto.setUserPersonalDTO(userMapper.fromUser(personals.get(0).getUserPersonal()));
  List<BankAccount> bankAccounts =personals.get(0).getPersonalBankAccounts();
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
  dto.setPersonalBankAccountsDTO(bankAccountDTOS);
  return dto;
}

}
