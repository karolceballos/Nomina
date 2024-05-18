package com.humanblend.saas.event.repository;

import com.humanblend.saas.event.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
