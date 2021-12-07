package nsu.oop.marketplace.server.database.simpleoperation;

import nsu.oop.marketplace.server.database.entity.LoginInfoEntity;
import nsu.oop.marketplace.server.database.entity.UsersEntity;
import nsu.oop.marketplace.server.database.utils.HibernateSessionFactory;
import org.hibernate.Session;

public class LogInInfoOp {

    public static void addNewUser(int userId, String firstName, String lastName, String role, String login, String password) {
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

}
