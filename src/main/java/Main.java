import nsu.oop.marketplace.server.ServerCore;
import nsu.oop.marketplace.server.database.simpleoperation.*;

public class Main {
    public static void main(String[] args) {

        UserOp.getQuery();
        TasksOp.getQuery();
        ProductsOp.getQuery();
        SalesOp.getQuery();
        LogInInfoOp.getQuery();
        LogHistoryOp.getQuery();
        ChangesOp.getQuery();

        ServerCore serverCore = new ServerCore(1025, 1000, 2000);

    }
}