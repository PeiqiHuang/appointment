package util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class BaseDao {
	
	public static List queryByPage(Session session, final String hql, final Page page) {
		
		Query q = session.createQuery(hql);
		q.setFirstResult(page.getBeginIndex());
		q.setMaxResults(page.getEveryPage());
		return q.list();
	}
}
