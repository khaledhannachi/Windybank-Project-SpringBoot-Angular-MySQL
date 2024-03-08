package com.dojo.youthbankserver.controllers;



import java.util.List;

import com.dojo.youthbankserver.dtos.PersonalDTO;
import com.dojo.youthbankserver.exceptions.PersonalNotFoundException;
import com.dojo.youthbankserver.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dojo.youthbankserver.dtos.ProfessionalDTO;
import com.dojo.youthbankserver.exceptions.ProfessionalNotFoundException;
import com.dojo.youthbankserver.services.ProfessionalServiceImpl;


import lombok.extern.slf4j.Slf4j;




@Slf4j
@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api/v1/professionals")

public class ProfessionalController {

    private ProfessionalServiceImpl professionalServiceImpl;
    @GetMapping("")
    public ResponseEntity<List<ProfessionalDTO>> professionals(){
        return ResponseEntity.ok().body(professionalServiceImpl.listProfessionals());
    }
//    @GetMapping("/professionals/search")
//    public List<ProfessionalDTO> searchProfessionals(@RequestParam(name = "keyword",defaultValue = "") String keyword){
//        return professionalService.searchProfessionals("%"+keyword+"%");
//    }
    @GetMapping("professional/{id}")
    public ResponseEntity<ProfessionalDTO> getProfessional(@PathVariable(name = "id") Long professionalId) throws ProfessionalNotFoundException {
        return ResponseEntity.ok().body(professionalServiceImpl.getProfessional(professionalId));
    }
    @PostMapping("/newprofessional/{userId}")
    public ResponseEntity<ProfessionalDTO> saveProfessional(@Valid @RequestBody ProfessionalDTO professionalDTO, @PathVariable Long userId) throws ProfessionalNotFoundException {
        return ResponseEntity.ok().body(professionalServiceImpl.saveProfessional(professionalDTO,userId));
    }
    @PutMapping("/{professionalId}/{userId}/editprofessional")
    public ResponseEntity<ProfessionalDTO> updateProfessional( @Valid @PathVariable Long professionalId, @RequestBody ProfessionalDTO professionalDTO,@PathVariable Long userId){
        professionalDTO.setId(professionalId);
        return ResponseEntity.ok().body(professionalServiceImpl.updateProfessional(professionalDTO,userId));
    }
    @DeleteMapping("/{id}")
    public void deleteProfessional(@PathVariable Long id){
    	professionalServiceImpl.deleteProfessional(id);
    }

  @GetMapping("/user/professional/{id}")
  public ResponseEntity<ProfessionalDTO> getProfessionalByUserId(@PathVariable(name = "id") Long id)  {
    return ResponseEntity.ok().body(professionalServiceImpl.getProfessionalByUserId(id));
  }
}
