package nsu.oop.marketplace.server.database.simpleoperation;

import nsu.oop.marketplace.server.database.entity.ProductsEntity;
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

    public static List<ProductsEntity> getQuery() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<ProductsEntity> products;

        NativeQuery query = session.createSQLQuery("SELECT * FROM products");
        query.addEntity(ProductsEntity.class);
        products = query.list();

        session.close();

        return products;
    }

    public static ProductsEntity getProductById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<ProductsEntity> products;

        NativeQuery query = session.createSQLQuery("SELECT * FROM products WHERE id = '" + id + "'");
        query.addEntity(ProductsEntity.class);
        products = query.list();

        session.close();

        if (products.size() == 0) {
            ProductsEntity product = new ProductsEntity();
            product.setId(-1);
            return product;
        }

        return products.get(0);
    }

    public static void updateProductByName(double price, String name) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        NativeQuery query = session.createSQLQuery("UPDATE products SET price = :price WHERE name = :name");
        query.setParameter("price", price);
        query.setParameter("name", name);
        query.executeUpdate();

        session.getTransaction().commit();

        session.close();
    }

    public static void updateProductPriceById(double price, int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        NativeQuery query = session.createSQLQuery("UPDATE products SET price = :price WHERE id = :id");
        query.setParameter("price", price);
        query.setParameter("id", id);
        query.executeUpdate();

        session.getTransaction().commit();

        session.close();
    }

    public static void updateProductNameById(String name, int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        NativeQuery query = session.createSQLQuery("UPDATE products SET name = :name WHERE id = :id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();

        session.getTransaction().commit();

        session.close();
    }

    public static void updateProductDescriptionById(String description, int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        NativeQuery query = session.createSQLQuery("UPDATE products SET description = :description WHERE id = :id");
        query.setParameter("description", description);
        query.setParameter("id", id);
        query.executeUpdate();

        session.getTransaction().commit();

        session.close();
    }

}
