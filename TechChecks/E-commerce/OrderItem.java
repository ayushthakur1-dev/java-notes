public class OrderItem {
    private final String productId;
    private final String productName;
    private final String category;
    private final int quantity;
    private final double unitPrice;

    public OrderItem(String productId, String productName, String category, int quantity, double unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}