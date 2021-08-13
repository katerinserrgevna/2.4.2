package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInit {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        roleService.saveRole(new Role("USER"));
        roleService.saveRole(new Role("ADMIN"));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleById(1));
        userService.saveUser(new User("user", "1", roles));
        roles.add(roleService.getRoleById(2));
        userService.saveUser(new User("admin", "2", roles));
    }
}
