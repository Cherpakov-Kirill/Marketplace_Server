import nsu.oop.marketplace.server.database.simpleoperation.ProductsOp;
import nsu.oop.marketplace.server.database.simpleoperation.SalesOp;
import nsu.oop.marketplace.server.database.simpleoperation.TasksOp;
import nsu.oop.marketplace.server.database.simpleoperation.UserOp;

public class Main {
    public static void main(String[] args) {
//        ServerCore serverCore = new ServerCore(1025, 1000, 2000);

        UserOp.getQuery();
        TasksOp.getQuery();
        ProductsOp.getQuery();
        SalesOp.getQuery();

    }
}