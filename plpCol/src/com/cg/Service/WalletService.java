package com.cg.Service;

import java.math.BigDecimal;

import com.cg.Dto.Customer;
import com.cg.Exception.LowBalance;
import com.cg.Exception.WalletException;

public interface WalletService {

	public Customer createAccount(String name ,String mobileno, BigDecimal amount);
	public Customer showBalance (String mobileno);
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount);
	public Customer depositAmount (String mobileNo,BigDecimal amount );
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws LowBalance;
	public boolean validateMobileNo(String mobileNo);
	public boolean isValidamount(BigDecimal amount);
	public boolean isValidName(String name) throws WalletException;
}
