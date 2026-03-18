package com.klu.app;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.klu.product.Product;
import com.klu.util.HibernateUtil;

public class mainApp {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Scanner sc = new Scanner(System.in);

        int ch;

        do {
            System.out.println("\n====== PRODUCT MANAGEMENT SYSTEM ======");
            System.out.println("1. Add Product");
            System.out.println("2. Display All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");

            System.out.println("----- HQL OPERATIONS -----");
            System.out.println("5. Sort Price ASC");
            System.out.println("6. Sort Price DESC");
            System.out.println("7. Sort by Quantity");
            System.out.println("8. First 3 Products");
            System.out.println("9. Next 3 Products");
            System.out.println("10. Count Products");
            System.out.println("11. Count Available Products");
            System.out.println("12. Min & Max Price");
            System.out.println("13. Filter by Price Range");
            System.out.println("14. Name Starts With");

            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Name: ");
                    String name = sc.next();

                    System.out.print("Description: ");
                    String desc = sc.next();

                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();

                    Product p = new Product(name, desc, price, qty);
                    ProductCRUDService.addProduct(session, p);
                    break;

                case 2:
                    ProductCRUDService.displayAll(session);
                    break;

                case 3:
                    System.out.print("Product ID: ");
                    int uid = sc.nextInt();

                    System.out.print("New Price: ");
                    double newPrice = sc.nextDouble();

                    System.out.print("New Quantity: ");
                    int newQty = sc.nextInt();

                    ProductCRUDService.updateProduct(session, uid, newPrice, newQty);
                    break;

                case 4:
                    System.out.print("Product ID: ");
                    int did = sc.nextInt();
                    ProductCRUDService.deleteProduct(session, did);
                    break;

                case 5:
                    ProductHQLService.sortPriceAsc(session);
                    break;

                case 6:
                    ProductHQLService.sortPriceDesc(session);
                    break;

                case 7:
                    ProductHQLService.sortByQuantity(session);
                    break;

                case 8:
                    ProductHQLService.firstThree(session);
                    break;

                case 9:
                    ProductHQLService.nextThree(session);
                    break;

                case 10:
                    ProductHQLService.countProducts(session);
                    break;

                case 11:
                    ProductHQLService.countAvailable(session);
                    break;

                case 12:
                    ProductHQLService.minMaxPrice(session);
                    break;

                case 13:
                    System.out.print("Min Price: ");
                    double min = sc.nextDouble();

                    System.out.print("Max Price: ");
                    double max = sc.nextDouble();

                    ProductHQLService.priceRange(session, min, max);
                    break;

                case 14:
                    System.out.print("Starts With: ");
                    String s = sc.next();
                    ProductHQLService.nameStartsWith(session, s);
                    break;

                case 0:
                    System.out.println("Exiting Application...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (ch != 0);

        session.close();
        factory.close();
        sc.close();
    }
}
