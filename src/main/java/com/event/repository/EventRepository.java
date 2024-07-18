package com.event.repository;

import com.event.entity.Event;
import com.event.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    //    Page<Event> findAll(Pageable pageable);
    Optional<Event> findByIdAndUser(long id, User user);

    List<Event> findByUser(User user);
}

