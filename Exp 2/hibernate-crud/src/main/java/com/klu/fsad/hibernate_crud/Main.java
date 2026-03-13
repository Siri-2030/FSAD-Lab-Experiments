package com.klu.fsad.hibernate_crud;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        // Insert Products
        insertProduct(new Product("Laptop", "Gaming Laptop", 75000, 10));
        insertProduct(new Product("Phone", "Android Phone", 25000, 50));

        // Retrieve Product
        Product p = getProductById(1);
        System.out.println("Retrieved: " + p.getName());

        // Update Product
        updateProductPrice(1, 80000);

        // Delete Product
        deleteProduct(2);
    }

    public static void insertProduct(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(product);
        tx.commit();
        session.close();
    }

    public static Product getProductById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }

    public static void updateProductPrice(int id, double newPrice) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Product product = session.get(Product.class, id);
        if (product != null) {
            product.setPrice(newPrice);
            session.merge(product);
        }
        tx.commit();
        session.close();
    }

    public static void deleteProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Product product = session.get(Product.class, id);
        if (product != null) {
            session.remove(product);
        }
        tx.commit();
        session.close();
    }
}
