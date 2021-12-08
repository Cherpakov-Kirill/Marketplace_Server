package nsu.oop.marketplace.server.database;

import nsu.oop.marketplace.inet.MarketplaceProto;

public interface DataBase {
    LogInData logIn(String name, String password);

    MarketplaceProto.Message.DBResponse getAllProductTable();

    MarketplaceProto.Message.DBResponse getAllTaskTable(int userId);

    MarketplaceProto.Message.DBResponse getAllChangesTable();

    MarketplaceProto.Message.DBResponse getAllSalesTable();

    MarketplaceProto.Message.DBResponse getAllLogTable();
}
