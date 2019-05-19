package com.alex.laba.web.controller;

import com.alex.laba.service.AgentService;
import com.alex.laba.web.ValidationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AgentController {
    private final AgentService service;

    public AgentController(AgentService service) {
        this.service = service;
    }

    @GetMapping("/agent")
    public ModelAndView findAllAgents(HttpServletRequest request, ModelAndView modelAndView) {
        request.setAttribute("agents", service.findAllAgents());
        modelAndView.setViewName("agent");
        return modelAndView;
    }

    @PostMapping("/agent")
    public ModelAndView createAgency(
            HttpServletRequest request,
            ModelAndView modelAndView,
            @RequestParam(name = "agent_name") String agentName,
            @RequestParam(name = "agency_id") String agencyID
    ) {
        if (!ValidationUtils.validateInt(agencyID)) {
            setError(request, "Agency id should contain only digits!");
            return modelAndView;
        }
        try {
            service.createAgent(agentName, Long.parseLong(agencyID));
        } catch (IllegalArgumentException exception) {
            setError(request, exception.getMessage());
            return modelAndView;
        }
        request.setAttribute("agents", service.findAllAgents());
        return modelAndView;
    }

    private void setError(HttpServletRequest request, String message) {
        request.setAttribute("agents", service.findAllAgents());
        request.setAttribute("errorMessage", message);
    }
}
