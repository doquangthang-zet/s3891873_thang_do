package vn.edu.rmit.individual_project;

import java.io.Console;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author <Do Quang Thang - S3891873>
 */

public class ShoppingCart {
    //ShoppingCart attributes
    //use Set interfaces
    private Set <String> productNames;
    private Map <Integer, Product> products;
    private Map <Integer, Integer> productsQuantity;
    private Map <Product, Integer> giftProducts;
    private Map <Integer, String> productsMsg;
    private static final double BASE_FEE = 0.1; //Constant base-fee to calculate shipping fee
    private double totalWeight;
    private double totalPrice;
    private double shippingFee;
    private double totalTax;
    private double totalCoupon;
    private int id;
    private int productCount;
    private Coupon coupon;


    /**
     * The constructor
     * @param id unique id of a shopping cart
    */
    public ShoppingCart(int id) {
        this.id = id;
        productNames = new HashSet<>();
        products = new LinkedHashMap<>();
        productsQuantity = new LinkedHashMap<>();
        giftProducts = new HashMap<>();
        productsMsg = new LinkedHashMap<>();
        totalWeight = 0;
        totalPrice = 0;
        shippingFee = 0;
        totalTax = 0;
        totalCoupon = 0;
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
    // public boolean addItem(String productName) {
    //     /**
    //      * two conditions below must satisfy
    //      * the product object must be embbeded to the cart
    //      * the product name is not contained in the cart
    //      */
    //     if(products.size() <= productNames.size()) {
    //         return false;
    //     }
    
    //     if(productNames.contains(productName)) {
    //         return false;
    //     }

    //     // look for the product by name and decrease its available quantity in service
    //     for(Product p : products.keySet()) {
    //         if(p.getName().equals(productName)) {
    //             p.setQuantityAvailable(p.getQuantityAvailable() - 1);
    //         }
    //     }

    //     productNames.add(productName);
    //     productCount++;
    //     totalWeight = calculateTotalWeight();
    //     return true;
    // }

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
    public boolean addItem(Product product, int quantity, int addChoice, int productId) {
        /**
         * two conditions below must satisfy
         * the available quantity of the product > 0
         * the product object is not contained in the cart
         */
        if(product.getQuantityAvailable() == 0 || product.getQuantityAvailable() < quantity) {
            return false;
        }

        if(addChoice == 2) {
            for (int i : products.keySet()) {
                if(productId == i) {
                    product.setQuantityAvailable(product.getQuantityAvailable() - quantity);
                    products.put(i, product);
                    productsQuantity.put(i, productsQuantity.get(i) + quantity);
                    return true;
                }
            }
            return false;
        }

        // if(product instanceof UsedAsGifts) {
        //     product.setQuantityAvailable(product.getQuantityAvailable() - quantity);

        //     Product newItems = product;
        //     ((UsedAsGifts)newItems).setMessage(msg);

        //     productCount++;
        //     totalWeight = calculateTotalWeight();

        //     giftProducts.put(newItems, quantity);
        //     return true;
        // }

        // if (products.containsKey(product)) {
        //     product.setQuantityAvailable(product.getQuantityAvailable() - quantity);

        //     productCount++;
        //     totalWeight = calculateTotalWeight();
    
        //     products.put(product, products.get(product) + quantity);
        //     return true;
        // }

        product.setQuantityAvailable(product.getQuantityAvailable() - quantity);

        productCount++;

        products.put(productCount, product);
        productsQuantity.put(productCount, quantity);

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
    // public boolean removeItem(String productName) {
    //     // the product name must exist in the cart
    //     if(!productNames.contains(productName)) {
    //         return false;
    //     }

    //     //increase available quantity of this product in the service
    //     for(Product p : products.keySet()) {
    //         if(p.getName().equals(productName)) {
    //             p.setQuantityAvailable(p.getQuantityAvailable() + 1);
    //         }
    //     }

    //     productNames.remove(productName);
    //     productCount--;
    //     totalWeight = calculateTotalWeight();
    //     return true;
    // }

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
    public boolean removeItem(Product product, int quantity, int productId) {
        //the product must exist in the cart
        if(!products.containsKey(productId)) {
            return false;
        }

        for(int i: products.keySet()) {
            if(products.get(i) == product && i == productId) {
                product.setQuantityAvailable(product.getQuantityAvailable() + quantity);


                if(quantity == productsQuantity.get(productId)) {  
                    // productCount--;
                    totalWeight = calculateTotalWeight();

                    products.remove(i);
                    productsQuantity.remove(i);
                    productsMsg.remove(i);
                    return true;
                } else if (quantity < productsQuantity.get(productId)) {        
                    totalWeight = calculateTotalWeight();

                    products.put(i, product);
                    productsQuantity.put(i, productsQuantity.get(i) - quantity);
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
        
    }

    /**
     * calculate the total amount of all product in this shopping cart
     * <p>
     * cart amount is calculated based on the price of all product in the cart + shipping fee for physical product
     * </p>
    */
    public double cartAmount() {
        totalPrice = 0;
        totalTax = 0;

        for(int i: products.keySet()) {
            totalPrice += products.get(i).getPrice() * productsQuantity.get(i);

            totalTax = totalTax + (products.get(i).getTax().getAmount() * products.get(i).getPrice() * productsQuantity.get(i)) / 100;

        }
        
        totalWeight = calculateTotalWeight();
        
        shippingFee = totalWeight * BASE_FEE;
        totalPrice = totalPrice + shippingFee + totalTax - totalCoupon;
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
        for(int i : products.keySet()) {
            if(products.get(i) instanceof PhysicalProduct) {
                totalWeight = totalWeight + ((PhysicalProduct)products.get(i)).getWeight() * productsQuantity.get(i);
            }

            if(products.get(i) instanceof PhysicalGiftProduct) {
                totalWeight = totalWeight + ((PhysicalGiftProduct)products.get(i)).getWeight() * productsQuantity.get(i);
            }
        }
        return totalWeight;
    }

    public boolean setMessage(String msg, int pId) {
        for (int i : products.keySet()) {
            if(i == pId) {
                if(products.get(i) instanceof UsedAsGifts) {
                    productsMsg.put(i, msg);
                    return true;
                }
            }
        }
        return false;
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

    public Coupon getCoupon() {
        return coupon;
    }

    public boolean setCoupon(Coupon coupon, int pId) {
        for (int i : products.keySet()) {
            if(i == pId) {
                for(String c: products.get(i).getCouponCodeList()) {
                    if(c.equals(coupon.getCouponCode())) {
                        this.coupon = coupon;
                        System.out.println(coupon.toString());
                        if(coupon instanceof CouponByPrice) {
                            totalCoupon = ((CouponByPrice)coupon).getPrice() * productsQuantity.get(i);
                        } else if (coupon instanceof CouponByPercent) {
                            totalCoupon = (((CouponByPercent)coupon).getPercent() * products.get(i).getPrice() * productsQuantity.get(i) / 100);
                        }
                        return true;
                    }
                }
            }
        }
        for (Product p : giftProducts.keySet()) {
            for(String c: p.getCouponCodeList()) {
                if(c == coupon.getCouponCode()) {
                    this.coupon = coupon;
                    return true;
                }
            }
        }
        return false;
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
        totalWeight = calculateTotalWeight();
        totalTax = 0;

        for(int i: products.keySet()) {
            totalTax = totalTax + (products.get(i).getTax().getAmount() * products.get(i).getPrice() * productsQuantity.get(i)) / 100;
        }
        return "Shopping Cart ID: " + id + "\t\tTotal weight: " + totalWeight + "\t\tNumber of product items: " + productCount +"\t\tTax: " + totalTax +"\t\tCoupon: " + coupon.getCouponCode();
    }

    public void displayCartDetails() {
        totalWeight = calculateTotalWeight();
        System.out.println("Shopping Cart ID: " + id + "\t\tTotal weight: " + totalWeight + "\t\tNumber of product items: " + productCount);
        for (int i : products.keySet()) {
            System.out.println("\t-ID: " + i + "\t-Name: " + products.get(i).getName() + "\tQuantity: " + productsQuantity.get(i) + "\tMessage: " + productsMsg.get(i));
        }
    }
}
