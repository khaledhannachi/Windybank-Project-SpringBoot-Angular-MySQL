package com.dojo.youthbankserver.dtos;

import java.util.List;

import lombok.Data;

@Data
public class AccountHistoryDTO {
	
    private String accountId;
    private double balance;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<AccountOperationDTO> accountOperationDTOS;
	

    
    
}
