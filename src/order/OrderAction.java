package order;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import product.Buyer;
import product.ProductService;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class OrderAction extends ActionSupport {
	
	public String list() {
		return SUCCESS;
	}
	
	public String listAjax() {
		/*list = orderService.findByDate(date);
		System.out.println("listajax:" + list);
		for (Order order : list) {
			buyers.add(order.getBuyer());
		}*/
		List<Buyer> buyers = productService.getBuyersByDate(date);
		setResultJson(buyers);
		return SUCCESS;
	}
	
	/*public String searchAjax() {
		list = OrderService.search(pattern);
		return SUCCESS;
	}*/
	
	public String searchForm() {
		return SUCCESS;
	}
	
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private OrderService orderService;
	private String date;
	private String pattern;
	private List<Order> list;
	private List<Order> inserted;
	private List<Order> updated;
	private List<Order> deleted;
	private Map<String, Object> msg = new HashMap<String, Object>();
	private String result;
	private Integer id;
	private Order order;
	private List<Buyer> buyers = new ArrayList<Buyer>();
	
	public OrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public List<Order> getList() {
		return list;
	}
	public void setList(List<Order> list) {
		this.list = list;
	}

	public List<Order> getInserted() {
		return inserted;
	}

	public void setInserted(List<Order> inserted) {
		this.inserted = inserted;
	}

	public List<Order> getUpdated() {
		return updated;
	}

	public void setUpdated(List<Order> updated) {
		this.updated = updated;
	}

	public List<Order> getDeleted() {
		return deleted;
	}

	public void setDeleted(List<Order> deleted) {
		this.deleted = deleted;
	}

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order Order) {
		this.order = Order;
	}
	
	
	public List<Buyer> getBuyers() {
		return buyers;
	}

	public void setBuyers(List<Buyer> buyers) {
		this.buyers = buyers;
	}

	private void setResultJson(List<Buyer> buyers) {
		final Map<String,Integer> countMap = new HashMap<String,Integer>();
		countMap.put("buies", 0);
		countMap.put("buyer", 0);
		Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {  
            @Override  
            public boolean shouldSkipField(FieldAttributes f) {  
//            	System.out.println(f.getName());
            	//过滤掉buyer-buies-product-buies  
            	if ("buies".equals(f.getName())) {
            		Integer count = countMap.get("buies");
            		count++;
            		countMap.put("buies", count);
//            		System.out.println("buies count = " + count);
            		if (count > 2) {
            			return true;
            		}
            	}
            	//过滤掉buyer-buies-buyer
            	if ("buyer".equals(f.getName())) {
            		Integer count = countMap.get("buyer");
            		count++;
            		countMap.put("buyer", count);
//            		System.out.println("buyer count = " + count);
            		if (count > 0) {
            			return true;
            		}
            	}
            	
            	return false;
            }  
  
            @Override  
            public boolean shouldSkipClass(Class<?> clazz) {  
                return false;  
            }  
        }).setDateFormat("MM/dd").create(); //
		result = gson.toJson(buyers);
	}
}
