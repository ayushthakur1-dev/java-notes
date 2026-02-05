
import java.util.List;
import java.util.Map;

public abstract class AbstractOrderAnalyticsService {
    // Customers with no successful payments
    public abstract List<String> findZeroRevenueCustomers(List<Order> orders);

    // Revenue lost due to cancelled or failed payments
    public abstract double calculateRevenueLeakage(List<Order> orders);

    // Highest revenue product per category
    public abstract Map<String, Double> findTopProductPerCategory(List<Order> orders);

    // Total revenue per category
    public abstract Map<String, Double> calculateRevenuePerCategory(List<Order> orders);
}