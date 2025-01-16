package com.webdev.securityapp.v1.agent.controller;


import com.webdev.securityapp.v1.agent.dto.AgentDto;
import com.webdev.securityapp.v1.agent.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agents")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping
    public ResponseEntity<AgentDto> addAgent(@RequestBody AgentDto agentDto) {
        AgentDto agent = agentService.save(agentDto);
        return new ResponseEntity<>(agent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentDto> getAgent(@PathVariable Long id) {
        AgentDto agentDto = agentService.findById(id);
        return ResponseEntity.ok(agentDto);
    }

    @GetMapping
    public ResponseEntity<List<AgentDto>> getAllAgents() {
        List<AgentDto> agentDto = agentService.findAll();
        return ResponseEntity.ok(agentDto);
    }

    @GetMapping("/deactivated-agents")
    public ResponseEntity<List<AgentDto>> getAllDeactivatedAgents() {
        List<AgentDto> agentsDto = agentService.findAllDeActiveAgents();
        return ResponseEntity.ok(agentsDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgentDto> updateAgent(@PathVariable Long id, @RequestBody AgentDto agentDto) {
        AgentDto updatedAgent = agentService.updateAgent(id, agentDto);
        return new ResponseEntity<>(updatedAgent, HttpStatus.OK);
    }

    @PutMapping("/reactivate/{id}")
    public ResponseEntity<AgentDto> reActivateUser(@PathVariable Long id) {
        AgentDto reActivatedUser = agentService.reActivateAgent(id);
        return new ResponseEntity<>(reActivatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        agentService.delete(id);
        return new ResponseEntity<>("Agent with the id: '" + id + "' has been deleted", HttpStatus.OK);
    }
}
