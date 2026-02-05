
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderAnalyticsService extends AbstractOrderAnalyticsService {
    @Override
    public List<String> findZeroRevenueCustomers(List<Order> orders) {
        return orders.stream()
            .filter(x -> !x.getPayment().getStatus().equals(PaymentStatus.SUCCESS))
            .map(Order::getCustomerName)
            .distinct()
            .toList();
    }

    @Override
    public double calculateRevenueLeakage(List<Order> orders) {
        return orders.stream()
            .filter(
                x -> x.getPayment().getStatus().equals(PaymentStatus.FAILED)
                || x.getStatus().equals(OrderStatus.CANCELLED)
            )
            .mapToDouble((x) -> x.getPayment().getAmountPaid())
            .sum();
    }

    @Override
    public Map<String, Double> findTopProductPerCategory(List<Order> orders) {
        List<OrderItem> allOrderList = orders.stream()
            .map(Order::getItems)
            .flatMap(x -> x.stream())
            .toList();

        Map<String, Double> topProductPerCategory = allOrderList.stream()
            .collect(
                Collectors.toMap(
                    OrderItem::getCategory,
                    item -> item.getUnitPrice() * item.getQuantity(),
                    Math::max
                )
            );

        return topProductPerCategory;
    }

    @Override
    public Map<String, Double> calculateRevenuePerCategory(List<Order> orders) {
        return orders.stream()
            .map(Order::getItems)
            .flatMap(x -> x.stream())
            .toList()
            .stream()
            .collect(
                Collectors.toMap(
                    OrderItem::getCategory,
                    item -> item.getQuantity() * item.getUnitPrice(),
                    (x, y) -> x + y
                )
            );
    }
}
