package com.cg.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.Dao.WalletDao;
import com.cg.Dao.WalletDaoImpl;
import com.cg.Dto.Account;
import com.cg.Dto.Customer;
import com.cg.Exception.LowBalance;
import com.cg.Exception.WalletException;

public class WalletServiceImpl implements WalletService {
	 private WalletDao repo;

		
		public WalletServiceImpl(Map<String, Customer> data){
			repo= new WalletDaoImpl(data);
		}
		public WalletServiceImpl(WalletDao repo) 
		{
			super();
			this.repo = repo;
		}
		

		public WalletServiceImpl() 
		{
		}
		

		public Customer createAccount(String name, String mobileNo, BigDecimal amount)
		{
			Customer customer=null;
			
			try {
				if(isValidName(name) && validateMobileNo(mobileNo) && isValidamount(amount))
				{
				customer=new Customer(name,mobileNo,new Account(amount));
				if(repo.findOne(mobileNo) != null)
					throw new WalletException("The account with mobile Number "+ mobileNo+" is already created");
				repo.save(customer);
				}
			} catch (WalletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return customer;		
		}

		public Customer showBalance(String mobileNo)
		{
			Customer customer=null;
			if(validateMobileNo(mobileNo))
			{
			  customer=repo.findOne(mobileNo);
			}
			if(customer == null)
				try {
					throw new WalletException("The mobile Number You Entered is Not having Payment Wallet Account");
				} catch (WalletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return customer;
		}

		public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount)
		{
			Customer source=null;
			Customer target=null;
			try {
				if(validateMobileNo(sourceMobileNo) && validateMobileNo(targetMobileNo) && isValidamount(amount))
				{
					     if(sourceMobileNo.equals(targetMobileNo))
					    	 throw new  WalletException("Enter Different Accounts to transfer Money");
					     
					     if(amount.compareTo(new BigDecimal(0)) == 0 )
					    	 throw new WalletException("Enter valid Amount to transfer");
				         source=repo.findOne(sourceMobileNo);
				         
				         if(source == null)
				        	 throw new WalletException("There is No Payment wallet account for the Number "+sourceMobileNo);
				         
				         target=repo.findOne(targetMobileNo);
				         
				         if(target == null)
				        	 throw new WalletException("There is No Payment wallet account for the Number "+targetMobileNo);
				
				if(amount.compareTo(source.getWallet().getBalance()) > 0 )
					throw new LowBalance("Insufficient Balance in the account "+sourceMobileNo);
				/*BigDecimal srcbalance=source.getWallet().getBalance().subtract(amount);
				BigDecimal tarbalance=target.getWallet().getBalance().add(amount);
				
				source.setWallet(new Wallet(srcbalance));
				target.setWallet(new Wallet(tarbalance));*/
				
				source=withdrawAmount(sourceMobileNo, amount);
				target=depositAmount(targetMobileNo, amount);
				repo.save(source);
				repo.save(target);
				}
			} catch (WalletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LowBalance e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return source;
		}

		public Customer depositAmount(String mobileNo, BigDecimal amount)
		{
			Customer customer=null;
			try {
				if(validateMobileNo(mobileNo) && isValidamount(amount))
				{
				customer=repo.findOne(mobileNo);
				
				if(customer == null)
					throw new WalletException("There is No Payment wallet account for the Number "+mobileNo);
				
				if(amount.equals(new BigDecimal(0)))
					throw new WalletException("Enter Valid Amount to Withdraw");
				
				BigDecimal balance=customer.getWallet().getBalance().add(amount);
				customer.setWallet(new Account(balance));
				repo.save(customer);
				}
			} catch (WalletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return customer;
		}

		public Customer withdrawAmount(String mobileNo, BigDecimal amount) 
		{
			Customer customer=null;
			try {
				if(validateMobileNo(mobileNo) && isValidamount(amount))
				{
					if(amount.equals(new BigDecimal(0)))
						throw new WalletException("Enter Valid Amount to Withdraw");
					
				 customer=repo.findOne(mobileNo);
				 
				 if(customer == null)
						throw new WalletException("There is No Payment wallet account for the Number "+mobileNo);
				
				if(amount.compareTo(customer.getWallet().getBalance()) > 0 )
					throw new LowBalance("Insufficient Balance");
				
				BigDecimal balance=customer.getWallet().getBalance().subtract(amount);
				customer.setWallet(new Account(balance));
				repo.save(customer);
}
			} catch (WalletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LowBalance e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return customer;
		
	}





	public boolean isValidName(String name) throws WalletException 
	{
		if( name == null)
			throw new WalletException( "Sorry, Customer Name is null" );
		
		if( name.trim().isEmpty() )
			throw new WalletException( "Sorry, customer Name is Empty" );
		
		return true;
	}

	public boolean validateMobileNo(String mobileNo)
	{
		if( mobileNo == null ||  isPhoneNumberInvalid( mobileNo ))
			try {
				throw new WalletException( "Sorry, Phone Number "+mobileNo+" is invalid"  );
			} catch (WalletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return true;
	}

	public boolean isValidamount(BigDecimal amount)
	{
		if( amount == null || isAmountInvalid( amount ) )
			try {
				throw new WalletException( "Amount is invalid" );
			} catch (WalletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return true;
	}

	public boolean isAmountInvalid(BigDecimal amount) 
	{
		
		if( amount.compareTo(new BigDecimal(0)) < 0) 
		{
			return true;
		}		
		else 
			return false;
	}

	public static boolean isPhoneNumberInvalid( String phoneNumber )
	{
		if(String.valueOf(phoneNumber).matches("[1-9][0-9]{9}")) 
		{
			return false;
		}		
		else 
			return true;
	}

	}


