package nsu.oop.marketplace.server.database.simpleoperation;

import nsu.oop.marketplace.server.database.entity.TasksEntity;
import nsu.oop.marketplace.server.database.entity.UsersEntity;
import nsu.oop.marketplace.server.database.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class TasksOp {

    public static void addNewTask(int userId, String task, boolean done) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        UsersEntity user = UserOp.getUserById(userId);

        session.beginTransaction();

        TasksEntity newTask = new TasksEntity();
        newTask.setTaskText(task);
        newTask.setUsersByUserId(user);
        newTask.setDone(done);

        session.saveOrUpdate(newTask);

        session.flush();
        session.getTransaction().commit();

        session.close();
    }

    public static List<TasksEntity> getQuery() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<TasksEntity> tasks;

        NativeQuery query = session.createSQLQuery("SELECT * FROM tasks");
        query.addEntity(TasksEntity.class);
        tasks = query.list();

        session.close();

        return tasks;
    }

    public static List<TasksEntity> getTaskById(int userId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<TasksEntity> tasks;
        UsersEntity user = UserOp.getUserById(userId);

        NativeQuery query = null;

        if (user.getRole().equals("Director")) {
            query = session.createSQLQuery("SELECT * FROM tasks");
        } else {
            query = session.createSQLQuery("SELECT * FROM tasks WHERE user_id = :userId AND done = false");
            query.setParameter("userId", userId);
        }
        query.addEntity(TasksEntity.class);
        tasks = query.list();

        session.close();

        return tasks;
    }

    public static void setTaskDone(int taskId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        NativeQuery query = session.createSQLQuery("UPDATE tasks SET done = true WHERE id = :id");
        query.setParameter("id", taskId);
        query.executeUpdate();

        session.getTransaction().commit();

        session.close();
    }
}
