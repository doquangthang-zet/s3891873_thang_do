package vn.edu.rmit.group_project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * Unit test for Physical Product classes.
 */
/**
 * @author <Group 2>
 * s3891873 - Do Quang Thang
 * s3965673 - Phung Hoang Long
 * s3924489 - Du Tuan Vu
 * s3930338 - Nhat Mn
 */

public class PhysicalProductTest {
    @Test
    public void stringRepresentationTest() {
        Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 123.45, 1.5, 3);

        p1.setName("laptop gaming");
        p1.setDescription("Asus Rox");
        p1.setQuantityAvailable(5);
        p1.setPrice(123.0);
        ((PhysicalProduct)p1).setWeight(1.6);

        assertEquals("Physical - Name: laptop gaming\t\t\tDescription: Asus Rox\nAvailable Quantity: 5\t\tPrice: 123.0\t\tWeight: 1.6\t\tTax type: Luxury Tax", p1.toString());
    }
}
