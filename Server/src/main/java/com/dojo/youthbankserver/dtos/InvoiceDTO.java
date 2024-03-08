package com.dojo.youthbankserver.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceDTO {

    private Long id;
    private Integer discount=0;
    private Integer tva=0; // Initialize to default value
    private Double tvaamount=0.0; // Initialize to default value
    private Integer timbre=0;
    private Double totalTax=0.0;
    private Double totalAmount=0.0;
    private Double amountPaid=0.0;
    private String beneficiary;
    private String phoneNumber;
    private String email;
    private String sectorOfActivity;
    private String taxIdentificationNumber;
    private List<ProductDTO> choosedProductsDTO = new ArrayList<>() ;
    //	 This will not allow the createdAt column to be updated after creation
    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;
    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

}
