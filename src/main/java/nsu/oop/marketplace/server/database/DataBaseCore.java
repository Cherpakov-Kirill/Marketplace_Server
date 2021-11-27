package nsu.oop.marketplace.server.database;

import nsu.oop.marketplace.inet.MarketplaceProto;

import java.util.Objects;

public class DataBaseCore implements DataBase{
    @Override
    public LogInData logIn(String name, String password) {
        //I want request like: SELECT userId, userType FROM UsersTable WHERE username=name AND userPass=password

        switch (name){
            case "Nikita" -> {if(Objects.equals(password, "1111")) return new LogInData(1, MarketplaceProto.UserType.DIRECTOR);}
            case "Kolya" -> {if(Objects.equals(password, "2222")) return new LogInData(1, MarketplaceProto.UserType.MANAGER);}
            case "Petya" -> {if(Objects.equals(password, "3333")) return new LogInData(1, MarketplaceProto.UserType.ADMINISTRATOR);}
        }
        return new LogInData(0, MarketplaceProto.UserType.UNAUTHENTICATED);
    }
}
