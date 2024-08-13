package Alom.user.controller;

import Alom.user.domain.User;
import Alom.user.service.NotificationService;
import Alom.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /*@Autowired
    private NotificationService notificationService;*/

    //유저 생성(임시)
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}/profile")
    public User updateProfile(@PathVariable Long userId, @RequestBody User updatedUser) {
        return userService.updateProfile(userId, updatedUser.getUserIcon(),
                updatedUser.getUserNickname(), updatedUser.getUserMent());
    }

    @PostMapping("/{userId}/block/{blockUserId}")
    public void blockUser(@PathVariable Long userId, @PathVariable String blockUserId) {
        userService.blockUser(userId, blockUserId);
    }

    @DeleteMapping("/{userId}/block/{unblockUserId}")
    public void unblockUser(@PathVariable Long userId, @PathVariable String unblockUserId) {
        userService.unblockUser(userId, unblockUserId);
    }

    @PostMapping("/{userId}/logout")
    public void logoutUser(@PathVariable Long userId) {
        userService.logoutUser(userId);
    }

    @GetMapping("/{userId}/blocked")
    public Set<String> getBlockedUsers(@PathVariable Long userId) {
        return userService.getBlockedUsers(userId);
    }
}
