package Arom.voiceChat.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ChatRoomDto {
    @NotNull
    private String roomId;
    private String roomName;
    private int userCount;
    private int maxUserCnt;

    private String roomPassword;
    private boolean isPrivate;

    private Map<String ,?> userList;
}
