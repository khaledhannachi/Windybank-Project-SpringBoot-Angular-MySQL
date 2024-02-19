package com.dojo.youthbankserver.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dojo.youthbankserver.entities.BankAccount;



public interface BankAccountRepository extends JpaRepository<BankAccount, String> {


}