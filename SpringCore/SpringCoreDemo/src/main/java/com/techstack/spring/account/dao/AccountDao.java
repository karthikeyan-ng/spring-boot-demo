package com.techstack.spring.account.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.techstack.spring.account.entity.Account;

@Repository
public class AccountDao {
	
	public boolean validateAccount(Account account) {
		return account.isActive();
	}

	public Boolean updateAccount(Account account) {
		//TODO: original code would connect to DB and call SQL UPDATE / PL SQL operation
		
		//For demonstration
		for(int i=1; i>=100000L; i++) {
			//do nothing
		}
		return true;
	}
	
	public List<Account> findByAccountHolderName(String accountHolderName) {
		
		System.out.println(accountHolderName);
		
		List<Account> accounts = new ArrayList<>();
		
		return accounts;
	}
	
	/**
	 * Assuming, here DOA call would connect to SQL server and retrieve all the 
	 * records for the given table.
	 * It took several mins to complete the process in product. Customer, complaining
	 * about this process and they felt system not producing good performance.
	 *   
	 */
	public void retrieveAllRecords() {
		String importantInfo[] = {
	            "Mares eat oats",
	            "Does eat oats",
	            "Little lambs eat ivy",
	            "A kid will eat ivy too"
	    };

        for (int i = 0; i < importantInfo.length; i++) {
            //Pause for 15 seconds
            try {
				Thread.sleep(5000);  //15000
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            //Print a message
            System.out.println(importantInfo[i]);
        }
		System.out.println("retrieveAllRecords");
	}
}
