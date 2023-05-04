package vn.edu.rmit.group_project;

public class DigitalGiftProduct extends Product implements UsedAsGifts {
    private String message;

    /**
     * The constructor is not inherited
     * but we can access it with "super"
     * @param name name of product
     * @param description description of the product
     * @param quantityAvailable quantity of product
     * @param price price of product
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
   * This method is called automatically when you use a PartTimeStudent
   * object in places where a String value is required.
   * </p>
   */
    @Override
    public String toString() {
        return "Digital - " + "Name: " + this.getName() + "\t\tDescription: " + this.getDescription() + "\t\tAvailable Quantity: " + this.getQuantityAvailable() + "\t\tPrice: " + this.getPrice() + "\t\tMessage: " + this.getMessage() + "\t\tTax type: " + this.getTax().getName();
    }
}
