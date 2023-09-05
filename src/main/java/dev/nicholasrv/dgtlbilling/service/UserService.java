package dev.nicholasrv.dgtlbilling.service;

import dev.nicholasrv.dgtlbilling.domain.User;
import dev.nicholasrv.dgtlbilling.dto.UserDTO;
import dev.nicholasrv.dgtlbilling.form.UpdateForm;

public interface UserService {
    UserDTO createUser(User user);
    UserDTO getUserByEmail(String email);
    void sendVerificationCode(UserDTO user);

    UserDTO verifyCode(String email, String code);
    void resetPassword(String email);
    UserDTO verifyPasswordKey(String key);

    void renewPassword(String key, String password, String confirmPassword);

    UserDTO verifyAccountKey(String key);

    UserDTO updateUserDetails(UpdateForm user);

    UserDTO getUserById(Long userId);
}
