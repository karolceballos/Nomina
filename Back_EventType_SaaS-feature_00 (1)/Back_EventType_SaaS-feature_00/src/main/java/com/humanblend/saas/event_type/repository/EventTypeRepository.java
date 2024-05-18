package com.humanblend.saas.event_type.repository;

import com.humanblend.saas.event_type.entities.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository<EventType, Integer> {
}
