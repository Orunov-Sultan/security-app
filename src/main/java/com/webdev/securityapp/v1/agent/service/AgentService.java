package com.webdev.securityapp.v1.agent.service;

import com.webdev.securityapp.v1.agent.dto.AgentDto;

import java.util.List;

public interface AgentService {
    AgentDto save(AgentDto agentDto);
    AgentDto findById(Long id);
    List<AgentDto> findAll();
    List<AgentDto> findAllDeActiveAgents();
    AgentDto updateAgent(Long id, AgentDto agentDto);
    AgentDto reActivateAgent(Long id);
    void delete(Long id);
}
