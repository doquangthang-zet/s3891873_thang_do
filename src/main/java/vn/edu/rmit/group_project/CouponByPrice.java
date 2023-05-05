package vn.edu.rmit.group_project;

public class CouponByPrice extends Coupon {
    //Attributes
    private double price;

    //Constructor
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

    /**
   * String representation of this price coupon
   * <p>
   * This method is called automatically when you use a CouponByPrice
   * object in places where a String value is required.
   * </p>
   */
    @Override
    public String toString() {
        return "Coupon Code: " + this.getCouponCode() + "\tDiscount Amount: " + price + "\tFor Product: " + this.getProduct().getName();
    };
}
