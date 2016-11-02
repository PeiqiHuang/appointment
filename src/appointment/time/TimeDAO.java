package appointment.time;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for Time
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see appointment.time.Time.Time
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TimeDAO {
	private static final Logger log = LoggerFactory.getLogger(TimeDAO.class);
	// property constants
	public static final String TIME = "time";
	public static final String DESC = "desc";
	public static final String COUNT = "count";

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

	public void save(Time transientInstance) {
		log.debug("saving Time instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Time persistentInstance) {
		log.debug("deleting Time instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Time findById(java.lang.Integer id) {
		log.debug("getting Time instance with id: " + id);
		try {
			Time instance = (Time) getCurrentSession().get("appointment.time.Time",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Time> findByExample(Time instance) {
		log.debug("finding Time instance by example");
		try {
			List<Time> results = (List<Time>) getCurrentSession()
					.createCriteria("appointment.time.Time").add(create(instance))
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
		log.debug("finding Time instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Time as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Time> findByTime(Object time) {
		return findByProperty(TIME, time);
	}

	public List<Time> findByDesc(Object desc) {
		return findByProperty(DESC, desc);
	}

	public List<Time> findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	public List findAll() {
		log.debug("finding all Time instances");
		try {
			String queryString = "from Time";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Time merge(Time detachedInstance) {
		log.debug("merging Time instance");
		try {
			Time result = (Time) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Time instance) {
		log.debug("attaching dirty Time instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Time instance) {
		log.debug("attaching clean Time instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TimeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TimeDAO) ctx.getBean("TimeDAO");
	}
}