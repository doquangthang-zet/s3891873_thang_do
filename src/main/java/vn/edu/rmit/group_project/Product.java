package vn.edu.rmit.group_project;

/**
 * @author <Group 2>
 * s3891873 - Do Quang Thang
 * s3965673 - Phung Hoang Long
 * s3924489 - Du Tuan Vu
 * s3930338 - Nhat Mn
 */

public abstract class Product {
    //Product attribites
    private String name;
    private String description;
    private int quantityAvailable;
    private double price;

    private TaxType tax;

    //Constructor
    public Product(String name, String description, int quantityAvailable, double price, int taxType) {
        this.name = name;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        if(taxType == 3) {
            tax = new LuxuryTax();
        } else if (taxType == 2) {
            tax = new NormalTax();
        } else {
            tax = new TaxFree();
        }
    }

    //Abstract method
    @Override
    public abstract String toString();

    public String getName() {
        return name;
    }

    //Setter and getter
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TaxType getTax() {
        return tax;
    }

    public void setTax(TaxType tax) {
        this.tax = tax;
    }

    @Override
    public int hashCode() {
        return this.name.length();
    }

    /**
   * compare two product objects
   * <p>
   * Two product objects are equal if they contain the same names
   * otherwise, return false
   * </p>
   * @param obj an object that compare with
   * @return true if two products have the same names, otherwise, return false
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
        Product p = (Product) obj;

        // Compare product names and return accordingly
        return this.name.equals(p.name);
    }
}
