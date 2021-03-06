package com.shoppingcart.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.query.Query;

public class PaginationResult <E>{ // E = ProductInfo
	
	private int totalRecords;
	
	private int currentPage;
	
	private List<E> list;
	
	private int maxResult;
	
	private int totalPages;
	
	private int maxNavigationPages;
	
	private List<Integer> navigationPages;
	
	public PaginationResult() {
		super();
	}

	public PaginationResult(int totalRecords, int currentPage, List<E> list, int maxResult, int totalPages,
			int maxNavigationPages, List<Integer> navigationPages) {
		super();
		this.totalRecords = totalRecords;
		this.currentPage = currentPage;
		this.list = list;
		this.maxResult = maxResult;
		this.totalPages = totalPages;
		this.maxNavigationPages = maxNavigationPages;
		this.navigationPages = navigationPages;
	}



	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getMaxNavigationPages() {
		return maxNavigationPages;
	}

	public void setMaxNavigationPages(int maxNavigationPages) {
		this.maxNavigationPages = maxNavigationPages;
	}

	public List<Integer> getNavigationPages() {
		return navigationPages;
	}

	public void setNavigationPages(List<Integer> navigationPages) {
		this.navigationPages = navigationPages;
	}

	public PaginationResult(Query query, int page, int maxResult, int maxNavigationPage) {
		int pageIndex = page - 1< 0 ? 0 : page -1;
		int fromRecordIndex = pageIndex * maxResult;
		int maxRecordIndex = fromRecordIndex + maxResult;
		
		ScrollableResults resultScroll = query.scroll(ScrollMode.SCROLL_INSENSITIVE);
		
		List<E> results = new ArrayList<E>();
		
		boolean hasResult = resultScroll.first();
		
		if (hasResult) {
			// Cuon toi vi tri:
			hasResult = resultScroll.scroll(fromRecordIndex); // 0
			if (hasResult) {
				do {
					E record = (E) resultScroll.get(0); // ProductInfo record = (ProductInfo)resultScroll.get(0)
					results.add(record); // result.add(record);
				} while (resultScroll.next() && resultScroll.getRowNumber() >= fromRecordIndex 
						&& resultScroll.getRowNumber() < maxRecordIndex);
			}
			
//			for (int i = fromRecordIndex; i < maxRecordIndex; i++) {
//				E record = (E) resultScroll.get(i); // ProductInfo record = (ProductInfo)resultScroll.get(0)
//				results.add(record); // result.add(record);
//			}
			
			
			// Chuy???n t???i b???n ghi cu???i
			resultScroll.last();
		}
		// T???ng s??? b???n ghi.
		this.totalRecords = resultScroll.getRowNumber() + 1;
		this.currentPage = pageIndex + 1;
		this.list = results;
		this.maxResult = maxResult;
		
		this.totalPages = (this.totalRecords / this.maxResult)+ 1;
		this.maxNavigationPages = maxNavigationPage;
		if(maxNavigationPage < this.totalPages) {
			this.maxNavigationPages = maxNavigationPage;
		}
		this.calcNavigationPages();
	}
	
	private void calcNavigationPages() {
		
		this.navigationPages = new ArrayList<Integer>();
		
		int current = this.currentPage > this.totalPages ? this.totalPages : this.currentPage;
		
		int begin = current - this.maxNavigationPages / 2;
		int end = current + this.maxNavigationPages /2;
		
		// Trang ?????u ti??n
		this.navigationPages.add(1);
		if (begin >2) {
			// D??ng cho '...'
			this.navigationPages.add(-1);
		}
		
		for (int i = begin; i < end; i ++) {
			if (i > 1 && i < this.totalPages) {
				this.navigationPages.add(i);
			}
		}
		
		if (end < this.totalPages -2 ) {
			// D??ng cho '...'
			this.navigationPages.add(-1);
		}
		// Trang cu???i c??ng.
		this.navigationPages.add(this.totalPages);
	}

}
