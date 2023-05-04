// package vn.edu.rmit.group_project;

// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.*;

// /**
//  * Unit test for Digital Product classes.
//  */

//  /**
//  * @author <Do Quang Thang - S3891873>
//  */
// public class DigitalProductTest {
//     @Test
//     public void stringRepresentationTest() {
//         Product p1 = new DigitalProduct("laptop", "Acer Nitro 5", 3, 100);

//         p1.setName("laptop gaming");
//         p1.setDescription("Asus Rox");
//         p1.setQuantityAvailable(5);
//         p1.setPrice(123.0);

//         assertEquals("Digital - Name: laptop gaming\t\tDescription: Asus Rox\t\tAvailable Quantity: 5\t\tPrice: 123.0\t\tMessage: null", p1.toString());
//     }

//     @Test
//     public void canBeUsedAsGift() {
//         UsedAsGifts p1 = new DigitalProduct("laptop", "Acer Nitro 5", 3, 100);
//         Product p2 = new DigitalProduct("laptop", "Acer Nitro 7", 6, 200);

//         p1.setMessage("Happy birthday to you!");
//         ((UsedAsGifts)p2).setMessage("Happy new year!");

//         assertEquals("Happy birthday to you!", p1.getMessage());
//         assertEquals("Happy new year!", ((UsedAsGifts)p2).getMessage());
//     }
// }
