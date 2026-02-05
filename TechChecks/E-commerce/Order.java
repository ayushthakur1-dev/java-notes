import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Order {
    private final long orderId;
    private final String customerId;
    private final String customerName;
    private final LocalDateTime orderDate;
    private final OrderStatus status;
    private final Payment payment;
    private final List<OrderItem> items;
    private final Optional<Coupon> coupon;

    public Order(long orderId, String customerId, String customerName, LocalDateTime orderDate,
        OrderStatus status, Payment payment, List<OrderItem> items, Optional<Coupon> coupon) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.status = status;
        this.payment = payment;
        this.items = items;
        this.coupon = coupon;
    }

    public long getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Payment getPayment() {
        return payment;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Optional<Coupon> getCoupon() {
        return coupon;
    }
}