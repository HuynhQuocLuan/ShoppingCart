package com.shoppingcart.util;

import javax.servlet.http.HttpServletRequest;

import com.shoppingcart.model.CartInfo;

public class Utils {
	// Thông tin các mặt hàng đã mua, được lưu trong Session.	
	public static CartInfo getCartInfoInSession(HttpServletRequest request) {
		// Thoong tin giỏ hàng có thể đã lưu vào trong Session trước đó.
		CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("myCartInfo");
		// Nếu chưa tạo giỏ hàng, tạo nó.
		if(cartInfo == null) {
			cartInfo = new CartInfo();
			// Và luwu vào trong Session.
			request.getSession().setAttribute("myCartInfo", cartInfo);
		}
		
		return cartInfo;
	}
	
	public static void removeCartInfoInSession(HttpServletRequest request) {
		request.getSession().removeAttribute("myCartInfo");
	}
	
	public static void storeLastOrderedCartInfoInSession(HttpServletRequest request, CartInfo cartInfo) {
		request.getSession().setAttribute("lastOrderedCartInfo", cartInfo);
	}
	
	public static CartInfo getLastOrderedCartInfoInSession(HttpServletRequest request) {
		return (CartInfo) request.getSession().getAttribute("lastOrderedCartInfo");
	}

}
