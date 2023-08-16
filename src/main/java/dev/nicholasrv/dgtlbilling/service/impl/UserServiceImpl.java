package dev.nicholasrv.dgtlbilling.service.impl;

import dev.nicholasrv.dgtlbilling.domain.User;
import dev.nicholasrv.dgtlbilling.dto.UserDTO;
import dev.nicholasrv.dgtlbilling.dtomapper.UserDTOMapper;
import dev.nicholasrv.dgtlbilling.repository.UserRepository;
import dev.nicholasrv.dgtlbilling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static dev.nicholasrv.dgtlbilling.dtomapper.UserDTOMapper.fromUser;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository<User> userRepository;

    @Override
    public UserDTO createUser(User user) {
        return fromUser(userRepository.create(user));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return fromUser(userRepository.getUserByEmail(email));
    }

    @Override
    public void sendVerificationCode(UserDTO user) {userRepository.sendVerificationCode(user);}

    @Override
    public User getUser(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public UserDTO verifyCode(String email, String code) {
        return fromUser(userRepository.verifyCode(email, code));
    }
}
