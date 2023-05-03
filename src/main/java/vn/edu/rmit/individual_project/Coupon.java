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
    public int hashCode() {
        return this.couponCode.length();
    }

    /**
   * compare two project objects
   * <p>
   * Two project objects are equal if they contain the same names
   * otherwise, return false
   * </p>
   * @param obj an object that compare with
   * @return true if two projects have the same names, otherwise, return false
   */
    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true 
        if(this == obj) {
            return true;
        }
        
        /* Check if obj is an instance of Product or not
      "null instanceof [type]" also returns false */
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        // typecast obj to Product so that we can compare data members
        Coupon p = (Coupon) obj;

        // Compare product names and return accordingly
        return this.couponCode.equals(p.couponCode);
    }

    @Override
    public abstract String toString();
}
