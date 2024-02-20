package com.dojo.youthbankserver.services;

import java.util.List;

import com.dojo.youthbankserver.dtos.ProfessionalDTO;
import com.dojo.youthbankserver.exceptions.ProfessionalNotFoundException;

public interface ProfessionalService {
	
	ProfessionalDTO saveProfessional(ProfessionalDTO professionalDTO);
  	
    List<ProfessionalDTO> listProfessionals();
 
    ProfessionalDTO getProfessional(Long professionalId) throws ProfessionalNotFoundException;

    ProfessionalDTO updateProfessional(ProfessionalDTO professionalDTO);

    void deleteProfessional(Long professionalId);

//    List<ProfessionalDTO> searchProfessionals(String keyword);
}
