package nsu.oop.marketplace.server;

import nsu.oop.marketplace.inet.*;
import nsu.oop.marketplace.inet.users.InetForUsersController;
import nsu.oop.marketplace.inet.users.Users;
import nsu.oop.marketplace.inet.users.UsersController;
import nsu.oop.marketplace.inet.users.UsersControllerListener;
import nsu.oop.marketplace.server.database.DataBase;
import nsu.oop.marketplace.server.database.DataBaseCore;
import nsu.oop.marketplace.server.database.LogInData;

public class ServerCore implements InetControllerListener, UsersControllerListener {
    private final Inet inet;
    private final MarketplaceProto.SessionConfig config;
    private final Users users;
    private final DataBase dataBase;

    public ServerCore(int port, int pingDelayMs, int nodeTimeOutMs){
        this.inet = new InetController(this,port,pingDelayMs,nodeTimeOutMs);
        this.config = MarketplaceProto.SessionConfig.newBuilder().setNodeTimeoutMs(2000).setPingDelayMs(1000).build();
        this.users = new UsersController(this, (InetForUsersController) inet);
        inet.attachUsers((UsersControllerForInet) users);
        this.dataBase = new DataBaseCore();
        inet.startMulticastPublisher(0, config);
    }

    public void interrupt(){
        inet.stopMulticastPublisher();
        inet.interruptUnicast();
    }

    @Override
    public int receiveJoinMsg(String name, String password, String ip, int port) {
        LogInData logInData = dataBase.logIn(name,password);
        if(logInData.userId() != 0){
            users.addUser(logInData.userId(), name, ip, port, logInData.userType());
            return logInData.userId();
        }
        return 0;
    }

    @Override
    public void receiveErrorMsg(String error, int senderId) {
        System.err.println("Receiver error-message from node number " + senderId + " Message: " + error);
    }

    //not used methods
    @Override
    public void launchClientCore(int i, MarketplaceProto.UserType userType) {

    }
    @Override
    public void receiveAnnouncementMsg(MarketplaceProto.Message.AnnouncementMsg announcementMsg, String s, int i) {
        //nothing, because this is server
    }


}
