package product;


import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class ProductDeleteAction extends ActionSupport {
	

	public String execute() {
		productService.deleteBuyer(buyerid);
//		System.out.println("submit buies ->" + buies);
		return SUCCESS;
	}
	
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private Integer buyerid;

	public Integer getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(Integer buyerid) {
		this.buyerid = buyerid;
	}
	
	
}
