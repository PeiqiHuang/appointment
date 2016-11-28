package product;

import static org.hibernate.criterion.Example.create;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import util.BaseDao;
import util.Page;
import util.PageUtil;

/**
 * A data access object (DAO) providing persistence and search support for
 * Product entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see product.Product
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ProductDAO {
	private static final Logger log = LoggerFactory.getLogger(ProductDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String PRICE = "price";
	public static final String VALID = "valid";
	public static final int PRODUCT_EVERY_PAGE = 8;
	

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

	public void save(Product transientInstance) {
		System.out.println("save " + transientInstance);
		log.debug("saving Product instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Product persistentInstance) {
		log.debug("deleting Product instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Product findById(java.lang.Integer id) {
		log.debug("getting Product instance with id: " + id);
		try {
			Product instance = (Product) getCurrentSession().get(
					"product.Product", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Product> findByIds(List<Integer> ids) {
		try {
			List<Product> results = (List<Product>) getCurrentSession()
					.createCriteria(Product.class).add(Restrictions.in("id", ids))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
		
	}

	public List<Product> findByExample(Product instance) {
		log.debug("finding Product instance by example");
		try {
			List<Product> results = (List<Product>) getCurrentSession()
					.createCriteria("product.Product").add(create(instance))
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
		log.debug("finding Product instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Product as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Product> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Product> findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List<Product> findByValid(Object valid) {
		return findByProperty(VALID, valid);
	}

	public List findAll() {
		log.debug("finding all Product instances");
		try {
			String queryString = "from Product order by valid desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int getSize() {
		log.debug("finding all Product instances");
		try {
			String queryString = "select count(*) from Product";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return ((Number)queryObject.uniqueResult()).intValue();  
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Product merge(Product detachedInstance) {
		log.debug("merging Product instance");
		try {
			Product result = (Product) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Product instance) {
		log.debug("attaching dirty Product instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Product instance) {
		log.debug("attaching clean Product instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProductDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ProductDAO) ctx.getBean("productDAO");
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductDAO productDAO = (ProductDAO) context.getBean("productDAO");
//		System.out.println(productDAO.findAll());
//		System.out.println(productDAO.queryByPage(1));
		System.out.println(productDAO.search("安敏滋"));
//		productDAO.search("优泽");
//		System.out.println(productDAO.findByName("优泽"));
		
	}

	public List<Product> getValidProducts() {
//		return findByValid(1);
		try {
			String queryString = "from Product as model where model.valid > 0 order by valid desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<String> getLabels() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Product> findProductsByLabel(String label) {
		
		return null;
	}
	
	public Page queryByPage(int currentPage) {
		Page page= PageUtil.createPage(PRODUCT_EVERY_PAGE, getSize(), currentPage);
		// System.out.println(page);
		page.setObjectList(BaseDao.queryByPage(getCurrentSession(), "from Product order by valid desc", page));
		return page;
	}

	public Page search(String searchPattern) {
		Page page= PageUtil.createPage(10000, 10000, 1);
		String hql = "from Product as model where model.name like :param or model.alias like :param order by valid desc";
		Query q = getCurrentSession().createQuery(hql);
		q.setString("param", "%" + searchPattern + "%");
//		page.setObjectList(BaseDao.queryByPage(getCurrentSession(), "from Product as model where model.name like '%"+searchPattern+"%' or model.alias like '%"+searchPattern+"%' order by valid desc", page));
		page.setObjectList(BaseDao.queryByPage(q, page));
		return page;
	}
}