package vn.edu.rmit.group_project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * Unit test for Digital Product classes.
 */

/**
 * @author <Group 2>
 * s3891873 - Do Quang Thang
 * s3965673 - Phung Hoang Long
 * s3924489 - Du Tuan Vu
 * s3930338 - Nhat Mn
 */
public class PhysicalGiftTest {
    @Test
    public void stringRepresentationTest() {
        Product p1 = new PhysicalGiftProduct("laptop", "Acer Nitro 5", 3, 100, 1.2, 2);

        p1.setName("laptop gaming");
        p1.setDescription("Asus Rox");
        p1.setQuantityAvailable(5);
        ((PhysicalGiftProduct)p1).setWeight(1.5);
        p1.setPrice(123.0);

        assertEquals("Physical Gift - Name: laptop gaming\t\t\tDescription: Asus Rox\nAvailable Quantity: 5\t\tPrice: 123.0\t\tWeight: 1.5\t\tMessage: null\t\tTax type: Normal Tax", p1.toString());
    }
    
    @Test
    public void canBeUsedAsGift() {
        UsedAsGifts p1 = new PhysicalGiftProduct("laptop", "Acer Nitro 5", 3, 123.45, 1.3, 3);
        Product p2 = new PhysicalGiftProduct("laptop", "Acer Nitro 7", 6, 234.56, 1.3, 3);

        p1.setMessage("Happy birthday to you!");
        ((UsedAsGifts)p2).setMessage("Happy new year!");

        assertEquals("Happy birthday to you!", p1.getMessage());
        assertEquals("Happy new year!", ((UsedAsGifts)p2).getMessage());
    }
}
