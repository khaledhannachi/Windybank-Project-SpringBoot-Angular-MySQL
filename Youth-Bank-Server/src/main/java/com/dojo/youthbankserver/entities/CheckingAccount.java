package com.dojo.youthbankserver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor
public class CheckingAccount extends BankAccount{
	private double overDraft;

	public double getOverDraft() {
		return overDraft;
	}

	public void setOverDraft(double overDraft) {
		this.overDraft = overDraft;
	}

	
	
	
}
