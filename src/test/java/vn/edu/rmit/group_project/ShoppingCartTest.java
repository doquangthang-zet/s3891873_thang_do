package vn.edu.rmit.group_project;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.*;

/**
 * Unit test for Shopping Cart classes.
 */
/**
 * @author <Group 2>
 * s3891873 - Do Quang Thang
 * s3965673 - Phung Hoang Long
 * s3924489 - Du Tuan Vu
 * s3930338 - Nhat Mn
 */

public class ShoppingCartTest {
    // Testing toString method
    @Test
    public void stringRepresentationTest() {
        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 123.45, 1.5, 2);
        ShoppingCart spc = new ShoppingCart(0);
        spc.addItem(p1, 2, 1, 1);
        assertEquals("Shopping Cart ID: 0\t\tTotal weight: 3.0\t\tNumber of product items: 1\t\tTax: 24.69\t\tTotal price: 271.89000000000004", spc.toString());
    }

    // Test adding new item to shopping cart
    @Test
    public void addNewItemToShoppingCart() {
        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 30, 123.45, 1.5, 2);
        Product p2 = new DigitalProduct("smartphone", "Oppo reno 5", 12, 134.56, 3);

        ShoppingCart spc = new ShoppingCart(0);

        assertTrue(spc.addItem(p1, 3, 1, 1));

        assertEquals("laptop", spc.getProducts().get(1).getName());

        //quantity available decreased after adding
        assertEquals(27, p1.getQuantityAvailable());

        assertTrue(spc.addItem(p2,4,1,2));
        assertEquals(8, p2.getQuantityAvailable());
    }

    //Test adding more items to existing items in cart
    @Test
    public void addExistingItemToShoppingCart() {
        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 30, 123.45, 1.5, 2);

        ShoppingCart spc = new ShoppingCart(0);

        assertTrue(spc.addItem(p1, 3, 1, 1));

        assertEquals("laptop", spc.getProducts().get(1).getName());
        
        //quantity available decreased after adding
        assertEquals(27, p1.getQuantityAvailable());

        assertTrue(spc.addItem(p1,4,2, 1));
        assertEquals(23, p1.getQuantityAvailable());

        //Quantity of item in cart should be 7 after 2 times adding
        assertEquals(7, spc.getProductsQuantity().get(1));
    }

    //Test add items with unapropriate quantity to cart
    @Test
    public void cannotAddItemWithNoQuantityOrBiggerQuantity() {
        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 123.45, 1.5,2);
        Product p2 = new DigitalProduct("smartphone", "Oppo reno 5", 12, 134.56,3);
        Product p3 = new DigitalProduct("smartphone 2", "Oppo reno 7", 0, 189.0,1);

        ShoppingCart spc = new ShoppingCart(0);

        assertTrue(spc.addItem(p1, 3, 1, 1));

        // The second item only has 12 in quantity
        assertFalse(spc.addItem(p2,13,1,1));
        assertEquals(12, p2.getQuantityAvailable());

        //the third item has no quantity
        assertFalse(spc.addItem(p3,1,1,1));
        assertEquals(0, p3.getQuantityAvailable());
    }

    //Test remove items from cart
    @Test
    public void removeItemFromShoppingCart() {
        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 123.45, 1.5,1);
        Product p2 = new DigitalProduct("smartphone", "Oppo reno 5", 12, 134.56,2);

        ShoppingCart spc = new ShoppingCart(0);

        spc.addItem(p1, 2,1,1);

        spc.addItem(p2,3,1,2);

        // remove only 1 item p1
        assertEquals(1, p1.getQuantityAvailable());
        assertTrue(spc.removeItem(p1,1,1));
        assertEquals(2, p1.getQuantityAvailable());

        // remove the whole item p2
        assertEquals(9, p2.getQuantityAvailable());
        assertTrue(spc.removeItem(p2,3,2));
        assertEquals(12, p2.getQuantityAvailable());
    }

    //Test remove item with inappropriate quantity from cart
    @Test
    public void cannotRemoveNonExistingItemOrBiggerQuantityItem() {
        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 123.45, 1.5,1);
        Product p2 = new DigitalProduct("smartphone", "Oppo reno 5", 12, 134.56,2);

        ShoppingCart spc = new ShoppingCart(0);

        spc.addItem(p1,2,1,1);

        assertFalse(spc.removeItem(p2, 2,1));

        assertFalse(spc.removeItem(p1,3,1));
        assertEquals(12, p2.getQuantityAvailable());
        assertTrue(spc.removeItem(p1,2,1));
    }

    //Test display cart amount function
    @Test
    public void checkCartAmount() {
        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 100, 10,1);
        Product p2 = new PhysicalProduct("smartphone", "Oppo reno 5", 12, 200, 20,2);
        Product p3 = new DigitalProduct("smartphone 2", "Oppo reno 7", 5, 300,3);

        ShoppingCart spc = new ShoppingCart(0);

        Coupon c1 = new CouponByPrice(p1, "hello", 10);
        
        spc.addItem(p1,2,1,1);
        spc.addItem(p2,2,1,1);
        spc.addItem(p3,2,1,1);

        spc.setCoupon(c1);

        assertEquals(1346.0, spc.cartAmount());
    }

    //Test set message for an item function
    @Test
    public void setMessage() {
        Product p1 = new PhysicalGiftProduct("laptop", "Acer Nitro 5", 3, 100, 10,1);
        Product p2 = new PhysicalGiftProduct("smartphone", "Oppo reno 5", 12, 200, 20,2);
        Product p3 = new DigitalGiftProduct("smartphone 2", "Oppo reno 7", 5, 300,3);

        ShoppingCart spc = new ShoppingCart(0);

        Coupon c1 = new CouponByPrice(p1, "hello", 10);
        
        spc.addItem(p1,2,1,1);
        spc.addItem(p2,2,1,1);
        spc.addItem(p3,2,1,1);

        spc.setCoupon(c1);

        spc.setMessage("Happy", 1);
        spc.setMessage("Hello", 2);

        assertEquals("Happy", spc.getProductsMsg().get(1));
        assertEquals("Hello", spc.getProductsMsg().get(2));
    }

    //Test set coupon for a cart
    @Test
    public void setCoupon() {
        Product p1 = new PhysicalGiftProduct("laptop", "Acer Nitro 5", 3, 100, 10,1);

        ShoppingCart spc = new ShoppingCart(0);

        Coupon c1 = new CouponByPrice(p1, "hello", 10);
        Coupon c2 = new CouponByPercent(p1, "happy", 10);
        
        spc.addItem(p1,2,1,1);

        assertTrue(spc.setCoupon(c1));
        assertEquals("hello", spc.getCoupon().getCouponCode());

        assertTrue(spc.setCoupon(c2));

        assertEquals("happy", spc.getCoupon().getCouponCode());
    }

    // Test setcoupon to cart which has no compatible product
    @Test
    public void cannotSetCouponIfNoCompatibleProduct() {
        Product p1 = new PhysicalGiftProduct("laptop", "Acer Nitro 5", 3, 100, 10,1);
        Product p2 = new PhysicalGiftProduct("smartphone", "Oppo", 30, 100, 1,1);

        ShoppingCart spc = new ShoppingCart(0);

        Coupon c1 = new CouponByPrice(p1, "hello", 10);
        Coupon c2 = new CouponByPercent(p2, "happy", 10);
        
        spc.addItem(p1,2,1,1);

        assertTrue(spc.setCoupon(c1));
        assertEquals("hello", spc.getCoupon().getCouponCode());

        assertFalse(spc.setCoupon(c2));
    }

    //Test sorting cart function
    @Test
    public void cartSortedByTotalWeight() {
        List<ShoppingCart> spcList = new ArrayList<>();

        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 100, 10.1,1);
        Product p2 = new PhysicalProduct("smartphone", "Oppo reno 5", 12, 200,10.2,2);
        Product p3 = new PhysicalProduct("smartphone 2", "Oppo reno 7", 5, 300, 10.3,3);

        ShoppingCart spc = new ShoppingCart(0);
        ShoppingCart spc2 = new ShoppingCart(1);
        ShoppingCart spc3 = new ShoppingCart(2);

        spcList.add(spc);
        spcList.add(spc2);
        spcList.add(spc3);

        spc.addItem(p3,1,1,1);

        spc2.addItem(p1,1,1,1);;

        spc3.addItem(p2,1,1,1);

        Collections.sort(spcList, new SortByTotalWeight());

        // for(ShoppingCart s: spcList) {
        //     System.out.println(s.getTotalWeight());
        // }
        
        assertTrue(spcList.size() == 3);
        assertTrue(spcList.get(0).getTotalWeight() < spcList.get(1).getTotalWeight());
        assertTrue(spcList.get(1).getTotalWeight() < spcList.get(2).getTotalWeight());
    }
}
