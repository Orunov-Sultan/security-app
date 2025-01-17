package com.webdev.securityapp.v1.agent.controller;


import com.webdev.securityapp.v1.agent.dto.AgentDto;
import com.webdev.securityapp.v1.agent.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agents")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PreAuthorize("hasRole('EMPLOYEE') and hasRole('AGENT')")
    @PostMapping
    public ResponseEntity<AgentDto> addAgent(@RequestBody AgentDto agentDto) {
        AgentDto agent = agentService.save(agentDto);
        return new ResponseEntity<>(agent, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('EMPLOYEE') and hasRole('AGENT')")
    @GetMapping("/{id}")
    public ResponseEntity<AgentDto> getAgent(@PathVariable Long id) {
        AgentDto agentDto = agentService.findById(id);
        return ResponseEntity.ok(agentDto);
    }

    @PreAuthorize("hasRole('EMPLOYEE') and hasRole('AGENT')")
    @GetMapping
    public ResponseEntity<List<AgentDto>> getAllAgents() {
        List<AgentDto> agentDto = agentService.findAll();
        return ResponseEntity.ok(agentDto);
    }

    @PreAuthorize("hasRole('EMPLOYEE') and hasRole('AGENT')")
    @GetMapping("/deactivated-agents")
    public ResponseEntity<List<AgentDto>> getAllDeactivatedAgents() {
        List<AgentDto> agentsDto = agentService.findAllDeActiveAgents();
        if (agentsDto.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(agentsDto);
    }

    @PreAuthorize("hasRole('EMPLOYEE') and hasRole('AGENT')")
    @PutMapping("/{id}")
    public ResponseEntity<AgentDto> updateAgent(@PathVariable Long id, @RequestBody AgentDto agentDto) {
        AgentDto updatedAgent = agentService.updateAgent(id, agentDto);
        return new ResponseEntity<>(updatedAgent, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') and hasRole('AGENT')")
    @PutMapping("/reactivate/{id}")
    public ResponseEntity<AgentDto> reActivateUser(@PathVariable Long id) {
        AgentDto reActivatedUser = agentService.reActivateAgent(id);
        return new ResponseEntity<>(reActivatedUser, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') and hasRole('AGENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        agentService.delete(id);
        return new ResponseEntity<>("Agent with the id: '" + id + "' has been deleted", HttpStatus.OK);
    }
}
