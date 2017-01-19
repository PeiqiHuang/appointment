package express;

import static org.hibernate.criterion.Example.create;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Express entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see express.Express
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ExpressDAO {
	private static final Logger log = LoggerFactory.getLogger(ExpressDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String PHONE = "phone";
	public static final String NUMBER = "number";
	public static final String COMPANY = "company";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void saveOrUpdate(Express transientInstance) {
		log.debug("saving Express instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Express persistentInstance) {
		log.debug("deleting Express instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Express findById(java.lang.Integer id) {
		log.debug("getting Express instance with id: " + id);
		try {
			Express instance = (Express) getCurrentSession().get(
					"express.Express", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Express> findByExample(Express instance) {
		log.debug("finding Express instance by example");
		try {
			List<Express> results = (List<Express>) getCurrentSession()
					.createCriteria("express.Express").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Express instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Express as model where model."
					+ propertyName + "= ? order by id desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Express> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Express> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List<Express> findByNumber(Object number) {
		return findByProperty(NUMBER, number);
	}

	public List<Express> findByCompany(Object company) {
		return findByProperty(COMPANY, company);
	}

	public List findAll() {
		log.debug("finding all Express instances");
		try {
			String queryString = "from Express";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Express merge(Express detachedInstance) {
		log.debug("merging Express instance");
		try {
			Express result = (Express) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Express instance) {
		log.debug("attaching dirty Express instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Express instance) {
		log.debug("attaching clean Express instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public List<Express> findbyDate(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d1);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
			Date d2 = calendar.getTime();
			String queryString = "from Express where date>=? and date <? order by date desc";
			Query queryObject = getCurrentSession().createQuery(queryString)
					.setTimestamp(0, d1).setTimestamp(1, d2);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return java.util.Collections.emptyList();
	}

	public static ExpressDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ExpressDAO) ctx.getBean("expressDAO");
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ExpressDAO dao = getFromApplicationContext(ctx);
//		Express express = new Express("name3", "13532786923", "123456789", "中通");
//		express.setDate(new Date());
//		dao.saveOrUpdate(express );
//		System.out.println(dao.findbyDate("2017-1-17"));
		System.out.println(dao.search("咕噜"));
	}

	public List<Express> search(String pattern) {
		try {
			String queryString = "from Express where name like :name or phone = :phone";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setString("name", "%" + pattern + "%");
			queryObject.setString("phone", pattern);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}