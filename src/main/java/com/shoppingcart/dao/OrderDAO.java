package com.shoppingcart.dao;

import com.shoppingcart.model.CartInfo;
import com.shoppingcart.model.OrderInfo;
import com.shoppingcart.model.PaginationResult;

public interface OrderDAO {
	
	public void saveOrder(CartInfo cartInfo);
	
	public PaginationResult <OrderInfo> getAllOrderInfos(int page, int maxResult, int maxNavigationPage); 

}
