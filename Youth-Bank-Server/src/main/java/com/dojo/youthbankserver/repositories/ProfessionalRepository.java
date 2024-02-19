package com.dojo.youthbankserver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dojo.youthbankserver.entities.Professional;



public interface ProfessionalRepository extends JpaRepository<Professional, Long> {


}