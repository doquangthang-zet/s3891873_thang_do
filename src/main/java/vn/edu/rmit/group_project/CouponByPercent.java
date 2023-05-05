package vn.edu.rmit.group_project;

public class CouponByPercent extends Coupon{
    //Attributes
    private int percent;

    //Constructor
    public CouponByPercent(Product p, String couponCode, int percent) {
        super(p, couponCode);
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    /**
   * String representation of this percent coupon
   * <p>
   * This method is called automatically when you use a CouponByPercent
   * object in places where a String value is required.
   * </p>
   */
    @Override
    public String toString() {
        return "Coupon Code: " + this.getCouponCode() + "\tDiscount Percent: " + percent + "\tFor Product: " + this.getProduct().getName();
    };
}
