package express;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import util.KdniaoTrackQueryAPI;

public class ExpressServiceImpl implements ExpressService {

	private ExpressDAO expressDAO;
	
	public void setExpressDAO(ExpressDAO expressDao) {
		this.expressDAO = expressDao;
	}

	@Override
	public List<Express> findByDate(String date) {
		return expressDAO.findbyDate(date);
	}

	@Override
	public void saveOrUpdate(Express express) {
		expressDAO.saveOrUpdate(express);
	}

	@Override
	public void delete(Express express) {
		expressDAO.delete(express);
	}

	@Override
	public String commit(List<Express> inserted, List<Express> updated,
			List<Express> deleted) {
		// insert
		if (inserted != null) {
			for(Express e : inserted) {
				if (null == e.getName()) return "名字不能为空";
				if (null == e.getPhone()) return "电话不能为空";
				if (null == e.getNumber()) return "单号不能为空";
				saveOrUpdate(e);
			}
		}
		// update
		if (updated != null) {
			for(Express e : updated) {
				if (null == e.getName()) return "名字不能为空";
				if (null == e.getPhone()) return "电话不能为空";
				if (null == e.getNumber()) return "单号不能为空";
				Express e0 = findById(e.getId());
				e0.setName(e.getName());
				e0.setPhone(e.getPhone());
				e0.setNumber(e.getNumber());
				e0.setCompany(e.getCompany());
				saveOrUpdate(e0);
			}
		}
		// delete
		if (deleted != null) {
			for(Express e : deleted) {
				Express e0 = findById(e.getId());
				delete(e0);
			}
		}
		return "";
	}

	@Override
	public Express findById(Integer id) {
		return expressDAO.findById(id);
	}

	@Override
	public List<Express> search(String pattern) {
		return expressDAO.search(pattern);
	}

	@Override
	public String trackQuery(Integer id) throws Exception {
		Express e = findById(id);
		return KdniaoTrackQueryAPI.get(e.getCompany(), e.getNumber().trim());
	}

	@Override
	public Express findByNameAndPhone(String name, String phone) {
		List<Express> list = expressDAO.findByPhone(phone);
		if (list.isEmpty()) {
			list = expressDAO.findByName(name);
			if (list.isEmpty()) {
				return null;
			} else {
				return list.get(0);
			}
		}
		return list.get(0);
	}

	@Override
	public List<Map<String, String>> getTrackList(Express express) throws Exception {
		List<Map<String, String>> trackList = new ArrayList<Map<String,String>>();
		if (null != express) {
			String jsonstr = trackQuery(express.getId());
		    JsonObject json = new JsonParser().parse(jsonstr).getAsJsonObject();  
		    JsonArray traces = json.get("Traces").getAsJsonArray();
		    Iterator itr = traces.iterator();
		    while (itr.hasNext()) {
				JsonObject object = (JsonObject) itr.next();
				Map<String, String> map = new HashMap<String, String>();
				map.put("time", object.get("AcceptTime").getAsString());
				map.put("station", object.get("AcceptStation").getAsString());
				trackList.add(map);
			}
		}
		return trackList;
	}

	@Override
	public Express searchOne(String pattern) {
		return expressDAO.searchOne(pattern);
	}

}
