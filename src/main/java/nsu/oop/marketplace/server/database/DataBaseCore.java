package nsu.oop.marketplace.server.database;

import nsu.oop.marketplace.inet.MarketplaceProto;
import nsu.oop.marketplace.inet.messages.MessageBuilder;
import nsu.oop.marketplace.server.database.entity.*;
import nsu.oop.marketplace.server.database.simpleoperation.*;

import java.util.ArrayList;
import java.util.List;

public class DataBaseCore implements DataBase {
    private int nonAuthenticatedUserNumber;

    public DataBaseCore() {
        nonAuthenticatedUserNumber = 0;
    }

    private int getNonAuthUserId() {
        nonAuthenticatedUserNumber++;
        return (-1) * nonAuthenticatedUserNumber;
    }

    @Override
    public LogInData logIn(String name, String password) {

        LoginInfoEntity user = LogInInfoOp.getUserByName(name);
        if (user.getId() == -1) {
            return new LogInData(getNonAuthUserId(), MarketplaceProto.UserType.UNAUTHENTICATED, "Unknown", "Unknown");
        }

        if (user.getPassword().equals(password)) {
            MarketplaceProto.UserType userRole = null;
            switch (user.getUsersByUserId().getRole()) {
                case "Director" -> userRole = MarketplaceProto.UserType.DIRECTOR;
                case "Admin" -> userRole = MarketplaceProto.UserType.ADMINISTRATOR;
                case "Manager" -> userRole = MarketplaceProto.UserType.MANAGER;
            }
            return new LogInData(user.getUsersByUserId().getId(), userRole, user.getUsersByUserId().getFirstName(), user.getUsersByUserId().getLastName());
        }

        return new LogInData(getNonAuthUserId(), MarketplaceProto.UserType.UNAUTHENTICATED, "Unknown", "Unknown");
    }

    @Override
    public MarketplaceProto.Message.DBResponse getAllProductTable() {
        List<MarketplaceProto.DBFullProduct> productList = new ArrayList<>();
        List<ProductsEntity> products = ProductsOp.getQuery();
        for (ProductsEntity product : products) {
            MarketplaceProto.DBFullProduct addProduct = MessageBuilder.dbFullProductBuilder(product.getId(), product.getName(), Double.toString(product.getPrice()), product.getDescription());
            productList.add(addProduct);
        }
        return MessageBuilder.productTableBuilder(productList);
    }

    @Override
    public MarketplaceProto.Message.DBResponse getAllTaskTable(int userId) {
        List<MarketplaceProto.DBFullTask> taskList = new ArrayList<>();
        List<TasksEntity> tasks = TasksOp.getTaskById(userId);
        for (TasksEntity task : tasks) {
            String fullUserName = task.getUsersByUserId().getFirstName() + " " + task.getUsersByUserId().getLastName();
            MarketplaceProto.DBFullTask addTask = MessageBuilder.dbFullTaskBuilder(task.getId(), fullUserName, task.getTaskText(), task.getDone());
            taskList.add(addTask);
        }
        return MessageBuilder.taskTableBuilder(taskList);
    }

    @Override
    public MarketplaceProto.Message.DBResponse getAllChangesTable() {
        List<MarketplaceProto.DBFullChanges> changesList = new ArrayList<>();
        List<ChangesEntity> changes = ChangesOp.getQuery();
        for (ChangesEntity change : changes) {
            String fullUserName = change.getUsersByUserId().getFirstName() + " " + change.getUsersByUserId().getLastName();
            String productName = change.getProductsByProductId().getName();
            MarketplaceProto.DBFullChanges addChange = MessageBuilder.dbFullChangeBuilder(change.getId(), productName, change.getChangeType(), change.getNewValue(), fullUserName);
            changesList.add(addChange);
        }
        return MessageBuilder.changeTableBuilder(changesList);
    }

    @Override
    public MarketplaceProto.Message.DBResponse getAllSalesTable(){
        List<MarketplaceProto.DBFullSales> salesList = new ArrayList<>();
        List<SalesEntity> sales = SalesOp.getQuery();
        for(SalesEntity sale : sales){
            String productName = sale.getProductsByProductId().getName();
            String date = sale.getDate().toString();
            MarketplaceProto.DBFullSales addSale = MessageBuilder.dbFullSaleBuilder(sale.getId(), productName, date, Integer.toString(sale.getQuantity()), Double.toString(sale.getAmount()));
            salesList.add(addSale);
        }
        return MessageBuilder.salesTableBuilder(salesList);
    }

    @Override
    public MarketplaceProto.Message.DBResponse getAllLogTable(){
        List<MarketplaceProto.DBFullLog> logsList = new ArrayList<>();
        List<LogHistoryEntity> logs = LogHistoryOp.getQuery();
        for(LogHistoryEntity log : logs){
            String fullUserName = log.getUsersByUserId().getFirstName() + " " + log.getUsersByUserId().getLastName();
            MarketplaceProto.DBFullLog addLog = MessageBuilder.dbFullLogBuilder(fullUserName, log.getLogDescription(), log.getActionType());
            logsList.add(addLog);
        }
        return MessageBuilder.logTableBuilder(logsList);
    }

}