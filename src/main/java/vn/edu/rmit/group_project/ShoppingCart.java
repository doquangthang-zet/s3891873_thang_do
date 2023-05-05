package vn.edu.rmit.group_project;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author <Do Quang Thang - S3891873>
 */

public class ShoppingCart {
    //ShoppingCart attributes
    private Map <Integer, Product> products;            //Collections contains all products in a cart
    private Map <Integer, Integer> productsQuantity;    //Collections contains all quantity corresponding to each product item in a cart
    private Map <Integer, String> productsMsg;          //Collections contains all messages corresponding to each product in a cart
    private static final double BASE_FEE = 0.1; //Constant base-fee to calculate shipping fee
    private double totalWeight;
    private double totalPrice;
    private double shippingFee;
    private double totalTax;
    private double totalCoupon;
    private int id;
    private int productCount;
    private Coupon coupon;
    private boolean isPurchased;


    /**
     * The constructor
     * @param id unique id of a shopping cart
    */
    public ShoppingCart(int id) {
        this.id = id;
        products = new LinkedHashMap<>();
        productsQuantity = new LinkedHashMap<>();
        productsMsg = new LinkedHashMap<>();
        totalWeight = 0;
        totalPrice = 0;
        shippingFee = 0;
        totalTax = 0;
        totalCoupon = 0;
        productCount = 0;
        isPurchased = false;
    }

