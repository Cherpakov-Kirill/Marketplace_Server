package nsu.oop.marketplace.server.database;

import nsu.oop.marketplace.inet.MarketplaceProto;
import nsu.oop.marketplace.server.database.entity.LoginInfoEntity;
import nsu.oop.marketplace.server.database.simpleoperation.LogInInfoOp;

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
            return new LogInData(getNonAuthUserId(), MarketplaceProto.UserType.UNAUTHENTICATED);
        }

        if (user.getPassword().equals(password)) {
            MarketplaceProto.UserType userRole = null;
            switch (user.getUsersByUserId().getRole()) {
                case "Director" -> userRole = MarketplaceProto.UserType.DIRECTOR;
                case "Admin" -> userRole = MarketplaceProto.UserType.ADMINISTRATOR;
                case "Manager" -> userRole = MarketplaceProto.UserType.MANAGER;
            }
            return new LogInData(user.getUsersByUserId().getId(), userRole);
        }

        return new LogInData(getNonAuthUserId(), MarketplaceProto.UserType.UNAUTHENTICATED);
    }
}