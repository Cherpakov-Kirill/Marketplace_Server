package nsu.oop.marketplace.server.database.simpleoperation;

import nsu.oop.marketplace.server.database.entity.UsersEntity;
import nsu.oop.marketplace.server.database.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserOp {

    public static void addNewUser(String firstName, String lastName, String role){
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

    public static void getQuery(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<UsersEntity> users;

        NativeQuery query = session.createSQLQuery("SELECT * FROM users");
        query.addEntity(UsersEntity.class);
        users = query.list();

        System.out.println("Simple query from table users");
        for (UsersEntity user : users) {
            System.out.println(user.toString());
        }

        session.close();
    }

    public static void updateUser(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        NativeQuery query = session.createSQLQuery("UPDATE users SET first_name = 'Dementor' WHERE last_name = 'Kogalenok'");
        query.executeUpdate();

        session.getTransaction().commit();

        session.close();
    }
}
