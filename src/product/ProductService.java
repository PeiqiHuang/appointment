package product;

import java.util.List;
import java.util.Map;

import util.Page;

public interface ProductService {
	
	public List<Product> getAllProducts();
	public List<Product> getValidProducts();
	public void saveOrder(List<Buy> buies, Buyer buyer);
	public List<Buyer> searchBuyer(String searchPattern);
	public List<Buyer> getThisWeekBuyers();
	public List<Buyer> getBuyersByDate(String date);
	public List<Buyer> getBuyersBySearch(String pattern);
	public Buyer findBuyerById(Integer buyerid);
	public void deleteBuyer(Integer buyerid);
	public void changeSelectStatus(List<Integer> ids, String string,
			Integer valid);
	public Product findProductById(Integer id);
	public void saveProduct(Product product);
	public void delProduct(Integer productId);
	public List<String> getLabels();
	public List<Product> getProductsByLabel(String label);
	public List<Buy> getBuiesByProductIds(List<Integer> ids);
	public Buyer createBuyerByProductIds(List<Integer> ids);
	public Page queryByPage(int currentPage);
	public Page search(String searchPattern);
	public List<Buyer> getUnsentBuyers();
	public void copyBuyer(Integer buyerid);
	public List<Map<String, String>> getMonthCount(String date);
	
}
