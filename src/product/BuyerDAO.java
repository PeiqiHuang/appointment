package product;

import static org.hibernate.criterion.Example.create;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for Buyer
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see product.Buyer
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BuyerDAO {
	private static final Logger log = LoggerFactory.getLogger(BuyerDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String PHONE = "phone";
	public static final String ADDRESS = "address";
	public static final String PRICE = "price";
	public static final String FREIGHT = "freight";
	public static final String TPRICE = "tprice";
	public static final String PAID = "paid";
	public static final String SENT = "sent";
	public static final String REMARK = "remark";

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

	public void save(Buyer transientInstance) {
		log.debug("saving Buyer instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Buyer persistentInstance) {
		log.debug("deleting Buyer instance");
		try {
			getCurrentSession().clear();
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Buyer findById(java.lang.Integer id) {
		log.debug("getting Buyer instance with id: " + id);
		try {
			Buyer instance = (Buyer) getCurrentSession().get("product.Buyer",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Buyer> findByExample(Buyer instance) {
		log.debug("finding Buyer instance by example");
		try {
			List<Buyer> results = (List<Buyer>) getCurrentSession()
					.createCriteria("product.Buyer").add(create(instance))
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
		log.debug("finding Buyer instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Buyer as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<Buyer> findByPattern(String pattern) {
		try {
			if (pattern.startsWith("id")) {
				String queryString = "from Buyer as model where model.id=?";
				pattern = pattern.replace("id", "");
				Query queryObject = getCurrentSession().createQuery(queryString);
				queryObject.setParameter(0, Integer.parseInt(pattern));
				return queryObject.list();
			} else {
				String queryString = "from Buyer as model where model.name=? or model.phone=? order by id desc";
				Query queryObject = getCurrentSession().createQuery(queryString);
				queryObject.setParameter(0, pattern).setParameter(1, pattern);
				return queryObject.list();
			}
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<Buyer> fuzzyFind(String pattern) {
		try {
			String queryString = "from Buyer as model where model.name like :pattern or model.phone like :pattern order by id desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setString("pattern", "%"+pattern+"%");
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<Buyer> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Buyer> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List<Buyer> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<Buyer> findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List<Buyer> findByFreight(Object freight) {
		return findByProperty(FREIGHT, freight);
	}

	public List<Buyer> findByTprice(Object tprice) {
		return findByProperty(TPRICE, tprice);
	}

	public List<Buyer> findByPaid(Object paid) {
		return findByProperty(PAID, paid);
	}

	public List<Buyer> findBySent(Object sent) {
		return findByProperty(SENT, sent);
	}

	public List<Buyer> findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Buyer instances");
		try {
			String queryString = "from Buyer";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Buyer merge(Buyer detachedInstance) {
		log.debug("merging Buyer instance");
		try {
			Buyer result = (Buyer) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Buyer instance) {
		log.debug("attaching dirty Buyer instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Buyer instance) {
		log.debug("attaching clean Buyer instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List getBuyersByDate(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d1);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
			Date d2 = calendar.getTime();
			String queryString = "from Buyer where date>=? and date <? order by date desc";
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
	
	public List<Buyer> getUnsentBuyers() {
		String queryString = "from Buyer where paid > 0 and sent = 0 order by date desc";
		Query queryObject = getCurrentSession().createQuery(queryString);
		return queryObject.list();
	}
	
	public List<Map<String, String>> getMonthCount(String date) {
		String sql = "select sent, count(*) count, sum(price) price, sum(tprice) tprice from `buyer` where date_format(date,'%Y-%m')=date_format(Date(\""+date+"\"), '%Y-%m') and paid>0 group by sent order by sent asc"; // date_format(now(),'%Y-%m')
		List result = getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return result;
		/*List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		System.out.println(result);
		for (String[] objs : result) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("count", objs[0]);
			m.put("price", objs[1]);
			m.put("tprice", objs[2]);
			list.add(m);
		}
		return list;*/
	}

	public static BuyerDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BuyerDAO) ctx.getBean("buyerDAO");
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		 
		BuyerDAO buyerDAO = (BuyerDAO) context.getBean("buyerDAO");
//		System.out.println(buyerDAO.findById(32).getBuies());
		System.out.println(buyerDAO.getUnsentBuyers());
//		List<Buyer> list = buyerDAO.findByPattern("8893858");
//		List<Buyer> list = buyerDAO.findByPattern("8893858");
//		System.out.println(list);
//		for (Buyer buyer : list) {
//			System.out.println(buyer.getBuies());
//			
//		}
//		Product product= new Product("p1", 1, 1);
//		Buyer buyer= new Buyer("b3", "123", "address3", 10, 8, 18, new Date(), 1, 1, "");
	}

}