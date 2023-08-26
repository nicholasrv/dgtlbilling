package dev.nicholasrv.dgtlbilling.service.impl;

import dev.nicholasrv.dgtlbilling.domain.Role;
import dev.nicholasrv.dgtlbilling.repository.RoleRepository;
import dev.nicholasrv.dgtlbilling.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository<Role> roleRepository;
    @Override
    public Role getRoleByUserId(Long id) {
        return roleRepository.getRoleByUserId(id);
    }
}
