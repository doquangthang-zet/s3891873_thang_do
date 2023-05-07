package vn.edu.rmit.group_project;

/**
 * @author <Group 2>
 * s3891873 - Do Quang Thang
 * s3965673 - Phung Hoang Long
 * s3924489 - Du Tuan Vu
 * s3930338 - Nhat Mn
 */

 
public abstract class Coupon {
    //Coupon attribites
    private Product product;
    private String couponCode;

    //Constructor
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
    public int hashCode() {
        return this.couponCode.length();
    }

    /**
   * compare two coupon objects
   * <p>
   * Two coupon objects are equal if they contain the same names
   * otherwise, return false
   * </p>
   * @param obj an object that compare with
   * @return true if two coupons have the same names, otherwise, return false
   */
    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true 
        if(this == obj) {
            return true;
        }
        
        /* Check if obj is an instance of Coupon or not
      "null instanceof [type]" also returns false */
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        // typecast obj to Coupon so that we can compare data members
        Coupon p = (Coupon) obj;

        // Compare coupon names and return accordingly
        return this.couponCode.equals(p.couponCode);
    }

    @Override
    public abstract String toString();
}
