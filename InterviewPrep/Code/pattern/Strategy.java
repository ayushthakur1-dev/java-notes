// it is a behavioural desgin pattern used to decide which strategy to use from a
// family of algorithm at runtime
// family of algorithms are those related algorithms that are interchangable with each other
// eg. we can give comparator in Collections.sort() and Arrays.sort() to decide the order of elements
class Strategy {
    public static void main(String[] args) {
        PaymentGateway gateway = new PaymentGateway();
        gateway.proceedPayment(new Card());
    }
}

interface Payment {
    void pay();
}

class Cash implements Payment {
    public void pay() {
        System.out.println("paying through cash");
    }
}

class Card implements Payment {
    public void pay() {
        System.out.printf("paying through card");
    }
}

class PaymentGateway {
    public void proceedPayment(Payment paymentMethod) {
        paymentMethod.pay();
    }
}