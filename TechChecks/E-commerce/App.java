import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orders.add(OrderTestDataFactory.getRandomOrder());
        }

        OrderAnalyticsService service = new OrderAnalyticsService();

        System.out.println("Revenue Leakage: " + service.calculateRevenueLeakage(orders) + "\n");
        System.out.println("Revenue Per Category: " + service.calculateRevenuePerCategory(orders) + "\n");
        System.out.println("Top Product Revenue Per Category" + service.findTopProductPerCategory(orders) + "\n");
        System.out.println("Zero Revenue Customers" + service.findZeroRevenueCustomers(orders) + "\n");
    }
}
