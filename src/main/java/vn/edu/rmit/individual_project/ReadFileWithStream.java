// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.util.stream.Stream;

// public class ReadFileWithStream {
//     public static void main(String[] args) {
//         String fileName = "data.txt";
//         try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
//             Cart cart = stream.map(line -> {
//                 String[] values = line.split(",");
//                 if (values[0].equals("product")) {
//                     return new Product(values[1], Double.parseDouble(values[2]));
//                 } else {
//                     Cart cart = new Cart();
//                     for (int i = 1; i < values.length; i++) {
//                         String[] productValues = values[i].split(":");
//                         cart.addProduct(new Product(productValues[0], Double.parseDouble(productValues[1])));
//                     }
//                     return cart;
//                 }
//             }).filter(Objects::nonNull).reduce(new Cart(), (cart, object) -> {
//                 if (object instanceof Product) {
//                     cart.addProduct((Product) object);
//                 } else {
//                     return (Cart) object;
//                 }
//                 return cart;
//             });
//             System.out.println("Cart: " + cart.getProducts());
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }

// // // product,name,price
// // // cart,product1:price1,product2:price2,product3:price3