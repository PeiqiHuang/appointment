package product;


import java.util.ArrayList;
import java.util.List;

import appointment.user.User;
import appointment.user.UserAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class ProductShowAction extends ActionSupport implements UserAware {
	
	public String execute() {
//		List<Buyer> buies = productService.getThisWeekBuyers();
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
	
	private List<Buyer> buies = new ArrayList<Buyer>();

	public List<Buyer> getBuies() {
		return buies;
	}

	public void setBuies(List<Buyer> buies) {
		this.buies = buies;
	}
	
	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		
	}
}
