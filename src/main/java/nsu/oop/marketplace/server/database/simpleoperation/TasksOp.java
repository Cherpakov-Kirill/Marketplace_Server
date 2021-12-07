package nsu.oop.marketplace.server.database.simpleoperation;

import nsu.oop.marketplace.server.database.entity.TasksEntity;
import nsu.oop.marketplace.server.database.entity.UsersEntity;
import nsu.oop.marketplace.server.database.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class TasksOp {

    public static void addNewTask(int userId, String userName, String userLastName, String role, String task) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        UsersEntity user = new UsersEntity();
        user.setFirstName(userName);
        user.setLastName(userLastName);
        user.setRole(role);
        user.setId(userId);

        session.beginTransaction();

        TasksEntity newTask = new TasksEntity();
        newTask.setTaskText(task);
        newTask.setUsersByUserId(user);

        session.saveOrUpdate(newTask);

        session.flush();
        session.getTransaction().commit();

        session.close();
    }

    public static void getQuery(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        List<TasksEntity> tasks;

        NativeQuery query = session.createSQLQuery("SELECT * FROM tasks");
        query.addEntity(TasksEntity.class);
        tasks = query.list();

        System.out.println("____________Simple query from table tasks____________");
        for (TasksEntity task : tasks) {
            System.out.println(task.toString());
        }

        session.close();
    }

    public static void updateTask(String task){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        NativeQuery query = session.createSQLQuery("UPDATE tasks SET task_text = task WHERE id = '1'");
        query.executeUpdate();

        session.getTransaction().commit();

        session.close();
    }
}
