package com.dojo.youthbankserver.dtos;

import com.dojo.youthbankserver.entities.User;
import lombok.Data;
@Data
public class BusinessDTO {
	private Long id;
    private String commercialName;
    private String activity;
    private String taxIdentificationNumber;
    private String businessRegistration;
    private String cnssNumber;
    private String companyPhone;
    private String companyEmail;
    private User businessLegalResponsible;
}
