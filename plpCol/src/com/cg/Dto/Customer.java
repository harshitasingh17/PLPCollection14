package com.cg.Dto;

public class Customer {
	private String cu_Name;
	private String cu_MobileNo;
	private Account cu_Account;
	public Customer(String name2, String mobileNo2, Account account2) {
		this.cu_Name=name2;
		cu_MobileNo=mobileNo2;
		cu_Account=account2;
	}
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return cu_Name;
	}
	public void setName(String name) {
		this.cu_Name = name;
	}

	public String getMobileNo() {
		return cu_MobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.cu_MobileNo = mobileNo;
	}
	public Account getWallet() {
		return cu_Account;
	}
	public void setWallet(Account account) {
		this.cu_Account = account;
	}
	@Override
	public String toString() {
		return "Customer name=" + cu_Name + ", mobileNo=" + cu_MobileNo
				+ cu_Account;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cu_MobileNo == null) ? 0 : cu_MobileNo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (cu_MobileNo == null) {
			if (other.cu_MobileNo != null)
				return false;
		} else if (!cu_MobileNo.equals(other.cu_MobileNo))
			return false;
		return true;
	}


}
