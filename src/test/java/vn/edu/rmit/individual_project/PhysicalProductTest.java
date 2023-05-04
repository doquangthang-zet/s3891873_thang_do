// package vn.edu.rmit.group_project;

// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.*;

// /**
//  * Unit test for Physical Product classes.
//  */
// /**
//  * @author <Do Quang Thang - S3891873>
//  */

// public class PhysicalProductTest {
//     @Test
//     public void stringRepresentationTest() {
//         Product p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 123.45, 1.5);

//         p1.setName("laptop gaming");
//         p1.setDescription("Asus Rox");
//         p1.setQuantityAvailable(5);
//         p1.setPrice(123.0);
//         ((PhysicalProduct)p1).setWeight(1.6);

//         assertEquals("Physical - Name: laptop gaming\t\tDescription: Asus Rox\t\tAvailable Quantity: 5\t\tPrice: 123.0\t\tWeight: 1.6\t\tMessage: null", p1.toString());
//     }

//     @Test
//     public void canBeUsedAsGift() {
//         UsedAsGifts p1 = new PhysicalProduct("laptop", "Acer Nitro 5", 3, 123.45, 1.5);
//         Product p2 = new PhysicalProduct("laptop", "Acer Nitro 7", 6, 234.56, 2.1);

//         p1.setMessage("Happy birthday to you!");
//         ((UsedAsGifts)p2).setMessage("Happy new year!");

//         assertEquals("Happy birthday to you!", p1.getMessage());
//         assertEquals("Happy new year!", ((UsedAsGifts)p2).getMessage());
//     }
// }
