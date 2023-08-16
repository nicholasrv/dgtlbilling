package dev.nicholasrv.dgtlbilling.service;

import dev.nicholasrv.dgtlbilling.domain.Role;

public interface RoleService {
    Role getRoleByUserId(Long id);
}
