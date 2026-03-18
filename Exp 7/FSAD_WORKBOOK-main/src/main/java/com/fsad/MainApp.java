package com.fsad;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainApp {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // INSERT 8 PRODUCTS
        session.save(new Product("Laptop","Electronics",50000,5));
        session.save(new Product("Phone","Electronics",20000,10));
        session.save(new Product("Mouse","Electronics",500,50));
        session.save(new Product("Keyboard","Electronics",1200,30));
        session.save(new Product("Notebook","Stationery",60,100));
        session.save(new Product("Pen","Stationery",20,200));
        session.save(new Product("Bag","Accessories",1500,15));
        session.save(new Product("Bottle","Accessories",300,25));

        tx.commit();

        // SORT BY PRICE ASCENDING
        System.out.println("\nProducts sorted by price ASC");
        List<Product> list1 = session.createQuery("FROM Product ORDER BY price ASC").list();

        for(Product p : list1)
            System.out.println(p.getName()+" "+p.getPrice());

        // SORT BY PRICE DESCENDING
        System.out.println("\nProducts sorted by price DESC");
        List<Product> list2 = session.createQuery("FROM Product ORDER BY price DESC").list();

        for(Product p : list2)
            System.out.println(p.getName()+" "+p.getPrice());

        // SORT BY QUANTITY
        System.out.println("\nProducts sorted by quantity");
        List<Product> list3 = session.createQuery("FROM Product ORDER BY quantity DESC").list();

        for(Product p : list3)
            System.out.println(p.getName()+" "+p.getQuantity());

        // PAGINATION FIRST 3
        System.out.println("\nFirst 3 products");
        Query q1 = session.createQuery("FROM Product");
        q1.setFirstResult(0);
        q1.setMaxResults(3);

        List<Product> page1 = q1.list();

        for(Product p : page1)
            System.out.println(p.getName());

        // PAGINATION NEXT 3
        System.out.println("\nNext 3 products");
        Query q2 = session.createQuery("FROM Product");
        q2.setFirstResult(3);
        q2.setMaxResults(3);

        List<Product> page2 = q2.list();

        for(Product p : page2)
            System.out.println(p.getName());

        // TOTAL PRODUCT COUNT
        Long total = (Long) session.createQuery("SELECT COUNT(*) FROM Product").uniqueResult();
        System.out.println("\nTotal products: "+total);

        // COUNT WHERE QUANTITY > 0
        Long available = (Long) session.createQuery("SELECT COUNT(*) FROM Product WHERE quantity>0").uniqueResult();
        System.out.println("Available products: "+available);

        // MIN MAX PRICE
        Object[] price = (Object[]) session.createQuery("SELECT MIN(price), MAX(price) FROM Product").uniqueResult();
        System.out.println("Min price: "+price[0]);
        System.out.println("Max price: "+price[1]);

        // GROUP BY DESCRIPTION
        System.out.println("\nGroup by description");
        List<Object[]> group = session.createQuery("SELECT description, COUNT(*) FROM Product GROUP BY description").list();

        for(Object[] row : group)
            System.out.println(row[0]+" "+row[1]);

        // WHERE PRICE RANGE
        System.out.println("\nProducts price between 100 and 5000");
        List<Product> range = session.createQuery("FROM Product WHERE price BETWEEN 100 AND 5000").list();

        for(Product p : range)
            System.out.println(p.getName()+" "+p.getPrice());

        // LIKE STARTS WITH
        System.out.println("\nNames starting with P");
        List<Product> start = session.createQuery("FROM Product WHERE name LIKE 'P%'").list();

        for(Product p : start)
            System.out.println(p.getName());

        // LIKE ENDS WITH
        System.out.println("\nNames ending with e");
        List<Product> end = session.createQuery("FROM Product WHERE name LIKE '%e'").list();

        for(Product p : end)
            System.out.println(p.getName());

        // LIKE CONTAINS
        System.out.println("\nNames containing 'oo'");
        List<Product> contain = session.createQuery("FROM Product WHERE name LIKE '%oo%'").list();

        for(Product p : contain)
            System.out.println(p.getName());

        // EXACT LENGTH
        System.out.println("\nNames with 5 characters");
        List<Product> length = session.createQuery("FROM Product WHERE length(name)=5").list();

        for(Product p : length)
            System.out.println(p.getName());

        session.close();

        System.out.println("\nHQL queries executed successfully");
    }
}