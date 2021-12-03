import nsu.oop.marketplace.server.ServerCore;

public class Main {
    public static void main(String[] args) {
        ServerCore serverCore = new ServerCore(1025, 1000, 2000);

//        Session session = HibernateSessionFactory.getSessionFactory().openSession();
//
//        session.beginTransaction();
//
//        UsersEntity user = new UsersEntity();
//        user.setId(1);
//        user.setFirstName("Dmitry");
//        user.setLastName("Kogalenok");
//        user.setRole("Director");
//
//        session.save(user);
//        session.getTransaction().commit();
//
//        session.close();


    }
}