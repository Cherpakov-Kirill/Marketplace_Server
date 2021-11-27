package nsu.oop.marketplace.server;

import nsu.oop.marketplace.inet.Inet;
import nsu.oop.marketplace.inet.InetController;
import nsu.oop.marketplace.inet.InetControllerListener;
import nsu.oop.marketplace.inet.MarketplaceProto;

public class Core implements InetControllerListener {
    private final Inet inet;

    public Core(){
        this.inet = new InetController(this,1025,1000,2000);
    }




    @Override
    public int receiveJoinMsg(String name, String password, String ip, int port) {
        return 0;
    }

    @Override
    public void receiveErrorMsg(String error, int senderId) {

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