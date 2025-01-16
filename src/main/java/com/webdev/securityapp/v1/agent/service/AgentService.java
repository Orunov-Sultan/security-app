package com.webdev.securityapp.v1.agent.service;

import com.webdev.securityapp.v1.agent.dto.AgentDto;
import com.webdev.securityapp.v1.user.dto.AppUserDto;

import java.util.List;

public interface AgentService {
    AgentDto save(AgentDto agentDto);
    AgentDto findById(Long id);
    List<AgentDto> findAll();
    List<AgentDto> findAllDeActiveUsers();
    AgentDto updateUser(Long id, AgentDto agentDto);
    AgentDto reActivateUser(Long id);
    void delete(Long id);
}
