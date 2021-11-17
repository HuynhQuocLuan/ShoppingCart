package com.shoppingcart.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingcart.dao.ProductDAO;
import com.shoppingcart.entity.Product;
import com.shoppingcart.model.CartInfo;
import com.shoppingcart.model.CustomerInfo;
import com.shoppingcart.model.PaginationResult;
import com.shoppingcart.model.ProductInfo;
import com.shoppingcart.util.Utils;

@Controller
public class MainController {
	
	@Autowired
	private ProductDAO productDAO;
	
	//Danh sách sản phẩm
	@RequestMapping(value = {"/productList"}, method = RequestMethod.GET)
//	@RequestMapping({"/productList"})
	public String getAllProductInfos(Model model, @RequestParam(value = "name", defaultValue = "") String likeName, 
			@RequestParam(value = "page", defaultValue = "1") int page) {
		
		final int maxResult = 5;
		final int maxNavigationPage = 10;
		
		PaginationResult<ProductInfo> productInfos = productDAO.getAllProductInfos(page, maxResult, maxNavigationPage, likeName);
		
		
		model.addAttribute("paginationProductInfos", productInfos);
		return "productList";
		
	}
	
	@RequestMapping(value = {"/productImage"}, method = RequestMethod.GET)
	public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
				@RequestParam("code") String code) throws IOException {
		Product product = null;
		if (code != null) {
			product = productDAO.getProductByCode(code);
		}
		if (product != null && product.getImage() != null) {
			response.setContentType("image/jpeg, image/ipg, image/png, image/gif");
			response.getOutputStream().write(product.getImage());
		}
		response.getOutputStream().close();
	}
	
	@RequestMapping({"/buyProduct"})
	public String buyProductHandler(HttpServletRequest request, Model model,
			@RequestParam(value = "code", defaultValue = "") String code) {
		Product product = null;
		
		if (code != null && code.length() > 0) {
			product = productDAO.getProductByCode(code);
		}
		if (product != null) {
			// Thông tin giỏ hàng có thể đã lưu vào trong Session trước đó.
			CartInfo cartInfo = Utils.getCartInfoInSession(request);
			ProductInfo productInfo = new ProductInfo(product);
			cartInfo.addProduct(productInfo, 1);
		}
		// Chuyển sang trang danh sách các sản phẩm đã mua.
		return "redirect:/shoppingCart";
	}
	
	// GET: Hiển thị giỏ hàng.
	@RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.GET)
	public String shoppingCartHandler(HttpServletRequest request, Model model) {
		CartInfo cartInfo = Utils.getCartInfoInSession(request);
		model.addAttribute("cartForm", cartInfo);
		return "shoppingCart";
	}
	
	// POST: Update product quantity after buy 
	@RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.POST)
	public String shoppingCartUpdateQuantity(HttpServletRequest request, Model model,
			@ModelAttribute("cartForm") CartInfo cartForm) {
		CartInfo cartInfo = Utils.getCartInfoInSession(request);
		cartInfo.updateQuantity(cartForm);
		return "redirect:/shoppingCart";
	}
	
	@RequestMapping({"/shoppingCartRemoveProduct"})
	public String removeProductHandler(HttpServletRequest request, Model model,
			@RequestParam(value = "code", defaultValue = "") String code) {
		Product product = null;
		
		if(code !=null && code.length() >0) {
			product = productDAO.getProductByCode(code);
		}
		
		if (product != null) {
			// Thông tin giỏ hàng có thể đã lưu vào trong Session trước đó.
			CartInfo cartInfo = Utils.getCartInfoInSession(request);
			ProductInfo productInfo = new ProductInfo(product);
			cartInfo.removeProduct(productInfo);
		}
		// Chuyển sang trang danh sách các sản phẩm đã mua.
		return "redirect:/shoppingCart";
	}
	
	// Get: Nhập thông tin khách hàng.
	@RequestMapping(value = {"/shoppingCartCustomer"}, method = RequestMethod.GET)
	public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
		CartInfo cartInfo = Utils.getCartInfoInSession(request);
		// Chưa mua mặt hàng nào.
		if (cartInfo.isEmpty()) {
			// Chuyển tới trang danh sách giỏ hàng.
			return "redirect:/shoppingCart";	
		}
		
		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		if (customerInfo == null) {
			customerInfo = new CustomerInfo();
		}
		
		model.addAttribute("customerForm", customerInfo);
		return "shoppingCartCustomer";
	}

}
