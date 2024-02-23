package com.dojo.youthbankserver.dtos;



import com.dojo.youthbankserver.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor @AllArgsConstructor
public class PersonalDTO {
	   	private Long id;
		private double netPay;
		private UserDTO userPersonalDTO;

	    
   
}
