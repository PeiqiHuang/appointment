package order;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 	* A data access object (DAO) providing persistence and search support for Order entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see order.Order
  * @author MyEclipse Persistence Tools 
 */
    @Transactional   
public class OrderDAO  {
	     private static final Logger log = LoggerFactory.getLogger(OrderDAO.class);
		//property constants
	public static final String BUYER = "buyer";
	public static final String STATUS = "status";



    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
       this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession(){
     return sessionFactory.getCurrentSession(); 
    }
	protected void initDao() {
		//do nothing
	}
    
    public void save(Order transientInstance) {
        log.debug("saving Order instance");
        try {
            getCurrentSession().merge(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Order persistentInstance) {
        log.debug("deleting Order instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Order findById( java.lang.Integer id) {
        log.debug("getting Order instance with id: " + id);
        try {
            Order instance = (Order) getCurrentSession()
                    .get("order.Order", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
        /*String sql = "select * from `order` where id = ?";
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, id);
		query.setResultTransformer(new SimpleColumnToBean(
				Order.class));
		return query.list() == null ? null : (Order)query.list().get(0);*/
    }
    
    
    public List<Order> findByExample(Order instance) {
        log.debug("finding Order instance by example");
        try {
            List<Order> results = (List<Order>) getCurrentSession() .createCriteria("order.Order").add( create(instance) ).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Order instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Order as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Order> findByBuyer(Object buyer
	) {
		return findByProperty(BUYER, buyer
		);
	}
	
	public List<Order> findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	

	public List findAll() {
		log.debug("finding all Order instances");
		try {
			String queryString = "from Order";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Order merge(Order detachedInstance) {
        log.debug("merging Order instance");
        try {
            Order result = (Order) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Order instance) {
        log.debug("attaching dirty Order instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Order instance) {
        log.debug("attaching clean Order instance");
        try {
                      	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public List<Order> findbyDate(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d1);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
			Date d2 = calendar.getTime();
			String queryString = "from Order where date>=? and date <? order by date desc";
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
	
	public static OrderDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OrderDAO) ctx.getBean("orderDAO");
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		OrderDAO dao = getFromApplicationContext(ctx);
		System.out.println(dao.findById(1));
	}
}