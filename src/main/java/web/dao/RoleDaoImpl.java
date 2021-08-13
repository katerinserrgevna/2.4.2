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
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Override
    public Role getRoleById(int id) {
        TypedQuery<Role> q = entityManager.createQuery(
                "SELECT u FROM Role u WHERE u.id = :id", Role.class
        );
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }
}
