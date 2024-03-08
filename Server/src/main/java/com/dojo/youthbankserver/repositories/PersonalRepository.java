package com.dojo.youthbankserver.repositories;

import com.dojo.youthbankserver.entities.Business;
import com.dojo.youthbankserver.entities.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PersonalRepository extends JpaRepository<Personal, Long> {
  @Query("SELECT p FROM Personal p WHERE p.userPersonal.id = :userId")
  List<Personal> findPersonalsByUserId(Long userId);

}
