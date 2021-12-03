package nsu.oop.marketplace.server.chat;

import nsu.oop.marketplace.inet.MarketplaceProto;
import nsu.oop.marketplace.inet.messages.MessageBuilder;

import java.util.HashMap;
import java.util.Map;

public class ChatCore {
    private final ChatCoreListener listener;
    private final Map<String, Integer> users;

    public ChatCore(ChatCoreListener listener) {
        this.listener = listener;
        this.users = new HashMap<>();
    }

    public void removeUser(int userId){
        for(String name : users.keySet()){
            if(users.get(name) == userId) {
                users.remove(name);
                return;
            }
        }
    }

    private MarketplaceProto.ChatUsers getUserList(){
        return MarketplaceProto.ChatUsers.newBuilder().addAllName(users.keySet()).build();
    }

    private void sendMessage(MarketplaceProto.Message.ChatMessage chatMessage, int id) {
        listener.sendChatMessage(chatMessage, id);
    }

    public void broadcastMessage(MarketplaceProto.Message.ChatMessage chatMessage) {
        for (int i : users.values()) {
            sendMessage(chatMessage, i);
        }
    }

    public void broadcastUserList(){
        broadcastMessage(MessageBuilder.chatUserListMsgBuilder(getUserList()));
    }


    public void receiveMessage(MarketplaceProto.Message.ChatMessage chatMessage, int id) {
        switch (chatMessage.getTypeCase()) {
            case JOIN -> {
                if (!users.containsValue(id)) {
                    users.put(chatMessage.getJoin().getName(), id);
                    MarketplaceProto.ChatUsers.newBuilder().addAllName(users.keySet()).build();
                    broadcastUserList();
                } else {
                    sendMessage(MessageBuilder.chatErrorMsgBuilder("This user had already connected!"), id);
                }
            }
            case END -> {
                if (users.containsValue(id)) {
                    users.remove(chatMessage.getEnd().getName());
                    MarketplaceProto.ChatUsers.newBuilder().addAllName(users.keySet()).build();
                    broadcastUserList();
                } else {
                    sendMessage(MessageBuilder.chatErrorMsgBuilder("This user was not connected!"), id);
                }
            }
            case PUBLIC -> {
                if (users.containsValue(id)) {
                    broadcastMessage(chatMessage);
                } else {
                    sendMessage(MessageBuilder.chatErrorMsgBuilder("This user was not connected!"), id);
                }
            }
            case PRIVATE -> {
                if (users.containsValue(id)) {
                    sendMessage(chatMessage, users.get(chatMessage.getPrivate().getReceiverName()));
                } else {
                    sendMessage(MessageBuilder.chatErrorMsgBuilder("This user was not connected!"), id);
                }
            }
            case LIST -> sendMessage(MessageBuilder.chatErrorMsgBuilder("Users could not send User List messages!"), id);
            case ERROR -> System.err.println(chatMessage.getError().getError());

        }
    }
}
