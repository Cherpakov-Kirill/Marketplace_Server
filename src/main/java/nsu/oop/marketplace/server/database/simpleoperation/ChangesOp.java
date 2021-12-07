package nsu.oop.marketplace.server.database.simpleoperation;

import nsu.oop.marketplace.server.database.entity.ChangesEntity;
import nsu.oop.marketplace.server.database.entity.ProductsEntity;
import nsu.oop.marketplace.server.database.entity.UsersEntity;
import nsu.oop.marketplace.server.database.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class ChangesOp {

    public static void addNewChanges(int userId, int productId, String changeType, String newValue) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        UsersEntity user = UserOp.getUserById(userId);
        ProductsEntity product = ProductsOp.getProductById(productId);

        ChangesEntity change = new ChangesEntity();
        change.setChangeType(changeType);
        change.setNewValue(newValue);
        change.setProductsByProductId(product);
        change.setUsersByUserId(user);

        session.save(change);
        session.getTransaction().commit();

        session.close();
    }

    public static void getQuery() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<ChangesEntity> changes;

        NativeQuery query = session.createSQLQuery("SELECT * FROM changes");
        query.addEntity(ChangesEntity.class);
        changes = query.list();

        System.out.println("____________Simple query from table changes____________");
        for (ChangesEntity change : changes) {
            System.out.println(change.toString());
        }

        session.close();
    }
}
