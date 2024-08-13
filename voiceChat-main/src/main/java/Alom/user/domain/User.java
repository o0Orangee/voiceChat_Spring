package Alom.user.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.w3c.dom.Text;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;            //Primary Key

    private String userName;        //이름
    private String userNickname;    //닉네임

    @Lob
    private byte[] userIcon;        //이미지 저장
    private String userMent;        //상태 메시지

    @ElementCollection
    private Set<String> userBlockList;  //차단한 유저 리스트

    @Column(unique = true)
    private UUID userUuid;          //유저 구분하는거..?

    private String userProvider;    //이건뭐지
    private String userProviderId;

    private LocalDateTime userCreateDate;   //계정 생성 날짜
    private LocalDateTime userUpdateDate;   //계정 업데이트(정보 수정?) 날짜

    //프로필 업데이트
    public void updateProfile(String newNickname, byte[] newIcon, String newMent) {
        this.userNickname = newNickname;
        this.userIcon = newIcon;
        this.userMent = newMent;
        this.userUpdateDate = LocalDateTime.now();
    }

    //차단한 유저 차단 목록에 추가

    //차단한 유저 차단 해제

}