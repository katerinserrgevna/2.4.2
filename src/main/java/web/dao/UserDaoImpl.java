package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext()
    private EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    public UserDaoImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUser (int id) {
        TypedQuery<User> typeQ = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.id = :id",
                User.class
        );
        typeQ.setParameter("id", id);
        return typeQ.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public User getUserByUserName(String userName) {
        TypedQuery<User> typeQ = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.userName = :userName",
                User.class
        );
        typeQ.setParameter("userName", userName);
        return typeQ.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public User update(User user) {
        if(getUser(user.getId()).getPassword() != user.getPassword()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return entityManager.merge(user);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(getUser(id));
    }
}
