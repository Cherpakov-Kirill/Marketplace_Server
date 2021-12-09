package nsu.oop.marketplace.server.database;

import nsu.oop.marketplace.inet.MarketplaceProto;

public interface DataBase {
    LogInData logIn(String name, String password);

    MarketplaceProto.Message.DBResponse getAllProductTable();

    MarketplaceProto.Message.DBResponse getAllTaskTable(int userId);

    MarketplaceProto.Message.DBResponse getAllChangesTable();

    MarketplaceProto.Message.DBResponse getAllSalesTable();

    MarketplaceProto.Message.DBResponse getAllLogTable();

    MarketplaceProto.Message.DBResponse completeTask(int id);

    MarketplaceProto.Message.DBResponse acceptChange(int id);

    MarketplaceProto.Message.DBResponse addNewUser(MarketplaceProto.Message.DBRequest.AddNewUser userInfo);

    MarketplaceProto.Message.DBResponse addNewProduct(MarketplaceProto.Message.DBRequest.AddNewProduct productInfo);

    MarketplaceProto.Message.DBResponse setNewTask(MarketplaceProto.Message.DBRequest.SetNewTask taskInfo);

    MarketplaceProto.Message.DBResponse getUserList();

    MarketplaceProto.Message.DBResponse getProductList();

    MarketplaceProto.Message.DBResponse changeProductInfo(MarketplaceProto.Message.DBRequest.ChangeProductInfo newProductInfo, int userId);
}
