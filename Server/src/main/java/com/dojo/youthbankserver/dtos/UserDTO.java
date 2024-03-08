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
    private Date birthDay;
    private String countryOfBirth;
    private String street;
    private String city;
    private int zipCode;
    private String countryOfResidence;
    private String telephone;
    private String nationality;
    private String email;
    private String role;
  private Date createdAt;


}

