package pl.wsb.fitnesstracker.event.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.event.Event;

import java.util.List;

@Repository
public class EventRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Event> findByName(String name) {
        String jpql = "SELECT e FROM Event e WHERE e.name = :name";
        TypedQuery<Event> query = entityManager.createQuery(jpql, Event.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}
