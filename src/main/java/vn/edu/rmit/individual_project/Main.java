package vn.edu.rmit.individual_project;

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
    //User List interface
    private static List <ShoppingCart> shoppingCartList;
    private static int cardId;
    private static Scanner sc;

    public static void main(String[] args) {
        productList = new HashSet<>();
        shoppingCartList = new ArrayList<>();
        cardId = 0;

        System.out.println();
        System.out.println("Welcome to our shopping service!");
        System.out.println("--------------------------------------------------------------------------------------------------------------");

        showInitialOptions();
    }

    // Main user interface of the system
    public static void showInitialOptions() {
        System.out.println();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Please select one of the following services:\n1. Create new products\n2. Edit products\n3. View all products\n4. Search Product\n5. Create new shopping cart\n6. Add product\n7. Remove product\n8. Display cart amount\n9. Display all shopping cart\n0. Quit");
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
                createNewShoppingCart();
                break;
            case 6:
                addProduct();
                break;
            case 7:
                removeProduct();
                break;
            case 8:
                displayCartAmount();
                break;
            case 9:
                displayAllShoppingCart();
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
        sc = new Scanner(System.in);

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

        System.out.println("Please enter product message (optional): ");
        String message = sc.nextLine();

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
                Product newProduct = new DigitalProduct(name, description, quantity, price);

                if(!message.equals("")) {
                    ((DigitalProduct)newProduct).setMessage(message);
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

        System.out.println("Please enter product message (optional): ");
        String message = sc.nextLine();

        if(name.equals("") || description.equals("")) {
            System.out.println("Please enter all the fields!");
            createNewDigitalProduct();
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
                Product newProduct = new PhysicalProduct(name, description, quantity, price, weight);

                if(!message.equals("")) {
                    ((PhysicalProduct)newProduct).setMessage(message);
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

                    // System.out.println("Please enter new product description: ");
                    String description = sc.nextLine();
                    p.setDescription(description);

                    System.out.println("Please enter new product available quantity: ");
                    int quantity = sc.nextInt();
                    sc.nextLine();
                    p.setQuantityAvailable(quantity);

                    System.out.println("Please enter new product price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();
                    p.setPrice(price);

                    if(p instanceof PhysicalProduct) {
                        System.out.println("Please enter new product weight: ");
                        double weight = sc.nextDouble();
                        sc.nextLine();
                        ((PhysicalProduct)p).setWeight(weight);
                    }

                    System.out.println("Please enter new product message (optional): ");
                    String message = sc.nextLine();
                    ((UsedAsGifts)p).setMessage(message);

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
        System.out.println("Please enter shopping cart ID to add: ");
        int cartId = sc.nextInt();
        sc.nextLine();

        System.out.println("Please enter product name to be added into the cart: ");
        String productName = sc.nextLine();

        boolean doesExist = false;

        for (ShoppingCart spc: shoppingCartList) {
            if(spc.getId() == cartId) {
                for(Product p : productList) {
                    if(p.getName().equals(productName)) {
                        if(spc.addItem(p) && spc.addItem(productName)) {
                            doesExist = true;
                            System.out.println("The product: " + productName + " has been added successfully!");
                        }
                    }
                }
            }
        }

        if(!doesExist) {
            System.out.println("Cannot find shopping cart ID/product name or the quantity is not enough or the product is already in the cart! Try again!");
            addProduct();
        }
        showInitialOptions();
    }

    // Remove a specific product from a specific shopping cart
    public static void removeProduct() {
        System.out.println("Please enter shopping cart ID to remove: ");
        int cartId = sc.nextInt();
        sc.nextLine();

        System.out.println("Please enter product name to be removed from the cart: ");
        String productName = sc.nextLine();

        boolean doesExist = false;

        for (ShoppingCart spc: shoppingCartList) {
            if(spc.getId() == cartId) {
                for(Product p : productList) {
                    if(p.getName().equals(productName)) {
                        if(spc.removeItem(productName) && spc.removeItem(p)) {
                            doesExist = true;
                            System.out.println("The product: " + productName + " has been removed successfully!");
                        }
                    }
                }
            }
        }

        if(!doesExist) {
            System.out.println("Cannot find shopping cart ID/product name or the product is not existing in the cart! Try again!");
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
}
