package com.dojo.youthbankserver.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
    private Long id;
    private Double price= 0.00;
    private String productName;
    private String productDescription;
    private String productCategory;
    private Integer units=0;
//    private InvoiceDTO invoiceProductsDTO;
//    private BusinessDTO businessProductsDTO;
private List<InvoiceDTO> productsinvoiceDTO;
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
