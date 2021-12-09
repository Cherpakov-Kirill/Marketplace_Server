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

    public static List<ChangesEntity> getQuery() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<ChangesEntity> changes;

        NativeQuery query = session.createSQLQuery("SELECT * FROM changes");
        query.addEntity(ChangesEntity.class);
        changes = query.list();

        session.close();

        return changes;
    }

    public static ChangesEntity getChangeById(int changeId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<ChangesEntity> changes;

        NativeQuery query = session.createSQLQuery("SELECT * FROM changes WHERE id = :changeId");
        query.setParameter("changeId", changeId);
        query.addEntity(ChangesEntity.class);
        changes = query.list();

        ChangesEntity change = new ChangesEntity();

        if (changes.size() == 0) {
            change.setId(-1);
            return change;
        }

        session.close();

        change = changes.get(0);

        return change;
    }

    public static void deleteChangeById(int changeId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        NativeQuery query = session.createSQLQuery("DELETE FROM changes WHERE id = :id");
        query.setParameter("id", changeId);
        query.executeUpdate();

        session.getTransaction().commit();

        session.close();
    }

    public static void acceptChange(int changeId) {
        ChangesEntity change = getChangeById(changeId);

        String changeType = change.getChangeType();
        int productId = change.getProductsByProductId().getId();

        switch (changeType) {
            case "price" -> {
                double newPrice = Double.parseDouble(change.getNewValue());
                ProductsOp.updateProductPriceById(newPrice, productId);
            }
            case "name" -> {
                String newName = change.getNewValue();
                ProductsOp.updateProductNameById(newName, productId);
            }
            case "description" -> {
                String newDesc = change.getNewValue();
                ProductsOp.updateProductDescriptionById(newDesc, productId);
            }
        }

        deleteChangeById(changeId);
    }
}
