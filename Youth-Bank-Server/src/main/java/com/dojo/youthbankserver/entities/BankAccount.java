package com.dojo.youthbankserver.entities;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.dojo.youthbankserver.enums.AccountStatus;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 4)
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class BankAccount {

	
		@Id
	    private String id;
	    private double balance;
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date createdAt;
	    @Enumerated(EnumType.STRING)
	    private AccountStatus status;
	    @ManyToOne
	    private Customer customer;

	    @OneToMany(mappedBy = "bankAccount",fetch = FetchType.LAZY)
	    private List<AccountOperation> accountOperations;
	    
	    
//		----- methods ---

		@PrePersist
		protected void onCreate() {
			this.createdAt = new Date();
		}
}
