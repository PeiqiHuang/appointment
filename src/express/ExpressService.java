package express;

import java.util.List;
import java.util.Map;

public interface ExpressService {
	List<Express> findByDate(String date);
	void saveOrUpdate(Express express);
	void delete(Express express);
	String commit(List<Express> inserted, List<Express> updated,
			List<Express> deleted);
	public Express findById(Integer id);
	List<Express> search(String pattern);
	String trackQuery(Integer pattern) throws Exception;
	Express findByNameAndPhone(String name, String phone);
	List<Map<String, String>> getTrackList(Express express) throws Exception;
	Express searchOne(String pattern);
}
