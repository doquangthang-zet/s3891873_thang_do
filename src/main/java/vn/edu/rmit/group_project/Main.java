package vn.edu.rmit.group_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * @author <Do Quang Thang - S3891873>
 */

public class Main {
    //Use Set interface
    private static Set <Product> productList;
    //Use List interface
    private static List <ShoppingCart> shoppingCartList;
    private static List <Coupon> couponList;
    private static int cardId;
    private static TaxFree tFree;
    private static NormalTax tNormal;
    private static LuxuryTax tLuxury;
    private static Scanner sc;

    public static void main(String[] args) {
        productList = new HashSet<>();
        shoppingCartList = new ArrayList<>();
        couponList = new ArrayList<>();
        cardId = 0;
        tFree = new TaxFree();
        tNormal = new NormalTax();
        tLuxury = new LuxuryTax();

        readProduct();
        readCarts();

        System.out.println();
        System.out.println("Welcome to our shopping service!");
        System.out.println("--------------------------------------------------------------------------------------------------------------");

        
        showInitialOptions();
    }

    // Read product file
    public static void readProduct() {
        String productFilePath = "java_projects/src/FurtherPrograming/s3891873_thang_do/products.txt";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(productFilePath));
            String line = reader.readLine();
            int skip = 0;
            while (line != null) {
                if(skip != 0) {
                    String[] fields = line.split(", ");
                    String type = fields[0];
                    if(type.equals("Digital")) {
                        String name = fields[1];
                        String description = fields[2];
                        int quantity = Integer.parseInt(fields[3]);
                        double price = Double.parseDouble(fields[4]);
                        double weight = Double.parseDouble(fields[5]);
                        int taxType = Integer.parseInt(fields[6]);
                        String msg = fields[7];

                        Product p = new DigitalProduct(name, description, quantity, price, taxType);

                        for (int i = 8; i < fields.length; i++) {
                            String[] coupon = fields[i].split(":");
                            String couponType = coupon[0];

                            if(couponType.equals("price")) {
                                String code = coupon[1];
                                double couponAmount = Double.parseDouble(coupon[2]);

                                Coupon c = new CouponByPrice(p, code, couponAmount);
                                couponList.add(c);
                            } else if (couponType.equals("percent")) {
                                String code = coupon[1];
                                int couponAmount = Integer.parseInt(coupon[2]);

                                Coupon c = new CouponByPercent(p, code, couponAmount);
                                couponList.add(c);
                            }
                        }
                        productList.add(p);
                    } 
                    else if (type.equals("Physical")) {
                        String name = fields[1];
                        String description = fields[2];
                        int quantity = Integer.parseInt(fields[3]);
                        double price = Double.parseDouble(fields[4]);
                        double weight = Double.parseDouble(fields[5]);
                        int taxType = Integer.parseInt(fields[6]);
                        String msg = fields[7];

                        Product p = new PhysicalProduct(name, description, quantity, price, weight, taxType);

                        for (int i = 8; i < fields.length; i++) {
                            String[] coupon = fields[i].split(":");
                            String couponType = coupon[0];

                            if(couponType.equals("price")) {
                                String code = coupon[1];
                                double couponAmount = Double.parseDouble(coupon[2]);

                                Coupon c = new CouponByPrice(p, code, couponAmount);
                                couponList.add(c);
                            } else if (couponType.equals("percent")) {
                                String code = coupon[1];
                                int couponAmount = Integer.parseInt(coupon[2]);

                                Coupon c = new CouponByPercent(p, code, couponAmount);
                                couponList.add(c);
                            }
                        }
                        productList.add(p);
                    } else if (type.equals("DigitalGift")) {
                        String name = fields[1];
                        String description = fields[2];
                        int quantity = Integer.parseInt(fields[3]);
                        double price = Double.parseDouble(fields[4]);
                        double weight = Double.parseDouble(fields[5]);
                        int taxType = Integer.parseInt(fields[6]);
                        String msg = fields[7];

                        Product p = new DigitalGiftProduct(name, description, quantity, price, taxType);
                        ((UsedAsGifts)p).setMessage(msg);

                        for (int i = 8; i < fields.length; i++) {
                            String[] coupon = fields[i].split(":");
                            String couponType = coupon[0];

                            if(couponType.equals("price")) {
                                String code = coupon[1];
                                double couponAmount = Double.parseDouble(coupon[2]);

                                Coupon c = new CouponByPrice(p, code, couponAmount);
                                couponList.add(c);
                            } else if (couponType.equals("percent")) {
                                String code = coupon[1];
                                int couponAmount = Integer.parseInt(coupon[2]);

                                Coupon c = new CouponByPercent(p, code, couponAmount);
                                couponList.add(c);
                            }
                        }
                        productList.add(p);
                    } else {
                        String name = fields[1];
                        String description = fields[2];
                        int quantity = Integer.parseInt(fields[3]);
                        double price = Double.parseDouble(fields[4]);
                        double weight = Double.parseDouble(fields[5]);
                        int taxType = Integer.parseInt(fields[6]);
                        String msg = fields[7];

                        Product p = new PhysicalGiftProduct(name, description, quantity, price, weight, taxType);
                        ((UsedAsGifts)p).setMessage(msg);

                        for (int i = 8; i < fields.length; i++) {
                            String[] coupon = fields[i].split(":");
                            String couponType = coupon[0];

                            if(couponType.equals("price")) {
                                String code = coupon[1];
                                double couponAmount = Double.parseDouble(coupon[2]);

                                Coupon c = new CouponByPrice(p, code, couponAmount);
                                couponList.add(c);
                            } else if (couponType.equals("percent")) {
                                String code = coupon[1];
                                int couponAmount = Integer.parseInt(coupon[2]);

                                Coupon c = new CouponByPercent(p, code, couponAmount);
                                couponList.add(c);
                            }
                        }
                        productList.add(p);
                    }
                }
                skip++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Read cart file
    public static void readCarts() {
        String cartFilePath = "java_projects/src/FurtherPrograming/s3891873_thang_do/carts.txt";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(cartFilePath));
            String line = reader.readLine();
            int skip = 0;
            while (line != null) {
                if(skip != 0) {
                    String[] fields = line.split(", ");
                    int cartIdInFile = Integer.parseInt(fields[0]);
                    cardId++;
                    ShoppingCart spc = new ShoppingCart(cartIdInFile);
                    shoppingCartList.add(spc);

                    String couponCode = fields[1];

                    for (int i = 2; i < fields.length; i++) {
                        String[] product = fields[i].split(":");

                        String productName = product[0];
                        int productQuantity = Integer.parseInt(product[1]);
                        String productMessage = product[2];

                        for(Product p : productList) {
                            if(p.getName().equals(productName)) {
                                spc.addItem(p, productQuantity, 1, 0);
                            }
                        }
                        spc.setMessage(productMessage, i - 1);
                    }

                    for (Coupon c : couponList) {
                        if(c.getCouponCode().equals(couponCode)) {
                            spc.setCoupon(c);
                        }
                    }
                }
                skip++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // Main user interface of the system
    public static void showInitialOptions() {
        System.out.println();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Please select one of the following services:\n1. Create new products\n2. Edit products\n3. View all products\n4. Search Product\n5. Create new coupon\n6. View all coupons\n7. Create new shopping cart\n8. Add product\n9. Remove product\n10. Apply coupon to cart\n11. Display cart amount\n12. Display all shopping cart\n13. Display specific cart\n14. Set message\n15. Print purchase receipts\n0. Quit");
        sc = new Scanner(System.in);

        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                createNewProduct();
                break;
            case 2:
                editProduct();
                break;
            case 3:
                viewAllProducts();
                break;
            case 4:
                searchProduct();
                break;
            case 5:
                createNewCoupon();
                break;
            case 6:
                viewAllCoupons();
                break;
            case 7:
                createNewShoppingCart();
                break;
            case 8:
                addProduct();
                break;
            case 9:
                removeProduct();
                break;
            case 10:
                applyCoupon();
                break;
            case 11:
                displayCartAmount();
                break;
            case 12:
                displayAllShoppingCart();
                break;
            case 13:
                displayCart();
                break;
            case 14:
                setMessageForCartItem();
                break;
            case 15:
                printCartReceipt();
                break;
            case 0:
                sc.close();
                break;
            default:
                System.out.println("Invalid input! Try again!");
                showInitialOptions();
                break;
        }
    }

    /**
     * create a new product
     * <p>
     * Create new product. There is two allowed type: digital and physical
     * </p>
    */
    public static void createNewProduct() {
        System.out.println("Creating new product....\nPlease choose product type:\n1. Digital product\n2. Physical product\n3. Go back");

        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                createNewDigitalProduct();
                break;
            case 2:
                createNewPhysicalProduct();
                break;
            case 3:
                showInitialOptions();
                break;
            default:
                System.out.println("Invalid input! Try again!");
                createNewProduct();
                break;
        }
    }

