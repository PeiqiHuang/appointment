package product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Page;

public class ProductServiceImpl implements ProductService {

	private ProductDAO productDAO;
	private BuyerDAO buyerDAO;
	private BuyDAO buyDAO;
	
	private List<String> labels;
	
	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	public BuyerDAO getBuyerDAO() {
		return buyerDAO;
	}

	public void setBuyerDAO(BuyerDAO buyerDAO) {
		this.buyerDAO = buyerDAO;
	}

	public BuyDAO getBuyDAO() {
		return buyDAO;
	}

	public void setBuyDAO(BuyDAO buyDAO) {
		this.buyDAO = buyDAO;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> list =  productDAO.findAll();
		Set<String> set =  new HashSet<String>();
		if (labels == null) {
			for (Product p : list) {
				String l = p.getLabel();
				String[] arr = l.split("，");
				for (String s : arr) {
					if (!s.equals(""))
						set.add(s);
				}
			}
			labels = new ArrayList<String>(set);
//			labels.add(0, "全部");
			// java.util.Collections.sort(labels);
		}
		return list;
	}

	@Override
	public void saveOrder(List<Buy> buies, Buyer buyer) {
		// 删除就购买记录
		if (buyer.getId()!=null) {
			for(Buy buy : buyerDAO.findById(buyer.getId()).getBuies()) {
//				System.out.println("Delete buy " + buy.getProduct().getName());
				buyDAO.delete(buy);
			}
		}
		buyerDAO.save(buyer);
		for (Buy buy : buies) {
			buy.setBuyer(buyer);
			buyDAO.save(buy);
			buy.setProduct(productDAO.findById(buy.getProduct().getId()));
		}
	}

	@Override
	public List<Buyer> searchBuyer(String searchPattern) {
		
		return buyerDAO.findByPattern(searchPattern);
	}

	@Override
	public List<Buyer> getThisWeekBuyers() {
		return buyerDAO.findAll();
	}

	@Override
	public List<Buyer> getBuyersByDate(String date) {
		return buyerDAO.getBuyersByDate(date);
	}

	@Override
	public Buyer findBuyerById(Integer buyerid) {
		return buyerDAO.findById(buyerid);
	}

	@Override
	public void deleteBuyer(Integer buyerid) {
		buyerDAO.delete(buyerDAO.findById(buyerid));
	}

	@Override
	public List<Product> getValidProducts() {
		return productDAO.getValidProducts();
	}

	@Override
	public void changeSelectStatus(List<Integer> ids, String col,
			Integer valid) {
		for (Integer id : ids) {
			Buyer b = buyerDAO.findById(id);
			if ("paid".equals(col)) {
				b.setPaid(valid);
			} else if ("sent".equals(col)) {
				b.setSent(valid);
			}
			buyerDAO.save(b);
		}
	}

	@Override
	public Product findProductById(Integer id) {
		return productDAO.findById(id);
	}

	@Override
	public void saveProduct(Product product) {
		productDAO.save(product);
	}

	@Override
	public void delProduct(Integer productId) {
		Product product = findProductById(productId);
		productDAO.delete(product);
	}

	@Override
	public List<String> getLabels() {
		if (labels == null) {
			getAllProducts();
		}
//		System.out.println("labels = " + labels);
		return labels;
	}

	@Override
	public List<Product> getProductsByLabel(String label) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Buy> getBuiesByProductIds(List<Integer> ids) {
		List<Buy> list = new ArrayList<Buy>();
		for(Product p : productDAO.findByIds(ids)) {
			list.add(new Buy(p, 1));
		}
		return list;
	}

	@Override
	public Buyer createBuyerByProductIds(List<Integer> ids) {
		int price = 0;
		Set<Buy> list = new HashSet<Buy>();
		for(Product p : productDAO.findByIds(ids)) {
			list.add(new Buy(p, 1));
			price += p.getPrice();
		}
		return new Buyer(price, list);
	}

	@Override
	public Page queryByPage(int currentPage) {
		return productDAO.queryByPage(currentPage);
	}

}
