package dev.nicholasrv.dgtlbilling.service;

import dev.nicholasrv.dgtlbilling.domain.User;
import dev.nicholasrv.dgtlbilling.dto.UserDTO;

public interface UserService {
    UserDTO createUser(User user);
    UserDTO getUserByEmail(String email);
    void sendVerificationCode(UserDTO user);

    UserDTO verifyCode(String email, String code);

    public void resetPassword(String email);
}
