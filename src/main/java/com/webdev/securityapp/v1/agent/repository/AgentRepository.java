package com.webdev.securityapp.v1.agent.repository;

import com.webdev.securityapp.v1.agent.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    @Query("SELECT u FROM Agent u WHERE u.is_deleted = false")
    List<Agent> findAllActiveUsers();

    @Query("SELECT u FROM Agent u WHERE u.is_deleted = true")
    List<Agent> findAllDeActivatedUsers();

    @Query("SELECT u FROM Agent u WHERE u.is_deleted = false AND u.id = :id")
    Agent findActiveUserById(Long id);

    @Query("SELECT u FROM Agent u WHERE u.is_deleted = true AND u.id = :id")
    Agent findDeActivatedUserById(Long id);
}
