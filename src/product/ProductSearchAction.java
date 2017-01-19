package product;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opensymphony.xwork2.ActionSupport;

import express.Express;
import express.ExpressService;

public class ProductSearchAction extends ActionSupport {
	
	public String searchResult() throws Exception {
		List<Buyer> list = productService.searchBuyer(searchPattern);
		if (list.isEmpty()) {
			searchPattern = "没有查询到记录";
			return "search";
		}
		buyer = list.get(0);
		buies = new ArrayList<Buy>(list.get(0).getBuies());
		//快递信息
		if (buyer.getSent() > 0) {
			express = expressService.findByNameAndPhone(buyer.getName(), buyer.getPhone());
			trackList = expressService.getTrackList(express);
		}
		return SUCCESS;
	}
	
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private ExpressService expressService;
	
	public ExpressService getExpressService() {
		return expressService;
	}

	public void setExpressService(ExpressService expressService) {
		this.expressService = expressService;
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
	
	private Express express;

	public Express getExpress() {
		return express;
	}

	public void setExpress(Express express) {
		this.express = express;
	}
	
	private List<Map<String, String>> trackList = new ArrayList<Map<String,String>>();

	public List<Map<String, String>> getTrackList() {
		return trackList;
	}

	public void setTrackList(List<Map<String, String>> trackList) {
		this.trackList = trackList;
	}
	
}
