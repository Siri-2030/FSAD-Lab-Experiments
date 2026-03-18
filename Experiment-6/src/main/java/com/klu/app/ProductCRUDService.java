package com.klu.app;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.klu.product.Product;

public class ProductCRUDService {

    // INSERT
    public static void addProduct(Session session, Product p) {
        Transaction tx = session.beginTransaction();
        session.save(p);
        tx.commit();
        System.out.println("Product Added Successfully");
    }

    // DISPLAY ALL
    public static void displayAll(Session session) {
        Query<Product> q = session.createQuery("FROM Product", Product.class);
        List<Product> list = q.list();
        list.forEach(p ->
            System.out.println(p.getId()+" | "+p.getName()+" | "+
                               p.getPrice()+" | "+p.getQuantity()));
    }

    // UPDATE
    public static void updateProduct(Session session, int id, double price, int qty) {
        Transaction tx = session.beginTransaction();
        Product p = session.get(Product.class, id);
        if (p != null) {
            p.setPrice(price);
            p.setQuantity(qty);
            session.update(p);
            tx.commit();
            System.out.println("Product Updated");
        } else {
            System.out.println("Product Not Found");
        }
    }

    // DELETE
    public static void deleteProduct(Session session, int id) {
        Transaction tx = session.beginTransaction();
        Product p = session.get(Product.class, id);
        if (p != null) {
            session.delete(p);
            tx.commit();
            System.out.println("Product Deleted");
        } else {
            System.out.println("Product Not Found");
        }
    }
}