    /**
     * create a new digital product
    */
    public static void createNewDigitalProduct() {
        String message = "";
        Product newProduct;

        System.out.println("Creating new digital product....\nPlease enter product name: ");
        String name = sc.nextLine();
        System.out.println("Please enter product description: ");
        String description = sc.nextLine();
        System.out.println("Please enter product available quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter product price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        System.out.println("Please enter type of tax (1.Tax-free 2.Normal-tax 3.Luxury-tax): ");
        int taxtype = sc.nextInt();
        sc.nextLine();

        System.out.println("Do you want to use this product as gift? (1.Yes 2.No): ");
        int giftChoice = sc.nextInt();
        sc.nextLine();
        
        if(giftChoice == 1) {
            System.out.println("Please enter product message (optional): ");
            message = sc.nextLine();
        } else if (giftChoice != 2) {
            System.out.println("Wrong input! Try again!");
            createNewDigitalProduct();
        }
        

        if(name.equals("") || description.equals("")) {
            System.out.println("Please enter all the fields!");
            createNewDigitalProduct();
        } else {
            boolean doesExist = false;

            for (Product p: productList) {
                if(p.getName().equals(name)) {
                    System.out.println("The product already existed! Try new name!");
                    doesExist = true;
                    createNewDigitalProduct();
                }
            }

            if(!doesExist) { 
                if(giftChoice == 1) {
                    System.out.println(tFree.getName());
                    newProduct = new DigitalGiftProduct(name, description, quantity, price, taxtype);
                    ((UsedAsGifts)newProduct).setMessage(message);
                } else {
                    newProduct = new DigitalProduct(name, description, quantity, price, taxtype);
                }
 
                productList.add(newProduct);
                System.out.println(newProduct.getName() + " added successfully!");
            }
        }
        showInitialOptions();
    }

    /**
     * create a new physical product
    */
    public static void createNewPhysicalProduct() {
        String message = "";
        TaxType t;
        Product newProduct;
        System.out.println("Creating new physical product....\nPlease enter product name: ");
        String name = sc.nextLine();
        System.out.println("Please enter product description: ");
        String description = sc.nextLine();
        System.out.println("Please enter product available quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter product price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        System.out.println("Please enter product weight: ");
        double weight = sc.nextDouble();
        sc.nextLine();

        System.out.println("Please enter type of tax (1.Tax-free 2.Normal-tax 3.Luxury-tax): ");
        int taxtype = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Do you want to use this product as gift? (1.Yes 2.No): ");
        int giftChoice = sc.nextInt();
        sc.nextLine();
        
        if(giftChoice == 1) {
            System.out.println("Please enter product message (optional): ");
            message = sc.nextLine();
        } else if (giftChoice != 2) {
            System.out.println("Wrong input! Try again!");
            createNewPhysicalProduct();
        }

        if(name.equals("") || description.equals("")) {
            System.out.println("Please enter all the fields!");
            createNewPhysicalProduct();
        } else {
            boolean doesExist = false;

            for (Product p: productList) {
                if(p.getName().equals(name)) {
                    System.out.println("The product already existed! Try new name!");
                    doesExist = true;
                    createNewPhysicalProduct();
                }
            }

            if(!doesExist) {
                if(giftChoice == 1) {
                    newProduct = new PhysicalGiftProduct(name, description, quantity, price, weight, taxtype);
                    ((UsedAsGifts)newProduct).setMessage(message);        
                } else {
                    newProduct = new PhysicalProduct(name, description, quantity, price, weight, taxtype);
                }
                productList.add(newProduct);
                System.out.println(newProduct.getName() + " added successfully!");
            }
        }
        showInitialOptions();
    }

    // edit existing product
    public static void editProduct() {
        System.out.println("Please enter product name to edit: ");
        String name = sc.nextLine();
        if (name.equals("")) {
            System.out.println("Please enter a name: ");
            editProduct();
        } else {
            boolean doesExist = false;
            for (Product p: productList) {
                if(p.getName().equals(name)) {
                    System.out.println("Start edit product information....\nPlease enter new product description(name cannot be changed): ");
                    // String newName = sc.nextLine();
                    // p.setName(newName);

                    String description = sc.nextLine();
                    p.setDescription(description);

                    // System.out.println("Please enter new product available quantity: ");
                    // int quantity = sc.nextInt();
                    // sc.nextLine();
                    // p.setQuantityAvailable(quantity);

                    System.out.println("Please enter new product price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();
                    p.setPrice(price);

                    if(p instanceof PhysicalProduct) {
                        System.out.println("Please enter new product weight: ");
                        double weight = sc.nextDouble();
                        sc.nextLine();
                        ((PhysicalProduct)p).setWeight(weight);
                    } else if (p instanceof PhysicalGiftProduct) {
                        System.out.println("Please enter new product weight: ");
                        double weight = sc.nextDouble();
                        sc.nextLine();
                        ((PhysicalGiftProduct)p).setWeight(weight);

                        System.out.println("Please enter new product message (optional): ");
                        String message = sc.nextLine();
                        ((UsedAsGifts)p).setMessage(message);
                    } else if (p instanceof DigitalGiftProduct) {
                        System.out.println("Please enter new product message (optional): ");
                        String message = sc.nextLine();
                        ((UsedAsGifts)p).setMessage(message);
                    }

                    System.out.println("Please enter new product tax type(1.Tax-free 2.Normal-tax 3.Luxury-tax): ");
                    int taxtype = sc.nextInt();
                    sc.nextLine();
                    TaxType t;
                    if(taxtype == 1) {
                        t = new TaxFree();
                        p.setTax(t);
                    } else if (taxtype == 2) {
                        t = new NormalTax();
                        p.setTax(t);
                    } else if (taxtype == 3) {
                        t = new TaxFree();
                        p.setTax(t);
                    } else {
                        System.out.println("Wrong tax type input!");
                        editProduct();
                    }

                    System.out.println("The product is edited successfully!");
                    doesExist = true;
                }
            }

            if(!doesExist) {
                System.out.println("There is no such product in your shop!");
                editProduct();
            }
        }

        showInitialOptions();
    }

    //View all product details existing in the service
    public static void viewAllProducts() {
        if(productList.size() > 0) {
            for (Product p: productList) {
                System.out.println();
                System.out.println(p.toString());
                System.out.println("###########################################");
            }
        } else {
            System.out.println("The shop doesn't have any product!");
        }

        showInitialOptions();
    }

    //Search for a specific product by its name
    public static void searchProduct() {
        System.out.println("Please enter a product name: ");
        String name = sc.nextLine();
        if (name.equals("")) {
            System.out.println("Please enter a name: ");
            searchProduct();
        } else {
            boolean doesExist = false;
            for (Product p: productList) {
                if(p.getName().equals(name)) {
                    System.out.println("***************************************");
                    System.out.println(p.toString());
                    System.out.println("***************************************");
                    doesExist = true;
                }
            }
            if(!doesExist) {
                System.out.println("There is no such product in your shop!");
                searchProduct();
            }
        }

        showInitialOptions();
    }

    //Create new coupon tied to a specific product
    public static void createNewCoupon() {
        System.out.println("Creating new coupon....\nPlease choose coupon type:\n1. Price coupon\n2. Percent coupon\n3. Go back");
        int choice = sc.nextInt();
        sc.nextLine();

        if(choice == 1) {
            System.out.println("Creating new price coupon....\nPlease enter product name to apply coupon: ");
            String name = sc.nextLine();
            System.out.println("Please enter coupon code: ");
            String code = sc.nextLine();
            System.out.println("Please enter deduct price: ");
            double price = sc.nextDouble();
            sc.nextLine();

            if(name.equals("") || code.equals("")) {
                System.out.println("Please enter all the fields!");
                createNewCoupon();
            } else {
                boolean couponExist = false;
                boolean productExist = false;

                for (Coupon c: couponList) {
                    if(c.getCouponCode().equals(code)) {
                        System.out.println("The coupon already existed! Try new code!");
                        couponExist = true;
                        createNewCoupon();
                    }
                }
                

                if(!couponExist) {
                    for (Product p: productList) {
                        if(p.getName().equals(name)) {
                            productExist = true;
                            
                            Coupon c = new CouponByPrice(p, code, price);
                            couponList.add(c);
                            p.getCouponCodeList().add(c.getCouponCode());
                            System.out.println(c.getCouponCode() + " added successfully!");
                        }
                    }                    
                    if(!productExist) {
                        System.out.println("The product is not existing! Try another product!");
                        createNewCoupon();
                    }
                }
            }
            showInitialOptions();
        } else if (choice == 2) {
            System.out.println("Creating new percent coupon....\nPlease enter product name to apply coupon: ");
            String name = sc.nextLine();
            System.out.println("Please enter coupon code: ");
            String code = sc.nextLine();
            System.out.println("Please enter deduct percent: ");
            int percent = sc.nextInt();
            sc.nextLine();

            if(name.equals("") || code.equals("")) {
                System.out.println("Please enter all the fields!");
                createNewCoupon();
            } else {
                boolean couponExist = false;
                boolean productExist = false;

                for (Coupon c: couponList) {
                    if(c.getCouponCode().equals(code)) {
                        System.out.println("The coupon already existed! Try new code!");
                        couponExist = true;
                        createNewCoupon();
                    }
                }
                

                if(!couponExist) {
                    for (Product p: productList) {
                        if(p.getName().equals(name)) {
                            productExist = true;
                            
                            Coupon c = new CouponByPercent(p, code, percent);
                            couponList.add(c);
                            p.getCouponCodeList().add(c.getCouponCode());
                            System.out.println(c.getCouponCode() + " added successfully!");
                        }
                    }                    
                    if(!productExist) {
                        System.out.println("The product is not existing! Try another product!");
                        createNewCoupon();
                    }
                }
            }
            showInitialOptions();
        } else if (choice == 3) {
            showInitialOptions();
        } else {
            System.out.println("Invalid input! Try again!");
            createNewCoupon();
        }
    }

    //View all existing coupon
    public static void viewAllCoupons() {
        if(couponList.size() > 0) {
            for (Coupon c: couponList) {
                System.out.println();
                System.out.println(c.toString());
                System.out.println("###########################################");
            }
        } else {
            System.out.println("The shop doesn't have any coupon!");
        }

        showInitialOptions();
    }

    // Create new shopping cart with auto-generated id
    public static void createNewShoppingCart() {
        cardId++;
        ShoppingCart spc = new ShoppingCart(cardId);
        shoppingCartList.add(spc);
        System.out.println("Shopping Cart ID: " + cardId + " has been created successfully!");
        showInitialOptions();
    }

    // Add a specific product to a specific shopping cart
    public static void addProduct() {
        String msg = "";
        int productId = 0;

        System.out.println("Add new items or add more items to existing onces: \n1. New items\n2. Existing items");
        int addingChoice = sc.nextInt();
        sc.nextLine();

        if (addingChoice != 1 && addingChoice != 2) {
            System.out.println("Wrong input! Try again!");
            addProduct();
        }

        System.out.println("Please enter shopping cart ID to add: ");
        int cartId = sc.nextInt();
        sc.nextLine();

        System.out.println("Please enter product name to be added into the cart: ");
        String productName = sc.nextLine();
        
        if(addingChoice == 2) {
            System.out.println("Please enter product ID(viewable by displayCart function): ");
            productId = sc.nextInt();
            sc.nextLine();
        }

        System.out.println("Please enter quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();
           
        boolean cartExist = false;
        boolean productExist = false;

        for (ShoppingCart spc: shoppingCartList) {
            if(spc.getId() == cartId) {
                cartExist = true;
                for(Product p : productList) {
                    if(p.getName().equals(productName)) {
                        productExist = true;
                        if(spc.addItem(p, quantity, addingChoice, productId)) {
                            System.out.println("The product: " + productName + " has been added successfully!");
                        } else {
                            System.out.println("The quantity may not enough! Try again!");
                            addProduct();
                        }
                    }
                }
            }
        }

        if(!cartExist) {
            System.out.println("Cannot find shopping cart ID! Try again!");
            addProduct();
        }
        if(!productExist) {
            System.out.println("Cannot find product name! Try again!");
            addProduct();
        }
        showInitialOptions();
    }

    // Remove a specific product from a specific shopping cart
    public static void removeProduct() {
        String msg = "";
        int productId = 0;
        System.out.println("Please enter shopping cart ID to remove: ");
        int cartId = sc.nextInt();
        sc.nextLine();

        System.out.println("Please enter product name to be removed from the cart: ");
        String productName = sc.nextLine();

        System.out.println("Please enter product ID(viewable by displayCart function): ");
        productId = sc.nextInt();
        sc.nextLine();

        System.out.println("Please enter quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        boolean cartExist = false;
        boolean productExist = false;

        for (ShoppingCart spc: shoppingCartList) {
            if(spc.getId() == cartId) {
                cartExist = true;
                for(Product p : productList) {
                    if(p.getName().equals(productName)) {
                        productExist = true;
                        if(spc.removeItem(p, quantity, productId)) {
                            System.out.println("The product: " + productName + " has been added successfully!");
                        } else {
                            System.out.println("The quantity may not enough! Try again!");
                            removeProduct();
                        }
                    }
                }
            }
        }

        if(!cartExist) {
            System.out.println("Cannot find shopping cart ID! Try again!");
            removeProduct();
        }
        if(!productExist) {
            System.out.println("Cannot find product name! Try again!");
            removeProduct();
        }

        showInitialOptions();
    }

    //Display total amount of a specific shopping cart
    public static void displayCartAmount() {
        System.out.println("Please enter the cart ID to check total price:");
        int cartId = sc.nextInt();
        sc.nextLine();

        boolean doesExist = false;

        for (ShoppingCart spc: shoppingCartList) {
            if(spc.getId() == cartId) {
                doesExist = true;
                System.out.println("The total price of this cart is: " + spc.cartAmount());
            }
        }

        if(!doesExist) {
            System.out.println("Cannot find shopping cart ID on the system! Try again!");
            displayCartAmount();
        }
        showInitialOptions();
    }

    // Apply a coupon to specific cart
    public static void applyCoupon() {
        System.out.println("Please enter the cart ID to apply coupon:");
        int cartId = sc.nextInt();
        sc.nextLine();

        // System.out.println("Please enter product ID(viewable by displayCart function): ");
        // int productId = sc.nextInt();
        // sc.nextLine();

        System.out.println("Please enter coupon code:");
        String code = sc.nextLine();

        boolean cartExist = false;
        boolean couponExist = false;

        for (ShoppingCart spc: shoppingCartList) {
            System.out.println(spc.getId());
            if(spc.getId() == cartId) {
                cartExist = true;
                
                for (Coupon c: couponList) {
                    if(c.getCouponCode().equals(code)) {
                        couponExist = true;
                        if(spc.setCoupon(c)) {
                            System.out.println("Coupon is successfully applied!");
                        } else {
                            System.out.println("This coupon is not compatible with any product in cart!");
                            applyCoupon();
                        }
                    }
                }
            }
        }

        if(!cartExist) {
            System.out.println("Cannot find shopping cart ID on the system! Try again!");
            applyCoupon();
        }
        if(!couponExist) {
            System.out.println("Cannot find coupon code on the system! Try again!");
            applyCoupon();
        }
        showInitialOptions();
    }

    // display all existing ghopping cart ordered by their total weight(ascending)
    public static void displayAllShoppingCart(){
        Collections.sort(shoppingCartList, new SortByTotalWeight());

        // If there is no shopping cart, inform user
        if(shoppingCartList.size() > 0) {
            for (ShoppingCart sc: shoppingCartList) {
                System.out.println(sc.toString());
            }
        } else {
            System.out.println("There is no shopping cart created!");
        }

        showInitialOptions();
    }

    // display a ghopping cart
    public static void displayCart(){
        System.out.println("Please enter the cart ID to display:");
        int cartId = sc.nextInt();
        sc.nextLine();

        boolean doesExist = false;

        for (ShoppingCart spc: shoppingCartList) {
            if(spc.getId() == cartId) {
                doesExist = true;
                spc.displayCartDetails();
            }
        }

        if(!doesExist) {
            System.out.println("Cannot find shopping cart ID on the system! Try again!");
            displayCartAmount();
        }
        showInitialOptions();
    }

    // Set message for a specific card item
    public static void setMessageForCartItem() {
        boolean cartExist = false;

        System.out.println("Please enter the cart ID to apply coupon:");
        int cartId = sc.nextInt();
        sc.nextLine();

        System.out.println("Please enter product ID(viewable by displayCart function): ");
        int productId = sc.nextInt();
        sc.nextLine();

        System.out.println("Please enter new message:");
        String msg = sc.nextLine();

        for (ShoppingCart spc: shoppingCartList) {
            if(spc.getId() == cartId) {
                cartExist = true;
                if(spc.setMessage(msg, productId)) {
                    System.out.println("Message is set successfully!");
                } else {
                    System.out.println("Product ID may not correct or this is not a gift product! Try again!");
                    setMessageForCartItem();
                }
            }
        }

        if(!cartExist) {
            System.out.println("Cannot find shopping cart ID on the system! Try again!");
            applyCoupon();
        }
        showInitialOptions();
    }

    // Print cart receipt function
    public static void printCartReceipt() {
        try {
            FileWriter writer = new FileWriter("receipt.txt");
            boolean cartExist = false;

            System.out.println("Please enter the cart ID to apply coupon:");
            int cartId = sc.nextInt();
            sc.nextLine();
    
            for (ShoppingCart spc: shoppingCartList) {
                if(spc.getId() == cartId) {
                    cartExist = true;
                    LocalDateTime myDateObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                    String formattedDate = myDateObj.format(myFormatObj);
                    
                    writer.write(spc.displayCartItems());
                    writer.append("Date of purchase: " + formattedDate);
                    System.out.println("The receipt is printed successfully!");
                }
            }
    
            if(!cartExist) {
                System.out.println("Cannot find shopping cart ID on the system! Try again!");
                printCartReceipt();
            }
            
            writer.close();
            showInitialOptions();
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
