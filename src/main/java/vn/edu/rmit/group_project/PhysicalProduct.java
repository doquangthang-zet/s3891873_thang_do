package vn.edu.rmit.group_project;

/**
 * @author <Do Quang Thang - S3891873>
 */

public class PhysicalProduct extends Product {
    //Physical product attributes
    private double weight;
    // private String message;

    /**
     * The constructor is not inherited
     * but we can access it with "super"
     * @param name name of product
     * @param description description of the product
     * @param quantityAvailable quantity of product
     * @param price price of product
     * @param weight weight of physical product
    */
    public PhysicalProduct(String name, String description, int quantityAvailable, double price, double weight, int t) {
        super(name, description, quantityAvailable, price, t);
        this.weight = weight;
    }

    //get weight of product
    public double getWeight() {
        return weight;
    }

    //set weight of product
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    // @Override
    // public void setMessage(String msg) {
    //     this.message = msg;
    // }

    // @Override
    // public String getMessage() {
    //     return message;
    // }
    
    /**
   * String representation of this physical product
   * <p>
   * This method is called automatically when you use a PhysicalProduct
   * object in places where a String value is required.
   * </p>
   */
    @Override
    public String toString() {
        return "Physical - " + "Name: " + this.getName() + "\t\tDescription: " + this.getDescription() + "\t\tAvailable Quantity: " + this.getQuantityAvailable() + "\t\tPrice: " + this.getPrice() + "\t\tWeight: " + this.getWeight() + "\t\tTax type: " + this.getTax().getName();
    }
}
