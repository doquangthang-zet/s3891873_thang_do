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
public class DigitalProductTest {
    @Test
    public void stringRepresentationTest() {
        Product p1 = new DigitalProduct("laptop", "Acer Nitro 5", 3, 100, 2);

        p1.setName("laptop gaming");
        p1.setDescription("Asus Rox");
        p1.setQuantityAvailable(5);
        p1.setPrice(123.0);

        assertEquals("Digital - Name: laptop gaming\t\t\tDescription: Asus Rox\nAvailable Quantity: 5\t\tPrice: 123.0\t\tTax type: Normal Tax", p1.toString());
    }
}
