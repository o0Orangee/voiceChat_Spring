package Alom.voiceChat.service;

import Arom.voiceChat.dto.ChatRoomDto;
import Arom.voiceChat.utils.ChatRoomMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class ChatServiceMain {

    private final MsgChatService msgChatService;
    private final RtcChatService rtcChatService;

    private final FileService fileService;

    public List<ChatRoomDto> findAllRoom() {
        List<ChatRoomDto> chatRooms = new ArrayList<>(ChatRoomMap.getInstance().getChatRooms().values());
        Collections.reverse(chatRooms);

        return chatRooms;
    }

    public ChatRoomDto findRoomById(String roomId){
        return ChatRoomMap.getInstance().getChatRooms().get(roomId);
    }

    public ChatRoomDto createChatRoom(String roomName, String roomPassword, boolean isPrivate , int maxUserCnt, String chatType ){
        ChatRoomDto room;
        if (chatType.equals("msgChat")){
            room = msgChatService.createChatRoom(roomName, roomPassword,isPrivate,maxUserCnt);
        }
        
        else{
            room = msgChatService.createChatRoom(roomName, roomPassword,isPrivate,maxUserCnt);
        }
        return room;
    }

    public boolean confirmPassword (String roomId, String roomPassword){

        return roomPassword.equals(ChatRoomMap.getInstance().getChatRooms().get(roomId).getRoomPassword());

    }

    public void plusUserCnt (String roomId){
        log.info("cnt {}", ChatRoomMap.getInstance().getChatRooms().get(roomId).getUserCount());
        ChatRoomDto room = ChatRoomMap.getInstance().getChatRooms().get(roomId);
        room.setUserCount(room.getUserCount()+1);
    }

    public void minusUserCnt (String roomId){
        log.info("cnt {}", ChatRoomMap.getInstance().getChatRooms().get(roomId).getUserCount());
        ChatRoomDto room = ChatRoomMap.getInstance().getChatRooms().get(roomId);
        room.setUserCount(room.getUserCount()-1);
    }

    public boolean checkRoomUserCnt(String roomId){
        ChatRoomDto room =ChatRoomMap.getInstance().getChatRooms().get(roomId);
        if (room.getMaxUserCnt()+1 > room.getMaxUserCnt()){
            return false;
        }
    }

}
