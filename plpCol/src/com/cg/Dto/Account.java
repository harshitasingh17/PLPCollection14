package com.cg.Dto;

import java.math.BigDecimal;

public class Account {

	private BigDecimal avail_Balance;

	public Account(BigDecimal amount) {
		this.avail_Balance=amount;
	}

	public BigDecimal getBalance() {
		return avail_Balance;
	}

	public void setBalance(BigDecimal balance) {
		this.avail_Balance = balance;
	}

	@Override
		public String toString() {
		return ", balance="+avail_Balance;
	}

}
