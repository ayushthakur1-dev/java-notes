
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class OrderTestDataFactory {
    private static final List<String> CUSTOMER_NAMES = List.of(
        "Ayush Thakur",
        "Vaibhav J",
        "Anurag S", 
        "Vipul Kumar", 
        "Sameer Kumar", 
        "Omkar Chaturvedi", 
        "Aayush Sharma");

    private static final List<OrderStatus> ORDER_STATUS = List.of(
        OrderStatus.CANCELLED, 
        OrderStatus.DELIVERED, 
        OrderStatus.NEW, 
        OrderStatus.PROCESSING);

    private static final List<PaymentMode> PAYMENT_MODES = List.of(
        PaymentMode.CARD,
        PaymentMode.CASH_ON_DELIVERY,
        PaymentMode.NET_BANKING,
        PaymentMode.UPI
    );

    private static final List<PaymentStatus> PAYMENT_STATUS = List.of(
        PaymentStatus.FAILED,
        PaymentStatus.PENDING,
        PaymentStatus.SUCCESS
    );

    private static final List<String> PRODUCT_NAME = List.of(
        "face-wash",
        "sun-screen",
        "milk",
        "chips",
        "painting",
        "table-cloth",
        "chocolate",
        "pencil",
        "dumbbell",
        "laptop",
        "oats"
    );

    private static final List<String> CATEGORY_NAME = List.of(
        "skin-care",
        "food",
        "home-decor",
        "gym",
        "education"
    );
    
    public static Order getRandomOrder() {
        Random rand = new Random();

        return new Order(
            rand.nextLong(10) + 1, 
            String.valueOf(rand.nextLong(10) + 1),
            CUSTOMER_NAMES.get(rand.nextInt(CUSTOMER_NAMES.size())), 
            LocalDateTime.now(), 
            ORDER_STATUS.get(rand.nextInt(ORDER_STATUS.size())), 
            new Payment(
                PAYMENT_MODES.get(rand.nextInt(PAYMENT_MODES.size())), 
                PAYMENT_STATUS.get(rand.nextInt(PAYMENT_STATUS.size())), 
                rand.nextDouble(9999.99 - 50.99 + 1.00) + 50.99
            ), 
            List.of(
                new OrderItem(
                    String.valueOf(rand.nextLong(10) + 1), 
                    PRODUCT_NAME.get(rand.nextInt(PRODUCT_NAME.size())), 
                    CATEGORY_NAME.get(rand.nextInt(CATEGORY_NAME.size())), 
                    rand.nextInt(8) + 1, 
                    rand.nextDouble(999.99 - 50.99 + 1.00) + 50.99
                )
            ), 
            null);
    }
}
