package com.cg.Dao;

import java.math.BigDecimal;

import com.cg.Dto.Customer;

public interface WalletDao {

	 public boolean save(Customer customer);
		
		public Customer findOne(String mobileNo);
}
