package com.dojo.youthbankserver.repositories;

import java.util.List;

import com.dojo.youthbankserver.entities.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dojo.youthbankserver.entities.Business;
import com.dojo.youthbankserver.entities.Professional;



public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

  @Query("SELECT p FROM Professional p WHERE p.userProfessional.id = :userId")
  List<Professional> findProfessionalByUserId(Long userId);
}
