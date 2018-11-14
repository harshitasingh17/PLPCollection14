package com.cg.Dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.cg.Dto.Account;
import com.cg.Dto.Customer;

public class WalletDaoImpl implements WalletDao {
	private Map<String, Customer> data; 
	public WalletDaoImpl(Map<String, Customer> data) 
	{
		super();
		this.data = data;
	}

	public boolean save(Customer customer) 
	{
		if(data.containsKey(customer.getMobileNo()))
		{
			data.replace(customer.getMobileNo(), customer);
		}
		else
			data.put(customer.getMobileNo(), customer);
	
		return true;
		
	}

	public Customer findOne(String mobileNo) 
	{
		Customer customer=null;
		
			
						
		customer=data.get(mobileNo);
		return customer;
	}

}
