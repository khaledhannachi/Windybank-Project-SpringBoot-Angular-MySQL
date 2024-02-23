package com.dojo.youthbankserver.dtos;

import com.dojo.youthbankserver.entities.Business;
import com.dojo.youthbankserver.entities.Personal;
import com.dojo.youthbankserver.entities.Professional;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Data
public class UserDTO {


    private Long id;
    private String firstName;
    private String lastName;
//	private List<Business> userBuisnessAccounts;
//	private List<Professional> userProfessionalAccounts;
//	private List<PersonalDTO> userPersonalAccounts;



}

