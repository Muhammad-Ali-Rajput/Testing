package com.smallworld;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.smallworld.data.Transaction;

public class TransactionDataFetcher {

	/**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {    	
    	List<Transaction> listOfAccounHolder = new ArrayList<Transaction>(Transaction.buildAccountList());

    	Double totalTranAmonut = listOfAccounHolder.stream().mapToDouble(Transaction :: getAmount)
								   .reduce(0,(a1,a2)-> a1+a2);		
		System.out.println("The sum of the amounts of all transactions ->  "+totalTranAmonut);
		
		return totalTranAmonut;
		
        //throw new UnsupportedOperationException();
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
    	List<Transaction> listOfAccounHolder = new ArrayList<Transaction>(Transaction.buildAccountList());
    	
    	double totalSentAmount = listOfAccounHolder.stream()
                .filter(transaction -> transaction.getSender().equalsIgnoreCase(senderFullName))
                .mapToDouble(Transaction::getAmount)
                .sum();

        System.out.println("Total sum of sent transaction amounts by " + senderFullName + ": " + totalSentAmount);

        return totalSentAmount;
        //throw new UnsupportedOperationException();
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
    	List<Transaction> listOfAccounHolder = new ArrayList<Transaction>(Transaction.buildAccountList());
    	double highestTransactionAmount = listOfAccounHolder.stream().
    			mapToDouble(Transaction::getAmount).max().getAsDouble();
    	
    	System.out.println("Highest transaction amount: " + highestTransactionAmount);
    	return highestTransactionAmount;
        //throw new UnsupportedOperationException();
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
    	List<Transaction> listOfAccounHolder = new ArrayList<Transaction>(Transaction.buildAccountList());
		Set<String> uniqueClients = new HashSet<>();
    	listOfAccounHolder.forEach(t -> {
    		uniqueClients.add(t.getSender());
    		uniqueClients.add(t.getReceiver());
    	});
    	
    	long numberOfUniqueClients = uniqueClients.size();
    	
    	System.out.println("Number Of Unique Clients (sent or received a transaction) : "+numberOfUniqueClients);
    	
    	return numberOfUniqueClients;
        	
        //throw new UnsupportedOperationException();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
    	
    	List<Transaction> listOfAccounHolder = new ArrayList<Transaction>(Transaction.buildAccountList());
    	listOfAccounHolder.stream()
    	.filter(t-> t.getSender().equalsIgnoreCase(clientFullName) && t.getCompliance().equals("UnSolved"))
    	.collect(Collectors.toList())
    	.forEach(transaction -> System.out.println("Client: " + transaction.getSender() + ", Complian: " + transaction.getCompliance()));
    	
    	return true;
        //throw new UnsupportedOperationException();
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, Transaction> getTransactionsByBeneficiaryName() {
    	List<Transaction> listOfAccounHolder = new ArrayList<Transaction>(Transaction.buildAccountList());
    	
    	System.out.println("All transactions indexed by beneficiary name.");
        Map<String, List<Transaction>> transactionsByBeneficiary =
        		listOfAccounHolder.stream()
        		.collect(Collectors.groupingBy(Transaction::getReceiver));

       transactionsByBeneficiary.forEach((beneficiary, beneficiaryTransactions) -> {
            System.out.println("Beneficiary: " + beneficiary);
            System.out.println("Beneficiary transactions count : " + beneficiaryTransactions.size());
            double amount = beneficiaryTransactions.stream().mapToDouble(Transaction :: getAmount)
			   .reduce(0,(a1,a2)-> a1+a2);
            System.out.println("Beneficiary received amount : " + amount);
            //beneficiaryTransactions.forEach(transaction -> System.out.println("  " + transaction));
        });
    	
        return null;
        //throw new UnsupportedOperationException();
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
    	List<Transaction> listOfAccounHolder = new ArrayList<Transaction>(Transaction.buildAccountList());
      	
        listOfAccounHolder.stream().filter(t-> t.getCompliance().equals("UnSolved"))
    	.collect(Collectors.toList())
    	.forEach(transaction -> System.out.println("Client: " + transaction.getSender() 
    	+ ", Complian: " + transaction.getAccountNumber()));
		return null;
        
       // throw new UnsupportedOperationException();
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
    	List<Transaction> listOfAccounHolder = new ArrayList<Transaction>(Transaction.buildAccountList());
      	
        listOfAccounHolder.stream().filter(t-> t.getCompliance().equals("Solved"))
    	.collect(Collectors.toList())
    	.forEach(transaction -> System.out.println("Client: " + transaction.getSender() 
    	+ ", Complian: " + transaction.getCompliance()));
        
        //throw new UnsupportedOperationException();
        
        return null;
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<Transaction> getTop3TransactionsByAmount() {
    	List<Transaction> listOfAccounHolder = new ArrayList<Transaction>(Transaction.buildAccountList());
    	
        List<Transaction> top3Transactions = listOfAccounHolder.stream()
       .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
       .limit(3).collect(Collectors.toList());

        System.out.println(" The 3 transactions with the highest amount sorted by amount descending. ");
        top3Transactions.forEach(System.out::println);
        
        //throw new UnsupportedOperationException();
        
        return top3Transactions;
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender() {
    	List<Transaction> listOfAccounHolder = new ArrayList<Transaction>(Transaction.buildAccountList());
    	
    	System.out.println("The senderFullName of the sender with the most total sent amount.");
        List<Transaction> topTransactions = listOfAccounHolder.stream()
       .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
       .limit(1).collect(Collectors.toList());
        topTransactions.forEach(System.out::println);
        //throw new UnsupportedOperationException();
        return null;        
    }
    
	public static void main(String args[]) {
			
			TransactionDataFetcher tdf = new TransactionDataFetcher();
			
			System.out.println(" <==========Task 01 =================================================================> ");
				tdf.getTotalTransactionAmount();
			System.out.println(" <========== END of Task 01 ==========================================================> ");
	
			System.out.println(" <==========Task 02 ==================================================================> ");
			  	tdf.getTotalTransactionAmountSentBy("Scarlett");
			System.out.println(" <========== END of Task 02 ==========================================================> ");
	
			System.out.println(" <==========Task 03 ==================================================================> ");
			 	tdf.getMaxTransactionAmount();
			System.out.println(" <========== END of Task 03 ==========================================================> ");
	
			System.out.println(" <==========Task 04 ==================================================================> ");
				tdf.countUniqueClients();
			System.out.println(" <========== END of Task 04 ==========================================================> ");
			
			System.out.println(" <==========Task 05 ==================================================================> ");
				tdf.hasOpenComplianceIssues("Scarlett");
			System.out.println(" <========== END of Task 05 ==========================================================> ");
			
			System.out.println(" <==========Task 06 ==================================================================> ");
				tdf.getTransactionsByBeneficiaryName();
			System.out.println(" <========== END of Task 06 ==========================================================> ");
	
			System.out.println(" <==========Task 07 ==================================================================> ");
				tdf.getUnsolvedIssueIds();
			System.out.println(" <========== END of Task 07 ==========================================================> ");
	
			System.out.println(" <==========Task 08 ==================================================================> ");
				tdf.getAllSolvedIssueMessages();
			System.out.println(" <========== END of Task 08 ==========================================================> ");
			
			System.out.println(" <==========Task 09 ==================================================================> ");
				tdf.getTop3TransactionsByAmount();
			System.out.println(" <========== END of Task 09 ==========================================================> ");
	
			System.out.println(" <==========Task 10 ==================================================================> ");
				tdf.getTopSender();
			System.out.println(" <========== END of Task 10 ==========================================================> ");
		
		}

}
