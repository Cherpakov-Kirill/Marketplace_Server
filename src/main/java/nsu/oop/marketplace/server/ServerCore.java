package nsu.oop.marketplace.server;

import nsu.oop.marketplace.inet.*;
import nsu.oop.marketplace.inet.users.InetForUsersController;
import nsu.oop.marketplace.inet.users.Users;
import nsu.oop.marketplace.inet.users.UsersController;
import nsu.oop.marketplace.inet.users.UsersControllerListener;
import nsu.oop.marketplace.server.database.DataBase;
import nsu.oop.marketplace.server.database.DataBaseCore;
import nsu.oop.marketplace.server.database.LogInData;
import nsu.oop.marketplace.server.chat.ChatCore;
import nsu.oop.marketplace.server.chat.ChatCoreListener;

public class ServerCore implements InetControllerListener, UsersControllerListener, ChatCoreListener {
    private final Inet inet;
    private final MarketplaceProto.SessionConfig config;
    private final Users users;
    private final DataBase dataBase;
    private final ChatCore chatCore;

    public ServerCore(int port, int pingDelayMs, int nodeTimeOutMs) {
        this.inet = new InetController(this, port, pingDelayMs, nodeTimeOutMs);
        this.config = MarketplaceProto.SessionConfig.newBuilder().setNodeTimeoutMs(nodeTimeOutMs).setPingDelayMs(pingDelayMs).setServerPort(port).build();
        this.users = new UsersController(this, (InetForUsersController) inet);
        inet.attachUsers((UsersControllerForInet) users);
        this.dataBase = new DataBaseCore();
        this.chatCore = new ChatCore(this);
        inet.startUnicast();
        inet.startMulticastPublisher(0, config);
    }

    public void interrupt() {
        inet.stopMulticastPublisher();
        inet.interruptUnicast();
    }

    @Override
    public int receiveJoinMsg(String username, String password, String ip, int port) {
        LogInData logInData = dataBase.logIn(username, password);
        int userId = logInData.userId();
        MarketplaceProto.UserType type = logInData.userType();
        users.addUser(userId, username, ip, port, type, logInData.firstName(), logInData.secondName());
        return userId;
    }

    @Override
    public void receiveErrorMsg(String error, int senderId) {
        System.err.println("Received error-message from node number " + senderId + " Message: " + error);
    }

    //Chat methods
    @Override
    public void receiveChatMsg(MarketplaceProto.Message.ChatMessage chatMessage, int id) {
        chatCore.receiveMessage(chatMessage, id);
    }

    @Override
    public void receiveDBRequestMsg(MarketplaceProto.Message.DBRequest dbRequest, int id) {
        MarketplaceProto.Message.DBResponse response = null;
        switch (dbRequest.getTypeCase()) {
            case PRODUCT_TABLE -> response = dataBase.getAllProductTable();
            case TASK_TABLE -> response = dataBase.getAllTaskTable(id);
            case CHANGE_TABLE -> response = dataBase.getAllChangesTable();
            case SALE_TABLE -> response = dataBase.getAllSalesTable();
            case LOG_TABLE -> response = dataBase.getAllLogTable();
            case COMPLETE_TASK -> response = dataBase.completeTask(dbRequest.getCompleteTask().getId());
            case ACCEPT_CHANGE -> response = dataBase.acceptChange(dbRequest.getAcceptChange().getId());
            case ADD_NEW_USER -> response = dataBase.addNewUser(dbRequest.getAddNewUser());
            case ADD_NEW_PRODUCT -> response = dataBase.addNewProduct(dbRequest.getAddNewProduct());
            case SET_NEW_TASK -> response = dataBase.setNewTask(dbRequest.getSetNewTask());
            case USER_LIST -> response = dataBase.getUserList();
            case PRODUCT_LIST -> response = dataBase.getProductList();
            case CHANGE_PRODUCT_INFO -> response = dataBase.changeProductInfo(dbRequest.getChangeProductInfo(), id);
        }
        if (response != null) users.sendDBResponseMessage(response, id);
    }

    @Override
    public void notifyCoreAboutDisconnect(int id) {
        chatCore.removeUser(id);
        chatCore.broadcastUserList();
    }

    @Override
    public void sendChatMessage(MarketplaceProto.Message.ChatMessage chatMessage, int id) {
        users.sendChatMessage(chatMessage, id);
    }

    //not used methods
    @Override
    public void receiveDBResponseMsg(MarketplaceProto.Message.DBResponse dbResponse, int i) {

    }

    @Override
    public void showErrorAuthMessage() {
    }

    @Override
    public void receiveUserInfoMsg(int i, MarketplaceProto.UserType userType, String s, String s1) {

    }

    @Override
    public void receiveAnnouncementMsg(MarketplaceProto.Message.AnnouncementMsg announcementMsg, String s, int i) {
    }
}

