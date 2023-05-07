package vn.edu.rmit.group_project;

/**
 * @author <Group 2>
 * s3891873 - Do Quang Thang
 * s3965673 - Phung Hoang Long
 * s3924489 - Du Tuan Vu
 * s3930338 - Nhat Mn
 */

 
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
