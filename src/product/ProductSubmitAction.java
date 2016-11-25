package product;


import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class ProductSubmitAction extends ActionSupport {
	

	public String submit() {
		boolean flag = buyer.getId()!=null;
//		System.out.println("submit buyer ->" + buyer);
//		System.out.println("submit buies ->" + buies);
		productService.saveOrder(buies, buyer);
//		System.out.println("submit buies ->" + buies);
		if (flag || productValid.intValue() == 0)
			return "update";
		else 
			return SUCCESS;
	}
	
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
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
	
	private Integer productValid;
	
	public Integer getProductValid() {
		return productValid;
	}

	public void setProductValid(Integer productValid) {
		this.productValid = productValid;
	}
}
