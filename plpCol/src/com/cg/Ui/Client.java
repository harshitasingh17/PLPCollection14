package com.cg.Ui;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.cg.Dto.Customer;
import com.cg.Exception.LowBalance;
import com.cg.Exception.WalletException;
import com.cg.Service.WalletService;
import com.cg.Service.WalletServiceImpl;

public class Client {

	private WalletService walletService;
	private Map<String,Customer> data=new HashMap<String, Customer>();
	public Client() 
	{
		System.out.println("Welcome to Payment Wallet Application");
		walletService=new WalletServiceImpl(data);
	}
	
	
	
	public void Operations() throws WalletException, LowBalance
	{
		System.out.println("1) Customer Registration");
		System.out.println("2) Check Balance");
		System.out.println("3) Transfer Funds");
		System.out.println("4) Deposit Money");
		System.out.println("5) Withdraw Money");
		System.out.println("6) Exit");
		System.out.println();
		System.out.println("Enter Your Choice");
		
		Scanner sc=new Scanner(System.in);
		
		String mobileNo;
		String mobileNo1;
		BigDecimal amount;
		String name;
		Customer customer;
		switch (sc.nextInt()) 
		{
			case 1: 
				
					System.out.print("Enter Your Name: ");
					name=sc.next();
					
					System.out.print("Enter Your Mobile Number: ");
					mobileNo=sc.next();
					
					System.out.print("Enter Balance: ");
					amount=sc.nextBigDecimal();
				
			Customer customer1=walletService.createAccount(name, mobileNo, amount);
			
			System.out.println("Account of "+customer1.getName()+" created ");
			System.out.println(customer1);
				 
					break;
			case 2: 
				
					
				
				  System.out.print("Enter the Mobile Number: ");
				  mobileNo=sc.next();
				
			customer=walletService.showBalance(mobileNo);
			System.out.println("Current Balance"+customer.getWallet().getBalance());
			
			break;
			
					
			
        case 3: 
				
				System.out.print("Enter sender's Mobile Number: ");
				mobileNo=sc.next();
				
				System.out.print("Enter receiver's mobile number: ");
				mobileNo1=sc.next();
				
				System.out.print("Enter the amount  : ");
				amount=sc.nextBigDecimal();
			customer=walletService.fundTransfer(mobileNo, mobileNo1, amount);
			System.out.println("Fund transfered successfully ");
			System.out.println("Current Balance "+customer.getWallet().getBalance());
			
			break;
			
        case 4: 
			
			System.out.print("Enter the Mobile Number: ");
			mobileNo=sc.next();
			
			System.out.print("Enter amount to be deposited: ");
			amount=sc.nextBigDecimal();
		    customer=walletService.depositAmount(mobileNo, amount);
			System.out.println("Amount Deposited ");
			System.out.println("Current Balance "+customer.getWallet().getBalance());
		    break;
     case 5: 
			
			System.out.print("Enter the Mobile Number: ");
			mobileNo=sc.next();
			
			System.out.print("Enter the amount to be withdrawn: ");
			amount=sc.nextBigDecimal();
		    try 
		    {
		    	customer=walletService.withdrawAmount(mobileNo, amount);
		    	System.out.println("Amount withdrawn... ");
		    	System.out.println("Current Balance "+customer.getWallet().getBalance());
		    } 
		    catch (LowBalance e)
		    {
		    	System.out.println(e.getMessage());				
		    }
		
		break;
		
     case 6: System.out.println("successfully exited");
             System.exit(0);
             break;


		default: System.out.println("You Entered an Invalid Option");
			break;
		}
	}
	   public static void main( String[] args ) throws LowBalance
	   {
		   Client client=new Client();
		   while(true)
			try {
				client.Operations();
			} catch (WalletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
}

