package com.dojo.youthbankserver.repositories;


import com.dojo.youthbankserver.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query("SELECT p FROM Product p WHERE p.productsinvoice = :invoiceId")
//    List<Product> findProductsByInvoiceId(Long invoiceId);

}
