package vn.edu.rmit.group_project;

/**
 * @author <Group 2>
 * s3891873 - Do Quang Thang
 * s3965673 - Phung Hoang Long
 * s3924489 - Du Tuan Vu
 * s3930338 - Nhat Mn
 */

public class DigitalProduct extends Product {

    /**
     * The constructor is not inherited
     * but we can access it with "super"
     * @param name name of product
     * @param description description of the product
     * @param quantityAvailable quantity of product
     * @param price price of product
     * @param t Taxtype of product
    */
    public DigitalProduct(String name, String description, int quantityAvailable, double price, int t) {
        super(name, description, quantityAvailable, price, t);
    }

    /**
   * String representation of this digital product
   * <p>
   * This method is called automatically when you use a PartTimeStudent
   * object in places where a String value is required.
   * </p>
   */
    @Override
    public String toString() {
        return "Digital - " + "Name: " + this.getName() + "\t\t\tDescription: " + this.getDescription() + "\nAvailable Quantity: " + this.getQuantityAvailable() + "\t\tPrice: " + this.getPrice() + "\t\tTax type: " + this.getTax().getName();
    }
}
