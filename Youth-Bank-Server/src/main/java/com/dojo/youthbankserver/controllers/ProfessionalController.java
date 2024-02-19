package com.dojo.youthbankserver.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dojo.youthbankserver.dtos.ProfessionalDTO;
import com.dojo.youthbankserver.exceptions.ProfessionalNotFoundException;
import com.dojo.youthbankserver.services.ProfessionalService;


import lombok.extern.slf4j.Slf4j;

@RestController


@Slf4j
@CrossOrigin("*")
public class ProfessionalController {
	@Autowired
    private ProfessionalService professionalService;
    @GetMapping("/professionals")
    public List<ProfessionalDTO> professionals(){
        return professionalService.listProfessionals();
    }
//    @GetMapping("/professionals/search")
//    public List<ProfessionalDTO> searchProfessionals(@RequestParam(name = "keyword",defaultValue = "") String keyword){
//        return professionalService.searchProfessionals("%"+keyword+"%");
//    }
    @GetMapping("/professionals/{id}")
    public ProfessionalDTO getProfessional(@PathVariable(name = "id") Long professionalId) throws ProfessionalNotFoundException {
        return professionalService.getProfessional(professionalId);
    }
    @PostMapping("/professionals")
    public ProfessionalDTO saveProfessional(@RequestBody ProfessionalDTO professionalDTO){
        return professionalService.saveProfessional(professionalDTO);
    }
    @PutMapping("/professionals/{professionalId}")
    public ProfessionalDTO updateProfessional(@PathVariable Long professionalId, @RequestBody ProfessionalDTO professionalDTO){
        professionalDTO.setId(professionalId);
        return professionalService.updateProfessional(professionalDTO);
    }
    @DeleteMapping("/professionals/{id}")
    public void deleteProfessional(@PathVariable Long id){
    	professionalService.deleteProfessional(id);
    }
}
