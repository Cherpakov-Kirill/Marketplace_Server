package nsu.oop.marketplace.server.database.simpleoperation;

import nsu.oop.marketplace.server.database.entity.LoginInfoEntity;
import nsu.oop.marketplace.server.database.entity.UsersEntity;
import nsu.oop.marketplace.server.database.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserOp {

    public static void addNewUser(String firstName, String lastName, String role) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        UsersEntity user = new UsersEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);

        session.save(user);
        session.getTransaction().commit();

        session.close();
    }

    public static void getQuery() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<UsersEntity> users;

        NativeQuery query = session.createSQLQuery("SELECT * FROM users");
        query.addEntity(UsersEntity.class);
        users = query.list();

        System.out.println("____________Simple query from table users____________");
        for (UsersEntity user : users) {
            System.out.println(user.toString());
        }

        session.close();
    }

    public static UsersEntity getUserById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<UsersEntity> users;

        NativeQuery query = session.createSQLQuery("SELECT * FROM users WHERE id = :id");
        query.setParameter("id", id);
        query.addEntity(UsersEntity.class);
        users = query.list();

        session.close();

        if (users.size() == 0) {
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setId(-1);
            return usersEntity;
        }

        return users.get(0);
    }

    public static void updateUser(String firstName, String lastName) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        NativeQuery query = session.createSQLQuery("UPDATE users SET first_name = :firstName WHERE last_name = :lastName");
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        query.executeUpdate();

        session.getTransaction().commit();

        session.close();
    }
}
