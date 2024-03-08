package com.dojo.youthbankserver.repositories;


import com.dojo.youthbankserver.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
