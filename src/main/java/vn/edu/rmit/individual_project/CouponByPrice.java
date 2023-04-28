package vn.edu.rmit.individual_project;

public class CouponByPrice extends Coupon {
    private double price;

    public CouponByPrice(Product p, String couponCode, double price) {
        super(p, couponCode);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Code: " + super.getCouponCode() + "\tDiscount Amount: " + price;
    };
}
