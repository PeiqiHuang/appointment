package product;


import util.Page;
import appointment.user.User;
import appointment.user.UserAware;

import com.opensymphony.xwork2.ActionSupport;

public class ProductAllAction extends ActionSupport implements UserAware{
	


	public String execute() {
		// products = productService.getAllProducts();
		page = productService.queryByPage(getCurrentPage());
		System.out.println(page);
		return SUCCESS;
	}
	
	public String insert() {
//		System.out.println("productId = " + productId);
		if (productId != null) {
			product = productService.findProductById(productId);
//			System.out.println("product = " + product);
		} else {
			product = new Product();
			product.setValid(0);
		}
		return SUCCESS;
	}
	
	public String submit() {
		productService.saveProduct(product);
		return SUCCESS;
	}
	
	public String del() {
		productService.delProduct(productId);
		return SUCCESS;
	}
	
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private Integer productId;
	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	private Product product; //  = new Product()
	

//	@Override
//	public Product getModel() {
//		return product;
//	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

//	private List<Product> products;
//
//	public List<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
	
	private Integer currentPage;
	
	public Integer getCurrentPage() {
		return currentPage == null ? 1 : currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	private Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		
	}
}
