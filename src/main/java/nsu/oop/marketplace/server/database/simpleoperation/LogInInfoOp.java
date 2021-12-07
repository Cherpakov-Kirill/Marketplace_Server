package nsu.oop.marketplace.server.database.simpleoperation;

import nsu.oop.marketplace.server.database.entity.LoginInfoEntity;
import nsu.oop.marketplace.server.database.entity.UsersEntity;
import nsu.oop.marketplace.server.database.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class LogInInfoOp {

    public static void addNewUserInfo(int userId, String login, String password) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        UsersEntity user = UserOp.getUserById(userId);

        LoginInfoEntity logInfo = new LoginInfoEntity();
        logInfo.setLogin(login);
        logInfo.setPassword(password);
        logInfo.setUsersByUserId(user);

        session.save(logInfo);
        session.getTransaction().commit();

        session.close();
    }

    public static LoginInfoEntity getUserByName(String login) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<LoginInfoEntity> users;

        NativeQuery query = session.createSQLQuery("SELECT * FROM login_info WHERE login = '" + login + "'");
        query.addEntity(LoginInfoEntity.class);
        users = query.list();

        session.close();

        if (users.size() == 0) {
            LoginInfoEntity logInfo = new LoginInfoEntity();
            logInfo.setId(-1);
            return logInfo;
        }
        return users.get(0);
    }

    public static void getQuery() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<LoginInfoEntity> users;

        NativeQuery query = session.createSQLQuery("SELECT * FROM login_info");
        query.addEntity(LoginInfoEntity.class);
        users = query.list();

        System.out.println("____________Simple query from table login_info____________");
        for (LoginInfoEntity login : users) {
            System.out.println(login.toString());
        }

        session.close();
    }

}
