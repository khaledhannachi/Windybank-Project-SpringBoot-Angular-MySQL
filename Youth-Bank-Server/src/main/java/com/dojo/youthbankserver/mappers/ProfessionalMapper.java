package com.dojo.youthbankserver.mappers;

import org.springframework.beans.BeanUtils;

import com.dojo.youthbankserver.dtos.ProfessionalDTO;
import com.dojo.youthbankserver.entities.Professional;



public class ProfessionalMapper {

	
	   public ProfessionalDTO fromProfessional(Professional professional){
		   ProfessionalDTO professionalDTO=new ProfessionalDTO();
	        BeanUtils.copyProperties(professional,professionalDTO);
	        return  professionalDTO;
	    }
	    public Professional fromProfessionalDTO(ProfessionalDTO professionalDTO){
	        Professional professional=new Professional();
	        BeanUtils.copyProperties(professionalDTO,professional);
	        return  professional;
	    }
}
