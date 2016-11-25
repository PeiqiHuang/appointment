package product;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

public class ProductAction extends ActionSupport {
	

	public String buy() {
		// update info
		if (null != buyerid) {
			buyer = productService.findBuyerById(buyerid);
			buies = new ArrayList<Buy>(buyer.getBuies());
		} else if (null != ids && !ids.isEmpty()) { // after selected on list page
			buyer = productService.createBuyerByProductIds(ids);
			buies = new ArrayList<Buy>(buyer.getBuies());
		}
//			System.out.println("buies = " + buies);
		
		// all product list
		if (null == productValid || productValid > 0)
			products = productService.getValidProducts();
		else 
			products = productService.getAllProducts();
//		System.out.println("buy product list ->" + products);
		
		return SUCCESS;
	}
	
	public String list() {
		products = productService.getAllProducts();
		labels = productService.getLabels();
		return SUCCESS;
	}
	
	public String submit() {
		
		return SUCCESS;
	}
	
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	private Integer buyerid;

	public Integer getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(Integer buyerid) {
		this.buyerid = buyerid;
	}
	
	private Integer productValid;
	
	public Integer getProductValid() {
		return productValid;
	}

	public void setProductValid(Integer productValid) {
		this.productValid = productValid;
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
	
	private List<String> labels;

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
	private List<Integer> ids;

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
}
