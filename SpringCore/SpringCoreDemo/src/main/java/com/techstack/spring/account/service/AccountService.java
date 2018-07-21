package com.techstack.spring.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techstack.spring.account.dao.AccountDao;
import com.techstack.spring.account.entity.Account;

@Service
public class AccountService {
	
//	@Autowired
	private AccountDao accountDao;
	
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public boolean doValudate(Account account) {
		return accountDao.validateAccount(account);
	}
	
	public Boolean updateAccount(Account account) {
		return accountDao.updateAccount(account);
	}
	
	public List<Account> findByAccountHolderName(String accountHolderName) {
		return accountDao.findByAccountHolderName(accountHolderName);
	}
	
	public void retrieveAllRecords() {
		accountDao.retrieveAllRecords();
	}
	
}