    /**
     * add product object to the shopping cart
     * <p>
     * Given a product, add this product to current shopping cart.
     * If the assignment is successful, return true,
     * otherwise, return false
     * </p>
     * @param product the product object
     * @param quantity the adding quantity
     * @param addChoice the adding choice (1-add new item, 2-add more items to existing onces)
     * @param productId the product item id
     * @return true if the assignment is successful, otherwise, return false
    */
    public boolean addItem(Product product, int quantity, int addChoice, int productId) {
        /**
         * if the adding quantity is zero or greater than the available quantity of the product return false
         */
        if(product.getQuantityAvailable() == 0 || product.getQuantityAvailable() < quantity) {
            return false;
        }

        /**
         * if this cart has been purchased return false
         */
        if(isPurchased == true) {
            return false;
        }

        /**
         * if the adding choice == 2, look for the existing item and add more quantity
         */
        if(addChoice == 2) {
            for (int i : products.keySet()) {
                if(productId == i) {
                    if(products.get(i).getName().equals(product.getName())) {
                        product.setQuantityAvailable(product.getQuantityAvailable() - quantity);
                        products.put(i, product);
                        productsQuantity.put(i, productsQuantity.get(i) + quantity);
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * if the adding choice == 1 create new product items andd add to cart
         */
        product.setQuantityAvailable(product.getQuantityAvailable() - quantity);

        productCount++;

        products.put(productCount, product);
        productsQuantity.put(productCount, quantity);

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
     * @param quantity the product adding quantity
     * @param productId the product item id
     * @return true if the assignment is successful, otherwise, return false
    */
    public boolean removeItem(Product product, int quantity, int productId) {
        //the product must exist in the cart
        if(!products.containsKey(productId)) {
            return false;
        }

        /**
         * if the cart has been purchase, return false
         */
        if(isPurchased == true) {
            return false;
        }

        /**
         * if the product exists and user enter correct infor, remove the product
         */
        for(int i: products.keySet()) {
            if(products.get(i) == product && i == productId) {
                // Check the quantity and decide remove the whole product or not
                if(quantity == productsQuantity.get(productId)) { 
                    //If the whole produuct is removed, check the coupon and disable it 
                    if(product.equals(coupon.getProduct())) {
                        setCoupon(null);
                    }
                    product.setQuantityAvailable(product.getQuantityAvailable() + quantity);
                    totalWeight = calculateTotalWeight();

                    products.remove(i);
                    productsQuantity.remove(i);
                    productsMsg.remove(i);
                    return true;
                } else if (quantity < productsQuantity.get(productId)) { 
                    product.setQuantityAvailable(product.getQuantityAvailable() + quantity);       
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
     * cart amount is calculated based on the price of all product in the cart + shipping fee for physical product + the total eax amount of all products - the copon price for corresponding products
     * </p>
    */
    public double cartAmount() {
        totalPrice = 0;
        totalTax = 0;
        shippingFee = 0;
        totalCoupon = 0;

        //Check if the cart contains any product matching the coupon, if yes continue, else return false
        for (int i : products.keySet()) {
            if(coupon.getProduct().getName().equals(products.get(i).getName())) {
                //Calculate the coupon value for this cart
                if(coupon instanceof CouponByPrice) {
                    totalCoupon = ((CouponByPrice)coupon).getPrice() * productsQuantity.get(i);
                } else if (coupon instanceof CouponByPercent) {
                    totalCoupon = (((CouponByPercent)coupon).getPercent() * products.get(i).getPrice() * productsQuantity.get(i) / 100);
                }
            }
        }

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

    /**
     * Set message for an item in the cart
     * <p>
     * message is set on the gift product only
     * </p>
    */
    public boolean setMessage(String msg, int pId) {
        // If the cart has been purchased, return false
        if(isPurchased == true) {
            return false;
        }
        
        //Look for the item and set the message
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

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }

    /**
     * set the coupon for this cart or remove coupon for  this cart
    */
    public boolean setCoupon(Coupon coupon) {
        if(isPurchased == true) {
            return false;
        }
        
        totalCoupon = 0;
        
        // IF the pass in value is null => remove the coupon
        if(coupon == null) {
            this.coupon = null;
            return true;
        } else {
            //Check if the cart contains any product matching the coupon, if yes continue, else return false
            for (int i : products.keySet()) {
                if(coupon.getProduct().getName().equals(products.get(i).getName())) {
                    //Check if the price of coupon valid or not
                    if(coupon instanceof CouponByPrice) {
                        if(((CouponByPrice)coupon).getPrice() > products.get(i).getPrice()) {
                            return false;
                        }
                    }
                    this.coupon = coupon;
                    //Calculate the coupon value for this cart
                    if(coupon instanceof CouponByPrice) {
                        totalCoupon = ((CouponByPrice)coupon).getPrice() * productsQuantity.get(i);
                    } else if (coupon instanceof CouponByPercent) {
                        totalCoupon = (((CouponByPercent)coupon).getPercent() * products.get(i).getPrice() * productsQuantity.get(i) / 100);
                    }
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
        return "Shopping Cart ID: " + id + "\t\tTotal weight: " + totalWeight + "\t\tNumber of product items: " + productCount +"\t\tTax: " + totalTax +"\t\tTotal price: " + cartAmount();
    }

    /**
   * Details String representation of this shopping cart
   * <p>
   * This method is contains the infor of this cart and all product of it
   * </p>
   */
    public void displayCartDetails() {
        totalWeight = calculateTotalWeight();
        System.out.print("Shopping Cart ID: " + id + "\t\tTotal weight: " + totalWeight + "\t\tNumber of product items: " + productCount +"\t\tTotal price: " + cartAmount());
        if(coupon != null) {
            System.out.println("\t\tCoupon: " + coupon.getCouponCode());
        } else {
            System.out.println();
        }
        for (int i : products.keySet()) {
            System.out.print("\t-ID: " + i + "\t-Name: " + products.get(i).getName() + "\tQuantity: " + productsQuantity.get(i) + "\t-Price: " + products.get(i).getPrice());
            if (productsMsg.get(i) != null) {
                System.out.println("\tMessage: " + productsMsg.get(i));;
            } else {
                System.out.println();
            }
        }
    }

    /**
   * String representation of all product items
   * <p>
   * This method is used mainly to print a receipt to new file
   * </p>
   */
    public String displayCartItems() {
        String allItems = "";
        totalWeight = calculateTotalWeight();
        shippingFee = totalWeight * BASE_FEE;

        allItems += "Shopping Cart ID: " + id + "\t\tTotal weight: " + totalWeight + "\t\tNumber of product items: " + productCount + "\t\tShipping Fee: " + shippingFee + "\n";
        for (int i : products.keySet()) {
            allItems += "\t-ID: " + i + "\t-Name: " + products.get(i).getName() + "\tQuantity: " + productsQuantity.get(i) + "\t-Price: " + products.get(i).getPrice() + "\t-Tax: " + products.get(i).getTax().getName();
            if (productsMsg.get(i) != null) {
                allItems += "\tMessage: " + productsMsg.get(i) + "\n";
            } else {
                allItems += "\n\n";
            }
        }
        if(coupon != null) {
            allItems += coupon.toString() + "\n";
        } else {
            allItems += "\n";
        }
        allItems += "\nTotal Price: " + cartAmount() + "\n\n";
        return allItems;
    }
}
