package order;

import java.util.List;
import java.util.Map;

public interface OrderService {
	List<Order> findByDate(String date);
}
