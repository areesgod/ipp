package com.example.ipp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ipp.model.Event;
@Repository
public interface EventRepository extends JpaRepository<Event,Long>{
}
