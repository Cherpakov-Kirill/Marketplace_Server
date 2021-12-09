package nsu.oop.marketplace.server.database.simpleoperation;

import nsu.oop.marketplace.server.database.entity.LoginInfoEntity;
import nsu.oop.marketplace.server.database.entity.UsersEntity;
import nsu.oop.marketplace.server.database.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserOp {

    public static UsersEntity addNewUser(String firstName, String lastName, String role) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        UsersEntity user = new UsersEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);

        session.save(user);
        session.getTransaction().commit();

        session.close();

        return user;
    }

    public static List<UsersEntity> getQuery() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<UsersEntity> users;

        NativeQuery query = session.createSQLQuery("SELECT * FROM users");
        query.addEntity(UsersEntity.class);
        users = query.list();

        session.close();

        return users;
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

    private static UsersEntity getLastUser() {
        List<UsersEntity> users = getQuery();
        return users.get(users.size() - 1);
    }

    private static boolean checkIdentityLogin(String login) {
        List<LoginInfoEntity> usersLogin = LogInInfoOp.getQuery();

        for (LoginInfoEntity userLogin : usersLogin) {
            if (userLogin.getLogin().equals(login)) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkRoleValid(String role) {
        boolean validFlag = false;
        switch (role) {
            case "Manager", "Director", "Admin" -> validFlag = true;
        }
        return validFlag;
    }

    public static boolean addNewUserAndLoginInfo(String firstName, String lastName, String role, String login, String password) {
        if (!checkIdentityLogin(login)) return false;
        if (!checkRoleValid(role)) return false;

        addNewUser(firstName, lastName, role);
        UsersEntity user = getLastUser();
        LogInInfoOp.addNewUserInfo(user.getId(), login, password);

        return true;
    }
}
