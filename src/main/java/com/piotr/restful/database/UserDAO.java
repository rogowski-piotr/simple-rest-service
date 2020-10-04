package com.piotr.restful.database;

import com.piotr.restful.model.User;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class UserDAO {

    @PersistenceContext(unitName = "nameunit", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    public List getAll() {
        return entityManager.createNamedQuery("User.findAll", User.class).getResultList();
    }

    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    public void update(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    public void create(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public void delete(User user) {
        entityManager.getTransaction().begin();
        if (!entityManager.contains(user)) {
            user = entityManager.merge(user);
        }

        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

}