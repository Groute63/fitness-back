package org.example.repository;

import org.example.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity,Long> {
    @Query(value = "select * from event where token_id = ?1", nativeQuery = true)
    List<EventEntity> findAllKEK(Long tokenId);
}
