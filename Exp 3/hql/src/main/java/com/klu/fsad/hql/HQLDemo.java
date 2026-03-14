package com.klu.fsad.hql;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class HQLDemo {
    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        try {
            session.beginTransaction();

            // Insert additional products
            session.persist(new Product("Tablet", "Electronics", 15000, 12));
            session.persist(new Product("Headphones", "Accessories", 2000, 50));
            session.persist(new Product("Keyboard", "Accessories", 1200, 30));
            session.persist(new Product("Monitor", "Electronics", 10000, 8));
            session.persist(new Product("Mouse", "Accessories", 800, 40));
            session.persist(new Product("Charger", "Accessories", 500, 25));
            session.persist(new Product("Speaker", "Electronics", 3500, 18));

            session.getTransaction().commit();

            // Sorting by price ASC
            List<Product> ascPrice = session.createQuery("FROM Product ORDER BY price ASC", Product.class).list();
            System.out.println("\nProducts sorted by price ASC:");
            ascPrice.forEach(System.out::println);

            // Sorting by price DESC
            List<Product> descPrice = session.createQuery("FROM Product ORDER BY price DESC", Product.class).list();
            System.out.println("\nProducts sorted by price DESC:");
            descPrice.forEach(System.out::println);

            // Sort by quantity (highest first)
            List<Product> sortQuantity = session.createQuery("FROM Product ORDER BY quantity DESC", Product.class).list();
            System.out.println("\nProducts sorted by quantity DESC:");
            sortQuantity.forEach(System.out::println);

            // Pagination: First 3 products
            List<Product> first3 = session.createQuery("FROM Product", Product.class)
                    .setFirstResult(0).setMaxResults(3).list();
            System.out.println("\nFirst 3 products:");
            first3.forEach(System.out::println);

            // Pagination: Next 3 products
            List<Product> next3 = session.createQuery("FROM Product", Product.class)
                    .setFirstResult(3).setMaxResults(3).list();
            System.out.println("\nNext 3 products:");
            next3.forEach(System.out::println);

            // Aggregate queries
            Long total = session.createQuery("SELECT COUNT(p) FROM Product p", Long.class).uniqueResult();
            System.out.println("\nTotal products: " + total);

            Long available = session.createQuery("SELECT COUNT(p) FROM Product p WHERE p.quantity > 0", Long.class).uniqueResult();
            System.out.println("Products with quantity > 0: " + available);

            List<Object[]> groupedCount = session.createQuery("SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description").list();
            System.out.println("\nCount grouped by description:");
            for(Object[] row : groupedCount){
                System.out.println(row[0] + " -> " + row[1]);
            }

            Double minPrice = session.createQuery("SELECT MIN(p.price) FROM Product p", Double.class).uniqueResult();
            Double maxPrice = session.createQuery("SELECT MAX(p.price) FROM Product p", Double.class).uniqueResult();
            System.out.println("\nMin Price: " + minPrice + ", Max Price: " + maxPrice);

            // GROUP BY description with SUM of quantity
            List<Object[]> groupByDesc = session.createQuery("SELECT p.description, SUM(p.quantity) FROM Product p GROUP BY p.description").list();
            System.out.println("\nGrouped by description (sum of quantity):");
            for(Object[] row : groupByDesc){
                System.out.println(row[0] + " -> " + row[1]);
            }

            // WHERE with price range
            List<Product> priceRange = session.createQuery("FROM Product p WHERE p.price BETWEEN 1000 AND 20000", Product.class).list();
            System.out.println("\nProducts in price range 1000–20000:");
            priceRange.forEach(System.out::println);

            // LIKE queries
            List<Product> startM = session.createQuery("FROM Product p WHERE p.name LIKE 'M%'", Product.class).list();
            System.out.println("\nNames starting with M:");
            startM.forEach(System.out::println);

            List<Product> endE = session.createQuery("FROM Product p WHERE p.name LIKE '%e'", Product.class).list();
            System.out.println("\nNames ending with e:");
            endE.forEach(System.out::println);

            List<Product> containTop = session.createQuery("FROM Product p WHERE p.name LIKE '%top%'", Product.class).list();
            System.out.println("\nNames containing 'top':");
            containTop.forEach(System.out::println);

            List<Product> length5 = session.createQuery("FROM Product p WHERE LENGTH(p.name) = 5", Product.class).list();
            System.out.println("\nNames with length 5:");
            length5.forEach(System.out::println);

        } finally {
            session.close();
            factory.close();
        }
    }
}
