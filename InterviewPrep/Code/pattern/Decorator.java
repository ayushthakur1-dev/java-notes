// it is a structural design pattern used to add behaviour at runtime
// lets say we have to implement discount -> coupon, birthday, membership and festival
// in order to apply combination of coupon we have to make 15 classes in total that is not
// convinient and will lead to class explosion in our codebase
// to solve this decorator pattern was introduced

class Decorator {
    public static void main(String[] args) {
        Product product = new Product("Laptop", 100000);

        Price discountPrice = new DiscountDecorator(product);
        discountPrice = new FestivalDiscount(discountPrice);
        discountPrice = new BirthdayDiscount(discountPrice);
        discountPrice = new CouponDiscount(discountPrice);
        discountPrice = new MembershipDiscount(discountPrice);

        System.out.println(discountPrice.getPrice());
    }
}

interface Price {
    double getPrice();
}

class Product implements Price{
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class DiscountDecorator implements Price {
    private double price;

    public DiscountDecorator(Price product) {
        this.price = product.getPrice();
    }

    public double getPrice() {
        return price;
    }
}

class FestivalDiscount extends DiscountDecorator {
    private double price;

    public FestivalDiscount(Price price) {
        super(price);
    }

    public double getPrice() {
        return super.getPrice() * 0.95;
    }
}

class MembershipDiscount extends DiscountDecorator {
    private double price;

    public MembershipDiscount(Price price) {
        super(price);
    }

    public double getPrice() {
        return super.getPrice() * 0.90;
    }
}

class BirthdayDiscount extends DiscountDecorator {
    private double price;

    public BirthdayDiscount(Price price) {
        super(price);
    }

    public double getPrice() {
        return super.getPrice() - 100;
    }
}

class CouponDiscount extends DiscountDecorator {
    private double price;

    public CouponDiscount(Price price) {
        super(price);
    }

    public double getPrice() {
        return super.getPrice() - 50;
    }
}