import nsu.oop.marketplace.server.ServerCore;
import nsu.oop.marketplace.server.database.simpleoperation.LogInInfoOp;
import nsu.oop.marketplace.server.database.simpleoperation.ProductsOp;
import nsu.oop.marketplace.server.database.simpleoperation.TasksOp;
import nsu.oop.marketplace.server.database.simpleoperation.UserOp;

public class Main {
    public static void main(String[] args) {
        TasksOp.getTaskById(2);
        ServerCore serverCore = new ServerCore(1025, 1000, 2000);
    }
}