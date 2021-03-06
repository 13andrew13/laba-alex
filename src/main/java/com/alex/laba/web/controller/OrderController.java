package com.alex.laba.web.controller;

import com.alex.laba.data.Order;
import com.alex.laba.service.OrderService;
import com.alex.laba.web.ValidationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/order")
    public ModelAndView findAllOrders(HttpServletRequest request, ModelAndView modelAndView) {
        request.setAttribute("orders", service.findAll());
        modelAndView.setViewName("order");
        return modelAndView;
    }

    @PostMapping("/order")
    public ModelAndView createOrder(
            HttpServletRequest request,
            ModelAndView modelAndView,
            @RequestParam(name = "user_id") String userID,
            @RequestParam(name = "agent_id") String agentID,
            @RequestParam(name = "tour_id") String tourID,
            @RequestParam(name = "cost") String cost
    ) {
        if (!ValidationUtils.validateInt(userID)) {
            setError(request, "User id should contain only digits!");
            return modelAndView;
        } else if (!ValidationUtils.validateInt(agentID)) {
            setError(request, "Agent id should contain only digits!");
            return modelAndView;
        } else if (!ValidationUtils.validateInt(tourID)) {
            setError(request, "Tour id should contain only digits!");
            return modelAndView;
        } else if (!ValidationUtils.validateInt(cost)) {
            setError(request, "Cost should contain only digits!");
            return modelAndView;
        }
        try {
            service.createOrder(Long.parseLong(userID), Long.parseLong(agentID), Long.parseLong(tourID), Long.parseLong(cost));
        } catch (IllegalArgumentException exception) {
            setError(request, exception.getMessage());
            return modelAndView;
        }
        request.setAttribute("orders", service.findAll());
        modelAndView.setViewName("order");
        return modelAndView;
    }

    private void setError(HttpServletRequest request, String message) {
        request.setAttribute("agents", service.findAll());
        request.setAttribute("errorMessage", message);
    }
}