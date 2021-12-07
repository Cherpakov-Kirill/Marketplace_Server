package nsu.oop.marketplace.server.database.simpleoperation;

import nsu.oop.marketplace.server.database.entity.LoginInfoEntity;
import nsu.oop.marketplace.server.database.entity.UsersEntity;
import nsu.oop.marketplace.server.database.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class LogInInfoOp {

    public static void addNewUserInfo(int userId, String firstName, String lastName, String role, String login, String password) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        UsersEntity user = new UsersEntity();
        user.setId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);

        LoginInfoEntity logInfo = new LoginInfoEntity();
        logInfo.setLogin(login);
        logInfo.setPassword(password);
        logInfo.setUsersByUserId(user);

        session.save(logInfo);
        session.getTransaction().commit();

        session.close();
    }

    public static List<LoginInfoEntity> getUserByName(String login) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<LoginInfoEntity> users;

        NativeQuery query = session.createSQLQuery("SELECT * FROM login_info WHERE login = '" + login + "'");
        query.addEntity(LoginInfoEntity.class);
        users = query.list();

        session.close();

        return users;
    }


}
