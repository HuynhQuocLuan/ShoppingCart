package com.shoppingcart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shoppingcart.dao.AccountDAO;
import com.shoppingcart.dao.OrderDAO;
import com.shoppingcart.dao.ProductDAO;
import com.shoppingcart.entity.Account;
import com.shoppingcart.model.AccountInfo;
import com.shoppingcart.model.OrderDetailInfo;
import com.shoppingcart.model.OrderInfo;
import com.shoppingcart.model.PaginationResult;
import com.shoppingcart.model.ProductInfo;
import com.shoppingcart.validator.AccountInfoValidator;
import com.shoppingcart.validator.ProductInfoValidator;

@Controller
public class AdminController {
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private ProductInfoValidator productInfoValidator;
	
	@Autowired
	private AccountInfoValidator accountInfoValidator;
	
	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value = {"/accountInfo"}, method = RequestMethod.GET)
	public String accountInfo(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		System.out.println("username: " + userDetails.getUsername());
		System.out.println("password: " + userDetails.getPassword());
		System.out.println("enable: " + userDetails.isEnabled());
		
		model.addAttribute("userDetails", userDetails);
		return "accountInfo";
	}
	
	@RequestMapping(value = {"/orderList"}, method = RequestMethod.GET)
	public String orderList(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
//		int page =1;
//		try {
//			page = Integer.parseInt(pageStr);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
		final int MAX_RESULT = 5;
		final int MAX_NAVIGATION_PAGE = 0;
		PaginationResult<OrderInfo> paginationOrderInfos = orderDAO.getAllOrderInfos(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
		model.addAttribute("paginationOrderInfos", paginationOrderInfos);
		return "orderList";
	}
	
	@RequestMapping(value = {"/order"}, method = RequestMethod.GET)
	public String orderView(Model model, @RequestParam("orderId") String orderId) {
		OrderInfo orderInfo = null;
		if (orderId != null) {
			orderInfo = orderDAO.getOrderInfoById(orderId);
		} 
		if (orderInfo == null) {
			return "redirect:/orderList";
		}
		
		List<OrderDetailInfo> orderDetailInfos = orderDAO.getAllOrderDetailInfos(orderId);
		orderInfo.setOrderDetailInfos(orderDetailInfos);
		model.addAttribute("orderInfo", orderInfo);
		return "order";
	}
	
	// GET: show product
	@RequestMapping(value = {"/product"}, method = RequestMethod.GET)
	public String product (Model model, @RequestParam(value = "code", defaultValue = "") String code) {
		ProductInfo productInfo = null;
		if (code != null && code.length() > 0) {
			productInfo = productDAO.getProductInfoByCode(code);
		}
		if (productInfo == null) {
			productInfo = new ProductInfo();
			productInfo.setNewProduct(true);
		}
		
		model.addAttribute("productForm", productInfo);
		return "product";
	}
	
	@RequestMapping(value = {"/product"}, method = RequestMethod.POST )
//	@Transactional(propagation = Propagation.NEVER)
	public String productSave(Model model, @ModelAttribute("productForm") @Validated ProductInfo productInfo,
			BindingResult result) {
		productInfoValidator.validate(productInfo, result);
		if(result.hasErrors()) {
			return "product";
		}
		
		try {
			productDAO.saveProductInfo(productInfo);
		}catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "product";
		}
		
		return "redirect:/productList";
		
	}
	
	@RequestMapping(value = {"/list"}, method = RequestMethod.GET)
	public String getAllAccounts(Model model) {
		
		List<Account> accounts = accountDAO.getAllAcount();
		model.addAttribute("accounts", accounts);
		return "list-accounts";
		
	}
	
	@GetMapping("/new")
	public String showAddNewAccountForm(Model model) {
		Account account = new Account();
		List<String> roles = new ArrayList<String>();
		roles.add("MANAGER");
		roles.add("EMPLOYEE");
		model.addAttribute("roles", roles);
		model.addAttribute("account", account);
		return "add-new-account-form";
	}
	
	@PostMapping("/insert")
	public String insertAccount(Model model, @ModelAttribute("account") @Validated Account account, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		accountInfoValidator.validate(account, bindingResult);
		if(bindingResult.hasErrors()) {
			List<String> roles = new ArrayList<String>();
			roles.add("MANAGER");
			roles.add("EMPLOYEE");
			model.addAttribute("roles", roles);
			return "add-new-account-form";
		}
		
		boolean accountInserted = accountDAO.insertAccount(account);
		if(accountInserted) {
			System.out.println("Insert Account successfully!!!");
		}
		return "redirect:/list";
	}
	@GetMapping("/edit/{accountUserName}")
	public String showEditAccountForm(@PathVariable("accountUserName")String username, Model model) {
		Account account = accountDAO.getAccountByUserName(username);
		List<String> roles = new ArrayList<String>();
		roles.add("MANAGER");
		roles.add("EMPLOYEE");
		model.addAttribute("roles", roles);
		model.addAttribute("account", account);
		
		if(account == null) {
			System.out.println("Not found !!!");
		}
		return "edit-account-form";
	}
	@PostMapping("/update")
	public String updateAccount(@ModelAttribute("account") Account account) {
		boolean accountInserted = accountDAO.updateAccount(account);
		if(accountInserted) {
			System.out.println("Update Account successfully!!!");
		}
		return "redirect:/list";
	}
	
	@GetMapping("/delete")
	public String deleteAccount(@RequestParam("deleteUserName")String username) {
		accountDAO.deleteByUserName(username);
		return "redirect:/list";
	}

}
