package com.dojo.youthbankserver.mappers;


import com.dojo.youthbankserver.dtos.PersonalDTO;
import com.dojo.youthbankserver.entities.Personal;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PersonalMapper {

    public PersonalDTO fromPersonal(Personal personal){
        PersonalDTO personalDTO =new PersonalDTO();
        BeanUtils.copyProperties(personal, personalDTO);
        return personalDTO;
    }
    public Personal fromPersonalDTO(PersonalDTO personalDTO){
        Personal personal =new Personal();

        BeanUtils.copyProperties(personalDTO, personal);
        return personal;
    }

	

}
