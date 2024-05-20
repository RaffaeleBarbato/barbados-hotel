package com.barbados.service;


import com.barbados.model.Role;
import com.barbados.model.Utente;

import java.util.List;

public interface IRoleService {
    List<Role> getRoles();
    Role createRole(Role theRole);

    void deleteRole(Long id);
    Role findByName(String name);

    Utente removeUserFromRole(Long userId, Long roleId);
    Utente assignRoleToUser(Long userId, Long roleId);
    Role removeAllUsersFromRole(Long roleId);
}