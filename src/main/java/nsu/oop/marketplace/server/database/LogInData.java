package nsu.oop.marketplace.server.database;

import nsu.oop.marketplace.inet.MarketplaceProto;

public record LogInData(int userId, MarketplaceProto.UserType userType) {
}

