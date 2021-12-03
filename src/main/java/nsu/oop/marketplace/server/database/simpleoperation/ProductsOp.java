package nsu.oop.marketplace.server.database.simpleoperation;

import nsu.oop.marketplace.server.database.entity.ProductsEntity;
import nsu.oop.marketplace.server.database.entity.UsersEntity;
import nsu.oop.marketplace.server.database.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class ProductsOp {

    public static void addNewProduct(String name, double price, String description) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        ProductsEntity user = new ProductsEntity();
        user.setName(name);
        user.setPrice(price);
        user.setDescription(description);

        session.save(user);
        session.getTransaction().commit();

        session.close();
    }

    public static void getQuery() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<ProductsEntity> products;

        NativeQuery query = session.createSQLQuery("SELECT * FROM products");
        query.addEntity(ProductsEntity.class);
        products = query.list();

        System.out.println("Simple query from table products");
        for (ProductsEntity product : products) {
            System.out.println(product.toString());
        }

        session.close();
    }

    public static void updateUser(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        NativeQuery query = session.createSQLQuery("UPDATE products SET price = '5.75' WHERE last_name = 'Furminator'");
        query.executeUpdate();

        session.getTransaction().commit();

        session.close();
    }

}