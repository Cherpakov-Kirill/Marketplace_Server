package nsu.oop.marketplace.server.database.simpleoperation;

import nsu.oop.marketplace.server.database.entity.ProductsEntity;
import nsu.oop.marketplace.server.database.entity.SalesEntity;
import nsu.oop.marketplace.server.database.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.Date;
import java.util.List;

public class SalesOp {

    public static void addNewSale(int productId, int quantity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        ProductsEntity product = ProductsOp.getProductById(productId);

        session.beginTransaction();

        SalesEntity sale = new SalesEntity();
        sale.setProductsByProductId(product);
        sale.setAmount(product.getPrice() * quantity);
        sale.setQuantity(quantity);
        sale.setDate(new Date());

        session.save(sale);
        session.getTransaction().commit();

        session.close();
    }

    public static void getQuery() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<SalesEntity> sales;

        NativeQuery query = session.createSQLQuery("SELECT * FROM sales");
        query.addEntity(SalesEntity.class);
        sales = query.list();

        System.out.println("____________Simple query from table sales____________");
        for (SalesEntity sale : sales) {
            System.out.println(sale.toString());
        }

        session.close();
    }
}
