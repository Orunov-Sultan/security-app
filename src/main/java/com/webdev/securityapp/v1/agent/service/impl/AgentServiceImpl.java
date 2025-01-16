package com.webdev.securityapp.v1.agent.service.impl;

import com.webdev.securityapp.v1.agent.dto.AgentDto;
import com.webdev.securityapp.v1.agent.entity.Agent;
import com.webdev.securityapp.v1.agent.repository.AgentRepository;
import com.webdev.securityapp.v1.agent.service.AgentService;
import com.webdev.securityapp.v1.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;
    private final ModelMapper modelMapper;

    public AgentServiceImpl(AgentRepository agentRepository, ModelMapper modelMapper) {
        this.agentRepository = agentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AgentDto save(AgentDto agentDto) {
        Agent user = modelMapper.map(agentDto, Agent.class);

        user.setCreated_at(LocalDateTime.now());
        user.setIs_deleted(false);

        Agent agent = agentRepository.save(user);

        return modelMapper.map(agent, AgentDto.class);
    }

    @Override
    public AgentDto findById(Long id) {
        Agent agent = agentRepository.findActiveAgentById(id);

        if (agent == null) {
            throw new ResourceNotFoundException("Agent with the id: '" + id + "' does not exist");
        }

        return modelMapper.map(agent, AgentDto.class);
    }

    @Override
    public List<AgentDto> findAll() {
        List<Agent> agents = agentRepository.findAllActiveAgents();
        return agents.stream()
                .map(agent -> modelMapper.map(agent, AgentDto.class))
                .toList();
    }

    @Override
    public List<AgentDto> findAllDeActiveAgent() {
        List<Agent> agents = agentRepository.findAllDeActivatedAgents();
        return agents.stream()
                .map(agent -> modelMapper.map(agent, AgentDto.class))
                .toList();
    }

    @Override
    public AgentDto updateAgent(Long id, AgentDto agentDto) {
        Agent agent = agentRepository.findActiveAgentById(id);

        if (agent == null) {
            throw new ResourceNotFoundException("Agent with the id: '" + id + "' does not exist");
        }

        agent.setLogin(agentDto.getLogin());
        Agent updatedAgent = agentRepository.save(agent);

        return modelMapper.map(updatedAgent, AgentDto.class);
    }

    @Override
    public AgentDto reActivateAgent(Long id) {
        Agent agent = agentRepository.findDeActivatedAgentById(id);

        if (agent == null) {
            throw new ResourceNotFoundException("Agent with the id: '" + id + "' does not exist");
        }

        agent.setIs_deleted(false);

        Agent activatedAgent = agentRepository.save(agent);

        return modelMapper.map(activatedAgent, AgentDto.class);
    }

    @Override
    public void delete(Long id) {
        Agent agent = agentRepository.findActiveAgentById(id);

        if (agent == null) {
            throw new ResourceNotFoundException("Agent with the id: '" + id + "' does not exist");
        }
        agent.setIs_deleted(true);
        agentRepository.save(agent);
    }
}
