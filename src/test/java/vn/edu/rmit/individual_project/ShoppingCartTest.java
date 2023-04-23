package vn.edu.rmit.individual_project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.*;

/**
 * Unit test for Shopping Cart classes.
 */
/**
 * @author <Do Quang Thang - S3891873>
 */

public class ShoppingCartTest {
    @Test
    public void stringRepresentationTest() {
        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 123.45, 1.5);
        ShoppingCart spc = new ShoppingCart(0);
        spc.addItem(p1);
        spc.addItem(p1.getName());
        assertEquals("Shopping Cart ID: 0\t\tTotal weight: 1.5\t\tNumber of product: 1", spc.toString());
    }

    @Test
    public void addItemToShoppingCart() {
        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 123.45, 1.5);
        Product p2 = new DigitalProduct("smartphone", "Oppo reno 5", 12, 134.56);

        ShoppingCart spc = new ShoppingCart(0);

        assertTrue(spc.addItem(p1));
        assertTrue(spc.addItem(p1.getName()));
        assertEquals(2, p1.getQuantityAvailable());

        assertTrue(spc.addItem(p2));
        assertTrue(spc.addItem(p2.getName()));
        assertEquals(11, p2.getQuantityAvailable());
    }

    @Test
    public void cannotAddItemWithSameName() {
        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 123.45, 1.5);
        Product p2 = new DigitalProduct("smartphone", "Oppo reno 5", 12, 134.56);
        Product p3 = new DigitalProduct("smartphone", "Oppo reno 7", 4, 189.0);

        ShoppingCart spc = new ShoppingCart(0);

        assertTrue(spc.addItem(p1));
        assertTrue(spc.addItem(p1.getName()));

        assertTrue(spc.addItem(p2));
        assertTrue(spc.addItem(p2.getName()));

        assertFalse(spc.addItem(p3));
        assertFalse(spc.addItem(p3.getName()));
        
        assertEquals(4, p3.getQuantityAvailable());
    }

    @Test
    public void cannotAddItemWithNoQuantity() {
        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 123.45, 1.5);
        Product p2 = new DigitalProduct("smartphone", "Oppo reno 5", 12, 134.56);
        Product p3 = new DigitalProduct("smartphone 2", "Oppo reno 7", 0, 189.0);

        ShoppingCart spc = new ShoppingCart(0);

        assertTrue(spc.addItem(p1));
        assertTrue(spc.addItem(p1.getName()));

        assertTrue(spc.addItem(p2));
        assertTrue(spc.addItem(p2.getName()));

        assertFalse(spc.addItem(p3));
        assertFalse(spc.addItem(p3.getName()));
        assertEquals(0, p3.getQuantityAvailable());
    }

    @Test
    public void removeItemFromShoppingCart() {
        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 123.45, 1.5);
        Product p2 = new DigitalProduct("smartphone", "Oppo reno 5", 12, 134.56);

        ShoppingCart spc = new ShoppingCart(0);

        spc.addItem(p1);
        spc.addItem(p1.getName());

        spc.addItem(p2);
        spc.addItem(p2.getName());

        assertEquals(2, p1.getQuantityAvailable());
        assertTrue(spc.removeItem(p1.getName()));
        assertTrue(spc.removeItem(p1));
        assertEquals(3, p1.getQuantityAvailable());
    }

    @Test
    public void cannotRemoveNonExistingItem() {
        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 123.45, 1.5);
        Product p2 = new DigitalProduct("smartphone", "Oppo reno 5", 12, 134.56);

        ShoppingCart spc = new ShoppingCart(0);

        spc.addItem(p1);
        spc.addItem(p1.getName());

        assertTrue(spc.removeItem(p1.getName()));
        assertTrue(spc.removeItem(p1));

        assertFalse(spc.removeItem(p2.getName()));
        assertFalse(spc.removeItem(p2));
        assertEquals(12, p2.getQuantityAvailable());
    }

    @Test
    public void checkCartAmount() {
        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 100, 10);
        Product p2 = new PhysicalProduct("smartphone", "Oppo reno 5", 12, 200, 20);
        Product p3 = new DigitalProduct("smartphone 2", "Oppo reno 7", 5, 300);

        ShoppingCart spc = new ShoppingCart(0);

        spc.addItem(p1);
        spc.addItem(p1.getName());
        spc.addItem(p2);
        spc.addItem(p2.getName());
        spc.addItem(p3);
        spc.addItem(p3.getName());

        assertEquals(603.0, spc.cartAmount());
    }

    @Test
    public void cartSortedByTotalWeight() {
        List<ShoppingCart> spcList = new ArrayList<>();

        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 100, 10.1);
        Product p2 = new PhysicalProduct("smartphone", "Oppo reno 5", 12, 200,10.2);
        Product p3 = new PhysicalProduct("smartphone 2", "Oppo reno 7", 5, 300, 10.3);

        ShoppingCart spc = new ShoppingCart(0);
        ShoppingCart spc2 = new ShoppingCart(1);
        ShoppingCart spc3 = new ShoppingCart(2);

        spcList.add(spc);
        spcList.add(spc2);
        spcList.add(spc3);

        spc.addItem(p3);
        spc.addItem(p3.getName());

        spc2.addItem(p1);
        spc2.addItem(p1.getName());

        spc3.addItem(p2);
        spc3.addItem(p2.getName());

        Collections.sort(spcList, new SortByTotalWeight());

        // for(ShoppingCart s: spcList) {
        //     System.out.println(s.toString());
        // }
        
        assertTrue(spcList.size() == 3);
        assertTrue(spcList.get(0).getTotalWeight() < spcList.get(1).getTotalWeight());
        assertTrue(spcList.get(1).getTotalWeight() < spcList.get(2).getTotalWeight());
    }
}
