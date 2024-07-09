package com.event.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.event.management.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
