package vn.edu.rmit.group_project;

/**
 * @author <Group 2>
 * s3891873 - Do Quang Thang
 * s3965673 - Phung Hoang Long
 * s3924489 - Du Tuan Vu
 * s3930338 - Nhat Mn
 */

 
public class DigitalGiftProduct extends Product implements UsedAsGifts {
    private String message;

    /**
     * The constructor is not inherited
     * but we can access it with "super"
     * @param name name of product
     * @param description description of the product
     * @param quantityAvailable quantity of product
     * @param price price of product
     * @param t Taxtype of product
    */
    public DigitalGiftProduct(String name, String description, int quantityAvailable, double price, int t) {
        super(name, description, quantityAvailable, price, t);
    }

    @Override
    public void setMessage(String msg) {
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
   * String representation of this digital product
   * <p>
   * This method is called automatically when you use a DigitalGiftProduct
   * object in places where a String value is required.
   * </p>
   */
    @Override
    public String toString() {
        return "Digital Gift - " + "Name: " + this.getName() + "\t\t\tDescription: " + this.getDescription() + "\nAvailable Quantity: " + this.getQuantityAvailable() + "\t\tPrice: " + this.getPrice() + "\t\tMessage: " + this.getMessage() + "\t\tTax type: " + this.getTax().getName();
    }
}
