package com.dojo.youthbankserver.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dojo.youthbankserver.entities.Business;


public interface BusinessRepository extends JpaRepository<Business, Long> {

	
}