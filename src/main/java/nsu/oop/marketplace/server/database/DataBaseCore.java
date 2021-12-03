package nsu.oop.marketplace.server.database;

import nsu.oop.marketplace.inet.MarketplaceProto;

import java.util.Objects;

public class DataBaseCore implements DataBase {
    private int nonAuthenticatedUserNumber;
    public DataBaseCore(){
        nonAuthenticatedUserNumber = 0;
    }

    private int getNonAuthUserId(){
        nonAuthenticatedUserNumber++;
        return (-1)*nonAuthenticatedUserNumber;
    }
    @Override
    public LogInData logIn(String name, String password) {
        //I want request like: SELECT userId, userType FROM UsersTable WHERE username=name AND userPass=password

        switch (name) {
            case "Kirill" -> {
                if (Objects.equals(password, "0000")) return new LogInData(1, MarketplaceProto.UserType.DIRECTOR);
            }
            case "Kolya" -> {
                if (Objects.equals(password, "2222")) return new LogInData(2, MarketplaceProto.UserType.MANAGER);
            }
            case "Petya" -> {
                if (Objects.equals(password, "3333")) return new LogInData(3, MarketplaceProto.UserType.ADMINISTRATOR);
            }
        }
        //if db got result that this user is unauthenticated this method must return data with getNonAuthUserId().
        //getNonAuthUserId is number of non-authenticated users in this session.
        return new LogInData(getNonAuthUserId(), MarketplaceProto.UserType.UNAUTHENTICATED);
    }
}