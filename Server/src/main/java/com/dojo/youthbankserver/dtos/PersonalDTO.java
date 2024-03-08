package com.dojo.youthbankserver.dtos;



import com.dojo.youthbankserver.entities.BankAccount;
import com.dojo.youthbankserver.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PersonalDTO {
	   	private Long id;
		private double netPay;
		private List<BankAccountDTO> personalBankAccountsDTO;
		private UserDTO userPersonalDTO;



}
