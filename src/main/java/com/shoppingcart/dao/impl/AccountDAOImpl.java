package com.shoppingcart.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingcart.dao.AccountDAO;
import com.shoppingcart.entity.Account;
import com.shoppingcart.model.AccountInfo;

@Repository
@Transactional
public class AccountDAOImpl implements AccountDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public Account getAccountByUserName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT ACC FROM Account as ACC WHERE ACC.userName = :USERNAME";
		Query<Account> query = session.createQuery(hql);
		query.setParameter("USERNAME", userName);
		Account accountInfo = (Account) query.uniqueResult();
		return accountInfo;
	}

	@Override
	public List<Account> getAllAcount(){
		Session session = sessionFactory.getCurrentSession();
		String hql_select = "SELECT c FROM Account as c";
		Query query = session.createQuery(hql_select);
		List<Account> accounts = query.getResultList();
		
		return accounts;
	}

	@Override
	public boolean insertAccount(Account account) {
		try {
			Session session = sessionFactory.getCurrentSession();
			account.setPassword(encoder.encode(account.getPassword()));
			session.save(account);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateAccount(Account account) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql_update = "UPDATE Account as a SET a.name =:name_hql, a.password =:password_hql, a.userRole = :userRole_hql WHERE  a.userName = :userName_hql";
			Query query = session.createQuery(hql_update);
					
			query.setParameter("name_hql", account.getName());
			query.setParameter("password_hql", account.getPassword());
			query.setParameter("userRole_hql", account.getUserRole());
			query.setParameter("userName_hql", account.getUserName());
//			account.setPassword(encoder.encode(account.getPassword()));
			session.update(account);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean deleteByUserName(String username) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql_delete = "DELETE Account as a WHERE a.userName = :userName_hql";
			Query query = session.createQuery(hql_delete);
			query.setParameter("userName_hql", username);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

}
