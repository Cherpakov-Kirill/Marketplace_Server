package nsu.oop.marketplace.server.database;

import nsu.oop.marketplace.inet.MarketplaceProto;
import nsu.oop.marketplace.server.database.entity.LoginInfoEntity;
import nsu.oop.marketplace.server.database.entity.UsersEntity;
import nsu.oop.marketplace.server.database.simpleoperation.LogInInfoOp;
import nsu.oop.marketplace.server.database.simpleoperation.UserOp;

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

        List<LoginInfoEntity> users = LogInInfoOp.getUserByName(name);
        if (users.size() == 0) {
            return new LogInData(getNonAuthUserId(), MarketplaceProto.UserType.UNAUTHENTICATED);
        }
        LoginInfoEntity user = users.get(0);

        if (user.getPassword().equals(password)) {
            MarketplaceProto.UserType userRole = null;
            List<UsersEntity> usersInfo = UserOp.getUserById(user.getId());
            UsersEntity userInfo = usersInfo.get(0);
            switch (userInfo.getRole()) {
                case "Director" -> userRole = MarketplaceProto.UserType.DIRECTOR;
                case "Admin" -> userRole = MarketplaceProto.UserType.ADMINISTRATOR;
                case "Manager" -> userRole = MarketplaceProto.UserType.MANAGER;
            }
            return new LogInData(user.getUsersByUserId().getId(), userRole);
        }

        return new LogInData(getNonAuthUserId(), MarketplaceProto.UserType.UNAUTHENTICATED);
    }
}