package dev.nicholasrv.dgtlbilling.utils;

import dev.nicholasrv.dgtlbilling.domain.UserPrincipal;
import dev.nicholasrv.dgtlbilling.dto.UserDTO;
import org.springframework.security.core.Authentication;

public class UserUtils {
    public static UserDTO getAuthenticatedUser(Authentication authentication) {
        return ((UserDTO) authentication.getPrincipal());
    }

    public static UserDTO getLoggedInUser(Authentication authentication) {
        return((UserPrincipal) authentication.getPrincipal()).getUser();
    }
}
