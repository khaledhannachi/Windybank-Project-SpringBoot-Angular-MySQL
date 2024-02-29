package com.dojo.youthbankserver.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dojo.youthbankserver.entities.Business;


public interface BusinessRepository extends JpaRepository<Business, Long> {

    @Query("SELECT b FROM Business b WHERE b.businessLegalResponsible.id = :userId")
    List<Business> findBusinessesByUserId(Long userId);
}