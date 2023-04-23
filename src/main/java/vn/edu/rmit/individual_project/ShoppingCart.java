package vn.edu.rmit.individual_project;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <Do Quang Thang - S3891873>
 */

public class ShoppingCart {
    //ShoppingCart attributes
    //use Set interfaces
    private Set <String> productNames;
    private Set <Product> products;
    private static final double BASE_FEE = 0.1; //Constant base-fee to calculate shipping fee
    private double totalWeight;
    private double totalPrice;
    private double shippingFee;
    private int id;
    private int productCount;


    /**
     * The constructor
     * @param id unique id of a shopping cart
    */
    public ShoppingCart(int id) {
        this.id = id;
        productNames = new HashSet<>();
        products = new HashSet<>();
        totalWeight = 0;
        totalPrice = 0;
        shippingFee = 0;
        productCount = 0;
    }

    /**
     * add product name to the shopping cart
     * <p>
     * Given a product, add this product name to current shopping cart.
     * If the assignment is successful, return true,
     * otherwise, return false
     * </p>
     * @param productName the product name
     * @return true if the assignment is successful, otherwise, return false
    */
    public boolean addItem(String productName) {
        /**
         * two conditions below must satisfy
         * the product object must be embbeded to the cart
         * the product name is not contained in the cart
         */
        if(products.size() <= productNames.size()) {
            return false;
        }
    
        if(productNames.contains(productName)) {
            return false;
        }

        // look for the product by name and decrease its available quantity in service
        for(Product p : products) {
            if(p.getName().equals(productName)) {
                p.setQuantityAvailable(p.getQuantityAvailable() - 1);
            }
        }

        productNames.add(productName);
        productCount++;
        totalWeight = calculateTotalWeight();
        return true;
    }

    /**
     * add product object to the shopping cart
     * <p>
     * Given a product, add this product to current shopping cart.
     * If the assignment is successful, return true,
     * otherwise, return false
     * </p>
     * @param product the product object
     * @return true if the assignment is successful, otherwise, return false
    */
    public boolean addItem(Product product) {
        /**
         * two conditions below must satisfy
         * the available quantity of the product > 0
         * the product object is not contained in the cart
         */
        if(product.getQuantityAvailable() == 0) {
            return false;
        }

        if(products.contains(product)) {
            return false;
        }

        products.add(product);
        return true;
    }

    /**
     * remove product name from the shopping cart
     * <p>
     * Given a product, remove this product name to current shopping cart.
     * If the assignment is successful, return true,
     * otherwise, return false
     * </p>
     * @param productName the product name
     * @return true if the assignment is successful, otherwise, return false
   */
    public boolean removeItem(String productName) {
        // the product name must exist in the cart
        if(!productNames.contains(productName)) {
            return false;
        }

        //increase available quantity of this product in the service
        for(Product p : products) {
            if(p.getName().equals(productName)) {
                p.setQuantityAvailable(p.getQuantityAvailable() + 1);
            }
        }

        productNames.remove(productName);
        productCount--;
        totalWeight = calculateTotalWeight();
        return true;
    }

    /**
     * remove product object from the shopping cart
     * <p>
     * Given a product, remove this product from current shopping cart.
     * If the assignment is successful, return true,
     * otherwise, return false
     * </p>
     * @param product the product object
     * @return true if the assignment is successful, otherwise, return false
    */
    public boolean removeItem(Product product) {
        //the product must exist in the cart
        if(!products.contains(product)) {
            return false;
        }

        products.remove(product);
        return true;
    }

    /**
     * calculate the total amount of all product in this shopping cart
     * <p>
     * cart amount is calculated based on the price of all product in the cart + shipping fee for physical product
     * </p>
    */
    public double cartAmount() {
        totalPrice = 0;

        for(Product p : products) {
            totalPrice += p.getPrice();
        }
        
        totalWeight = calculateTotalWeight();
        
        shippingFee = totalWeight * BASE_FEE;
        totalPrice = totalPrice + shippingFee;
        return totalPrice;
    }

    /**
     * calculate the total weight of all product in this shopping cart
     * <p>
     * total weight is calculated based on all physical product in the cart
     * </p>
    */
    public double calculateTotalWeight() {
        totalWeight = 0;
        for(Product p : products) {
            if(p instanceof PhysicalProduct) {
                totalWeight = totalWeight + ((PhysicalProduct)p).getWeight();
            }
        }
        return totalWeight;
    }

    //getter and setter
    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    /**
   * String representation of this shopping cart
   * <p>
   * This method is called automatically when you use a ShoppingCart object
   * in places where a String value is required.
   * </p>
   */
    @Override
    public String toString() {
        return "Shopping Cart ID: " + id + "\t\tTotal weight: " + totalWeight + "\t\tNumber of product: " + productCount;
    }
}
