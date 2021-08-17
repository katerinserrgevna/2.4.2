package web.dao;


import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role getRoleByName(String roleName) {
        return entityManager.createQuery(
                "SELECT u FROM Role u WHERE u.roleName = :roleName", Role.class)
                .setParameter("roleName", roleName)
                .getSingleResult();
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("FROM Role", Role.class)
                .getResultList();
    }
}
