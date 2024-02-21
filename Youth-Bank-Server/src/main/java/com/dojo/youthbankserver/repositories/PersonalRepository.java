package com.dojo.youthbankserver.repositories;

import com.dojo.youthbankserver.entities.Personal;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonalRepository extends JpaRepository<Personal, Long> {

//	  @Query("select c from Personal c where c.name like :kw")
//	    List<Personal> searchCustomer(@Param("kw") String keyword);
}