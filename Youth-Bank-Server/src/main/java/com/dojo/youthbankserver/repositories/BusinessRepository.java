package com.dojo.youthbankserver.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dojo.youthbankserver.entities.Business;


public interface BusinessRepository extends JpaRepository<Business, Long> {

}