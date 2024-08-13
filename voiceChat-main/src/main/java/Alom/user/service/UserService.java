package Alom.user.service;

import Alom.user.domain.User;
import Alom.user.repository.UserRepository;
import Alom.user.utils.UserNotFoundException;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    private static final String USER_NOT_FOUND_MESSAGE = "해당 아이디를 가진 사용자가 없습니다: ";

    @Autowired
    private UserRepository userRepository;

    //유저 생성(임시)
    public User createUser(User user) {
        user.setUserUuid(UUID.randomUUID());
        user.setUserCreateDate(LocalDateTime.now());
        user.setUserUpdateDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    //아이디로 유저 찾기
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException(USER_NOT_FOUND_MESSAGE + userId));
    }

    public User getUserByUuid(UUID userUuid) {
        return userRepository.findByUserUuid(userUuid);
    }

    //로그아웃
    public void logoutUser(Long userId) {
        //이건 로그인 방법을 알아야 할 수 있을 듯..
        //ex: 토큰 삭제..?
    }


    //회원탈퇴
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


    //프로필 변경
    public User updateProfile(Long userId, byte[] newUserIcon, String newUserNickname, String newUserMent) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setUserIcon(newUserIcon);
                    user.setUserNickname(newUserNickname);
                    user.setUserMent(newUserMent);
                    user.setUserUpdateDate(LocalDateTime.now());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new IllegalIdentifierException(USER_NOT_FOUND_MESSAGE + userId));
    }


    //사용자 차단
    public void blockUser(Long userId, String blockUserId) {
        if (!userRepository.existsById(blockUserId)) {
            throw new UserNotFoundException(USER_NOT_FOUND_MESSAGE + blockUserId);
        }
        User user = getUserById(userId);
        user.getUserBlockList().add(blockUserId);
        userRepository.save(user);
    }


    //차단 해제
    public void unblockUser(Long userId, String unblockUserId) {
        User user = getUserById(userId);
        if (!user.getUserBlockList().contains(unblockUserId)) {
            throw new UserNotFoundException(USER_NOT_FOUND_MESSAGE + unblockUserId);
        }
        user.getUserBlockList().remove(unblockUserId);
        userRepository.save(user);
    }

    //차단한 사용자 목록
    public Set<String> getBlockedUsers(Long userId) {
        User user = getUserById(userId);
        return user.getUserBlockList();
    }
}
