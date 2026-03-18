package com.klu.app;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.klu.product.Product;

public class ProductHQLService{

    // 3a) Sort by price ASC
    public static void sortPriceAsc(Session session) {
        Query<Product> q = session.createQuery(
                "FROM Product p ORDER BY p.price ASC", Product.class);
        q.list().forEach(p ->
                System.out.println(p.getName() + " " + p.getPrice()));
    }

    // 3b) Sort by price DESC
    public static void sortPriceDesc(Session session) {
        Query<Product> q = session.createQuery(
                "FROM Product p ORDER BY p.price DESC", Product.class);
        q.list().forEach(p ->
                System.out.println(p.getName() + " " + p.getPrice()));
    }

    // 4) Sort by quantity DESC
    public static void sortByQuantity(Session session) {
        Query<Product> q = session.createQuery(
                "FROM Product p ORDER BY p.quantity DESC", Product.class);
        q.list().forEach(p ->
                System.out.println(p.getName() + " " + p.getQuantity()));
    }

    // 5a) First 3 products
    public static void firstThree(Session session) {
        Query<Product> q = session.createQuery("FROM Product", Product.class);
        q.setFirstResult(0);
        q.setMaxResults(3);
        q.list().forEach(p -> System.out.println(p.getName()));
    }

    // 5b) Next 3 products
    public static void nextThree(Session session) {
        Query<Product> q = session.createQuery("FROM Product", Product.class);
        q.setFirstResult(3);
        q.setMaxResults(3);
        q.list().forEach(p -> System.out.println(p.getName()));
    }

    // 6a) Count total products
    public static void countProducts(Session session) {
        Long count = session.createQuery(
                "SELECT COUNT(p) FROM Product p", Long.class)
                .getSingleResult();
        System.out.println("Total Products: " + count);
    }

    // 6b) Count quantity > 0
    public static void countAvailable(Session session) {
        Long count = session.createQuery(
                "SELECT COUNT(p) FROM Product p WHERE p.quantity > 0", Long.class)
                .getSingleResult();
        System.out.println("Available Products: " + count);
    }

    // 6c) Count grouped by description
    public static void countByDescription(Session session) {
        List<Object[]> list = session.createQuery(
                "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description")
                .list();
        list.forEach(r ->
                System.out.println(r[0] + " : " + r[1]));
    }

    // 6d) Min & Max price
    public static void minMaxPrice(Session session) {
        Object[] r = (Object[]) session.createQuery(
                "SELECT MIN(p.price), MAX(p.price) FROM Product p")
                .getSingleResult();
        System.out.println("Min Price: " + r[0]);
        System.out.println("Max Price: " + r[1]);
    }

    // 8) Filter price range
    public static void priceRange(Session session, double min, double max) {
        Query<Product> q = session.createQuery(
                "FROM Product p WHERE p.price BETWEEN :min AND :max", Product.class);
        q.setParameter("min", min);
        q.setParameter("max", max);
        q.list().forEach(p ->
                System.out.println(p.getName() + " " + p.getPrice()));
    }

    // 9) LIKE examples
    public static void nameStartsWith(Session session, String s) {
        session.createQuery(
                "FROM Product p WHERE p.name LIKE :val", Product.class)
                .setParameter("val", s + "%")
                .list().forEach(p -> System.out.println(p.getName()));
    }
}
