package product;


import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class ProductSearchAction extends ActionSupport {
	
	public String searchResult() {
		List<Buyer> list = productService.searchBuyer(searchPattern);
		if (list.isEmpty()) {
			searchPattern = "没有查询到记录";
			return "search";
		}
		buyer = list.get(0);
		buies = new ArrayList<Buy>(list.get(0).getBuies());
		return SUCCESS;
	}
	
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private String searchPattern;

	public String getSearchPattern() {
		return searchPattern;
	}

	public void setSearchPattern(String searchPattern) {
		this.searchPattern = searchPattern;
	}
	
	private List<Buy> buies = new ArrayList<Buy>();

	public List<Buy> getBuies() {
		return buies;
	}

	public void setBuies(List<Buy> buies) {
		this.buies = buies;
	}

	private Buyer buyer;

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
}
