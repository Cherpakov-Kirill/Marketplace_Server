package nsu.oop.marketplace.server.chat;

import nsu.oop.marketplace.inet.MarketplaceProto;

public interface ChatCoreListener {
    void sendChatMessage(MarketplaceProto.Message.ChatMessage chatMessage, int id);
}
