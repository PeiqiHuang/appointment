package express;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import util.KdniaoTrackQueryAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class ExpressAction extends ActionSupport {
	
	public String list() {
		return SUCCESS;
	}
	
	public String listAjax() {
		list = expressService.findByDate(date);
		return SUCCESS;
	}
	
	public String searchAjax() {
		list = expressService.search(pattern);
		return SUCCESS;
	}
	
	public String commit() {
		String info = expressService.commit(inserted, updated, deleted);
		msg.put("status", StringUtils.isBlank(info));
		msg.put("info", info);
		return SUCCESS;
	}
	
	public String trackQuery() throws Exception {
		result = expressService.trackQuery(id);
		return SUCCESS;
	}
	
	private ExpressService expressService;
	private String date;
	private String pattern;
	private List<Express> list;
	private List<Express> inserted;
	private List<Express> updated;
	private List<Express> deleted;
	private Map<String, Object> msg = new HashMap<String, Object>();
	private String result;
	private Integer id;
	
	public ExpressService getExpressService() {
		return expressService;
	}
	public void setExpressService(ExpressService expressService) {
		this.expressService = expressService;
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
	public List<Express> getList() {
		return list;
	}
	public void setList(List<Express> list) {
		this.list = list;
	}

	public List<Express> getInserted() {
		return inserted;
	}

	public void setInserted(List<Express> inserted) {
		this.inserted = inserted;
	}

	public List<Express> getUpdated() {
		return updated;
	}

	public void setUpdated(List<Express> updated) {
		this.updated = updated;
	}

	public List<Express> getDeleted() {
		return deleted;
	}

	public void setDeleted(List<Express> deleted) {
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
	
}
