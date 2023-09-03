package com.smallworld.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transaction {
	
	 private Integer accountNumber;
	 private String sender;
	 private String receiver;
	 private Double amount;
	 private boolean hasComplianceIssue;
	 private String compliance;
	 
	 
	 
	public Transaction(Integer accountNumber, String sender, String receiver, Double amount,
			boolean hasComplianceIssue,String compliance) {
		this.accountNumber = accountNumber;
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.hasComplianceIssue = hasComplianceIssue;
		this.compliance = compliance;
	}


	public Integer getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getReceiver() {
		return receiver;
	}


	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public boolean isHasComplianceIssue() {
		return hasComplianceIssue;
	}


	public void setHasComplianceIssue(boolean hasComplianceIssue) {
		this.hasComplianceIssue = hasComplianceIssue;
	}
	
	public String getCompliance() {
		return compliance;
	}


	public void setCompliance(String compliance) {
		this.compliance = compliance;
	}
	

	public static List<Transaction> buildAccountList() {
		List<Transaction> accountList = new ArrayList<>();
		
		accountList.add(new Transaction(1, "Mike", "Recv-Mike",5000d,true, "Solved"));
		accountList.add(new Transaction(2, "John","Recv-Joe", 7000d,true, "Solved"));
		accountList.add(new Transaction(3, "Mary", "Recv-Mike",400d,false, "Solved"));
		accountList.add(new Transaction(4, "Joe", "Recv-Mike",25000d,true, "UnSolved"));
		accountList.add(new Transaction(5, "Nicole", "Recv-Alice",2100d,false, "Solved"));
		accountList.add(new Transaction(6, "Alice", "Recv-Mike",22000d,false, "Solved"));
		accountList.add(new Transaction(7, "Bob", "Recv-Mike",35000d,true, "Solved"));
		accountList.add(new Transaction(8, "Scarlett", "Recv-Mike",3800d,false, "UnSolved"));
		accountList.add(new Transaction(8, "Scarlett", "Recv-John",800d,false, "Solved"));
		accountList.add(new Transaction(2, "John", "Recv-Nicole",14000d,false, "UnSolved"));
		
	    return accountList;
	}

	@Override
	public String toString() {
		return "Transaction [accountNumber=" + accountNumber + ", sender=" + sender + ", receiver=" + receiver
				+ ", amount=" + amount + ", hasComplianceIssue=" + hasComplianceIssue + ", compliance=" + compliance
				+ "]";
	}

	
}
