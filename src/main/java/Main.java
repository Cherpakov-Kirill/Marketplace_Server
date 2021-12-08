import nsu.oop.marketplace.server.ServerCore;
import nsu.oop.marketplace.server.database.simpleoperation.TasksOp;

public class Main {
    public static void main(String[] args) {

        ServerCore serverCore = new ServerCore(1025, 1000, 2000);
        TasksOp.getTaskById(3);
    }
}