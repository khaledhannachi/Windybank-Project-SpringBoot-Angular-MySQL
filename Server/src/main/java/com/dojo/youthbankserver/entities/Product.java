package com.dojo.youthbankserver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price= 0.00;
    private String productName;
    private String productDescription;
    private String productCategory;
    private Integer units=0;
    private boolean checked;

    // This will not allow the createdAt column to be updated after creation
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

    //many to one
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    private Business businessProducts;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "invoice_id")
//    private Invoice invoiceProducts;


    //	M:M
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "products_table",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "invoice_id")
    )
    private List<Invoice> productsinvoice;


}
