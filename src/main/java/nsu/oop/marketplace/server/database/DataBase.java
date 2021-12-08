package nsu.oop.marketplace.server.database;

import nsu.oop.marketplace.inet.MarketplaceProto;

public interface DataBase {
    LogInData logIn(String name, String password);

    MarketplaceProto.Message.DBResponse getAllProductTable();
}
