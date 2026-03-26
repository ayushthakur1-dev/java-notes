// it is a creational design pattern, core concept is like factory pattern only that is
// to create and object without exposing which class is being instantiated but instead
// of one object it give a family of objects that are closely related or dependent on each other

class AbstractFactory {
    public static void main(String[] args) {
        PaymentGatewayFactory factory = new RazorPayGateway();
        Payment payment = factory.payment();
        Refund refund = factory.refund();

        payment.pay();
        refund.refund();
    }
}

interface Payment {
    void pay();
}

interface Refund {
    void refund();
}

class RazorPayPayment implements Payment {
    public void pay() {
        System.out.println("paying through razorpay");
    }
}

class RazorPayRefund implements Refund {
    public void refund() {
        System.out.println("refunding through razorpay");
    }
}
class CashFreePayment implements Payment {
    public void pay() {
        System.out.println("paying through cashfree");
    }
}

class CashFreeRefund implements Refund {
    public void refund() {
        System.out.println("refunding through cashfree");
    }
}

interface PaymentGatewayFactory {
    Payment payment();
    Refund refund();
}

class RazorPayGateway implements PaymentGatewayFactory {
    public Payment payment() {
        return new RazorPayPayment();
    }

    public Refund refund() {
        return new RazorPayRefund();
    }
}

class CashFreeGateway implements PaymentGatewayFactory {
    public Payment payment() {
        return new CashFreePayment();
    }

    public Refund refund() {
        return new CashFreeRefund();
    }
}