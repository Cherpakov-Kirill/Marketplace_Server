package nsu.oop.marketplace.server.database.simpleoperation;

import nsu.oop.marketplace.server.database.entity.LogHistoryEntity;
import nsu.oop.marketplace.server.database.entity.LoginInfoEntity;
import nsu.oop.marketplace.server.database.entity.UsersEntity;
import nsu.oop.marketplace.server.database.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class LogHistoryOp {

    public static void addNewLog(int userId, String logDescp, String action) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        UsersEntity user = UserOp.getUserById(userId);

        session.beginTransaction();

        LogHistoryEntity log = new LogHistoryEntity();
        log.setLogDescription(logDescp);
        log.setActionType(action);
        log.setUsersByUserId(user);

        session.save(log);
        session.getTransaction().commit();

        session.close();
    }

    public static List<LogHistoryEntity> getQuery() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<LogHistoryEntity> logs;

        NativeQuery query = session.createSQLQuery("SELECT * FROM log_history");
        query.addEntity(LogHistoryEntity.class);
        logs = query.list();

        session.close();

        return logs;
    }
}
