package dev.nicholasrv.dgtlbilling.repository;

import dev.nicholasrv.dgtlbilling.domain.User;
import dev.nicholasrv.dgtlbilling.dto.UserDTO;
import dev.nicholasrv.dgtlbilling.form.UpdateForm;

import java.util.Collection;

public interface UserRepository <T extends User>{
    T create (T data);
    Collection<T> list(int page, int pageSize);
    T get(Long id);
    T update(T data);
    boolean delete(Long id);

    User getUserByEmail(String email);

    void sendVerificationCode(UserDTO user);

    User verifyCode(String email, String code);

    void resetPassword(String email);

    T verifyPasswordKey(String key);

    void renewPassword(String key, String password, String confirmPassword);

    T verifyAccountKey(String key);

    T updateUserDetails(UpdateForm user);
}
