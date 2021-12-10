package com.shoppingcart.dao;

import java.util.List;

import com.shoppingcart.entity.Account;
import com.shoppingcart.model.AccountInfo;

public interface AccountDAO {
	
	public Account getAccountByUserName(String username);
	
	public List<Account> getAllAcount();
	
	public boolean insertAccount(Account account);
	
	public boolean updateAccount(Account account);
	
	public boolean deleteByUserName(String username);
	

}
