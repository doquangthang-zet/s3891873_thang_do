package vn.edu.rmit.individual_project;

public abstract class Coupon {
    private Product product;
    private String couponCode;

    public Coupon(Product p, String couponCode) {
        this.product = p;
        this.couponCode = couponCode;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    @Override
    public abstract String toString();
}
