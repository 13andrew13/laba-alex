package com.alex.laba.service;

import com.alex.laba.dao.hibernate.AgencyDAO;
import com.alex.laba.dao.hibernate.AgentDAO;
import com.alex.laba.data.Agent;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class AgentService {
    private final AgentDAO agentDAO;
    private final AgencyDAO agencyDAO;

    public AgentService(AgentDAO agentDAO, AgencyDAO agencyDAO) {
        this.agentDAO = agentDAO;
        this.agencyDAO = agencyDAO;
    }

    public Agent createAgent(String name, Long agencyId) throws IllegalArgumentException {

        if (!agencyDAO.findById(agencyId).isPresent()) {
            throw new IllegalArgumentException("Cann't find agency with such id");
        }

        Agent agent = new Agent();

        agent.setAgencyId(agencyId);
        agent.setAgentName(name);
        agentDAO.save(agent);
        return agent;
    }

    public List<Agent> findAllAgents() {
        return agentDAO.findAll();
    }
}
