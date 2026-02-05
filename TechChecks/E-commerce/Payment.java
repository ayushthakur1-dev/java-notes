public class Payment {
    private final PaymentMode mode;
    private final PaymentStatus status;
    private final double amountPaid;

    public Payment(PaymentMode mode, PaymentStatus status, double amountPaid) {
        this.mode = mode;
        this.status = status;
        this.amountPaid = amountPaid;
    }

    public PaymentMode getMode() {
        return mode;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public double getAmountPaid() {
        return amountPaid;
    }
}