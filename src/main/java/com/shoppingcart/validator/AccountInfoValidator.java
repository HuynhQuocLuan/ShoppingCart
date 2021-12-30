package com.shoppingcart.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shoppingcart.dao.AccountDAO;
import com.shoppingcart.entity.Account;
import com.shoppingcart.model.AccountInfo;

@Component
public class AccountInfoValidator implements Validator{
	
	@Autowired
	private AccountDAO accountDAO; 

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Account.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Account account = (Account) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.accountForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.accountForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.accountForm.username");
		
		String username = account.getUserName();
		if(username != null && username.length() > 0) {
			if(username.matches("\\s+")) {
				errors.rejectValue("userName", "Pattern.accountForm.username");
			}else if (account.isActive()) {
				Account accounta = accountDAO.getAccountByUserName(username);
				if (accounta != null) {
					errors.rejectValue("userName", "Duplicate.accountForm.username");
				}
				
			}
		}
		
		String password = account.getPassword();
		String passwordConfirm = account.getPasswordConfirm();
		
		if(!password.equals(passwordConfirm)) {
			errors.rejectValue("password", "NotMatch.accountForm.password");
		}
		
	}
	
	

}
