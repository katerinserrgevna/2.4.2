package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleByName(String roleName);
    void saveRole(Role role);
    List<Role> getRoles();
}
