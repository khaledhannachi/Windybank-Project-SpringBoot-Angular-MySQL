package com.dojo.youthbankserver.controllers;

import com.dojo.youthbankserver.dtos.BusinessDTO;
import com.dojo.youthbankserver.dtos.PersonalDTO;
import com.dojo.youthbankserver.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dojo.youthbankserver.exceptions.PersonalNotFoundException;
import com.dojo.youthbankserver.services.PersonalServiceImpl;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/v1/personals")
@AllArgsConstructor
public class PersonalController {

    private PersonalServiceImpl personalServiceImpl;
    @GetMapping("")
    public ResponseEntity<List<PersonalDTO>> personals(){
        return ResponseEntity.ok().body(personalServiceImpl.listPersonals());
    }

    @GetMapping("/personal/{id}")
    public ResponseEntity<PersonalDTO> getPersonal(@PathVariable(name = "id") Long personalId) throws PersonalNotFoundException {
        return ResponseEntity.ok().body(personalServiceImpl.getPersonal(personalId));
    }
    @PostMapping("/newpersonal/{userId}")
    public ResponseEntity<PersonalDTO> savePersonal(@Valid  @RequestBody PersonalDTO personalDTO, @PathVariable Long userId){
        return ResponseEntity.ok().body(personalServiceImpl.savePersonal(personalDTO,userId));
    }
    @PutMapping("/{personalId}/{userId}/editpersonal")
    public ResponseEntity<PersonalDTO> updatePersonal(@Valid @PathVariable Long personalId, @RequestBody PersonalDTO personalDTO,@PathVariable Long userId){
        personalDTO.setId(personalId);
        return ResponseEntity.ok().body(personalServiceImpl.updatePersonal(personalDTO,userId));
    }
    @DeleteMapping("/{id}")
    public void deletePersonal(@PathVariable Long id){
        personalServiceImpl.deletePersonal(id);
    }

  @GetMapping("/user/personal/{id}")
  public ResponseEntity<PersonalDTO> getPersonalByUserId(@PathVariable(name = "id") Long id) throws UserNotFoundException {
    return ResponseEntity.ok().body(personalServiceImpl.getPersonalByUserId(id));
  }
}
