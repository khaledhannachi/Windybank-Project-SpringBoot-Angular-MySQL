package com.dojo.youthbankserver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

//	M:M
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "products_table",
			joinColumns = @JoinColumn(name = "invoice_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	private List<Product> choosedProducts = new ArrayList<>();

	//many to one
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_id")
	private Business businessInvoices;

}
