import nsu.oop.marketplace.server.ServerCore;
import nsu.oop.marketplace.server.database.simpleoperation.*;

public class Main {
    public static void main(String[] args) {
        ServerCore serverCore = new ServerCore(1025, 1000, 2000);

        UserOp.getQuery();
        TasksOp.getQuery();
        ProductsOp.getQuery();
        SalesOp.getQuery();

    }
}