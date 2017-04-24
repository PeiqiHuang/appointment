package product;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAjaxAction extends ActionSupport {
	
	public String getBuyersByDate() {
		List<Buyer> buyers = productService.getBuyersByDate(date);
		setResultJson(buyers);
		return SUCCESS;
	}
	
	public String getMonthCount() {
		Gson gson = new GsonBuilder().create();
		result = gson.toJson(productService.getMonthCount(date));
		return SUCCESS;
	}
	
	public String getBuyersBySearch() {
		List<Buyer> buyers = productService.getBuyersBySearch(pattern);
		setResultJson(buyers);
		return SUCCESS;
	}
	
	public String getUnsentBuyers() {
		List<Buyer> buyers = productService.getUnsentBuyers();
		setResultJson(buyers);
		return SUCCESS;
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
	
	public String changeSelectPaid() {
//		System.out.println("ids = " + ids);
//		System.out.println("valid = " + valid);
		productService.changeSelectStatus(ids, "paid", valid);
		result = "修改成功";
		return SUCCESS;
	}
	
	public String changeSelectSent() {
//		System.out.println("ids = " + ids);
//		System.out.println("valid = " + valid);
		productService.changeSelectStatus(ids, "sent", valid);
		result = "修改成功";
		return SUCCESS;
	}
	
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private String result;
	
	public String getResult() {
		return result;
	}
	

	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	private String pattern;
	
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	private List<Integer> ids;
	
	private Integer valid;

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}
	
}
