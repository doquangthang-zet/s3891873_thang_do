package vn.edu.rmit.individual_project;

public class CouponByPercent extends Coupon{
    private int percent;

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

    @Override
    public String toString() {
        return "Code: " + this.getCouponCode() + "\tDiscount Percent: " + percent + "\tFor Product: " + this.getProduct().getName();
    };
}
