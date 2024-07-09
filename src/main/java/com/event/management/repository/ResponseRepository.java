package com.event.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.event.management.model.Response;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
