package appointment.person;

import static org.hibernate.criterion.Example.create;

import java.math.BigInteger;
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
 * Person entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see appointment.appointment.person.Person
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class PersonDAO {
	private static final Logger log = LoggerFactory.getLogger(PersonDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String GENDER = "gender";
	public static final String AGE = "age";
	public static final String PHONE = "phone";
	public static final String PAID = "paid";

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

	public void save(Person transientInstance) {
		log.debug("saving Person instance");
		try {
			if (transientInstance.getDate() == null) {
				getCurrentSession().update(transientInstance);
			}
			getCurrentSession().merge(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Person persistentInstance) {
		log.debug("deleting Person instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Person findById(java.lang.Integer id) {
		log.debug("getting Person instance with id: " + id);
		try {
			Person instance = (Person) getCurrentSession().get("appointment.person.Person",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Person> findByExample(Person instance) {
		log.debug("finding Person instance by example");
		try {
			List<Person> results = (List<Person>) getCurrentSession()
					.createCriteria("appointment.person.Person").add(create(instance))
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
		log.debug("finding Person instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Person as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Person> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Person> findByGender(Object gender) {
		return findByProperty(GENDER, gender);
	}

	public List<Person> findByAge(Object age) {
		return findByProperty(AGE, age);
	}

	public List<Person> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List<Person> findByPaid(Object paid) {
		return findByProperty(PAID, paid);
	}

	public List findAll() {
		log.debug("finding all Person instances");
		try {
			String queryString = "from Person";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findToday() {
		log.debug("finding all Person instances");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(new Date());
			String queryString = "from Person where date>=? order by id";
			Query queryObject = getCurrentSession().createQuery(queryString)
					.setTimestamp(0, sdf.parse(today));
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return java.util.Collections.emptyList();
	}
	
	public List getPersonsByDate(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d1);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
			Date d2 = calendar.getTime();
			String queryString = "from Person where date>=? and date <? order by id";
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
	
	public BigInteger getMonthCount(String date) {
		String sql = "select count(*) from `person` where date_format(date,'%Y-%m')=date_format(Date(\""+date+"\"), '%Y-%m') and paid>0"; // date_format(now(),'%Y-%m')
		return (BigInteger) getCurrentSession().createSQLQuery(sql).uniqueResult();
	}
	
	public Person merge(Person detachedInstance) {
		log.debug("merging Person instance");
		try {
			Person result = (Person) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Person instance) {
		log.debug("attaching dirty Person instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Person instance) {
		log.debug("attaching clean Person instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PersonDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PersonDAO) ctx.getBean("personDAO");
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		 
		PersonDAO pDao = getFromApplicationContext(context);
		System.out.println(pDao.getMonthCount("2016-12-7"));
//		System.out.println(pDao.getPersonsByDate("2016-11-7"));
//		System.out.println(pDao.findToday());
		
//		List<Person> list = pDao.findByAge("11");
		
//		Person person = new Person();
		
//		person.setName("加法卡");
//		person.setGender(true);
//		person.setAge("12");
//		person.setPhone(5544444);
//		List<Person> list = pDao.findByExample(person);
//		System.out.println("list -> " + list.size());
		
		
//		for (Person user : list) {
//			System.out.println(user.getTime().getTime());
//		}
	}
}