package dev.nicholasrv.dgtlbilling.service.impl;

import dev.nicholasrv.dgtlbilling.domain.Role;
import dev.nicholasrv.dgtlbilling.domain.User;
import dev.nicholasrv.dgtlbilling.dto.UserDTO;
import dev.nicholasrv.dgtlbilling.repository.RoleRepository;
import dev.nicholasrv.dgtlbilling.repository.UserRepository;
import dev.nicholasrv.dgtlbilling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static dev.nicholasrv.dgtlbilling.dtomapper.UserDTOMapper.fromUser;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository<User> userRepository;
    private final RoleRepository<Role> roleRepository;

    @Override
    public UserDTO createUser(User user) {
        return mapToUserDTO(userRepository.create(user));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return mapToUserDTO(userRepository.getUserByEmail(email));
    }

    @Override
    public void sendVerificationCode(UserDTO user) {userRepository.sendVerificationCode(user);}

    @Override
    public UserDTO verifyCode(String email, String code) {
        return mapToUserDTO(userRepository.verifyCode(email, code));
    }

    @Override
    public void resetPassword(String email) {
        userRepository.resetPassword(email);
    }

    @Override
    public UserDTO verifyPasswordKey(String key) {
        return mapToUserDTO(userRepository.verifyPasswordKey(key));
    }

    @Override
    public void renewPassword(String key, String password, String confirmPassword) {
        userRepository.renewPassword(key, password, confirmPassword);
    }

    @Override
    public UserDTO verifyAccountKey(String key) {
        return mapToUserDTO(userRepository.verifyAccountKey(key));
    }

    private UserDTO mapToUserDTO(User user) {
        return fromUser(user, roleRepository.getRoleByUserId(user.getId()));
    }
}
