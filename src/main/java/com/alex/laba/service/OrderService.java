package com.alex.laba.service;

import com.alex.laba.dao.hibernate.AgentDAO;
import com.alex.laba.dao.hibernate.OrderDAO;
import com.alex.laba.dao.hibernate.TourDAO;
import com.alex.laba.dao.hibernate.UserDAO;
import com.alex.laba.data.Order;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private final OrderDAO dao;
    private final UserDAO userDAO;
    private final AgentDAO agentDAO;
    private final TourDAO tourDAO;

    public OrderService(OrderDAO dao, UserDAO userDAO, AgentDAO agentDAO, TourDAO tourDAO) {
        this.dao = dao;
        this.userDAO = userDAO;
        this.agentDAO = agentDAO;
        this.tourDAO = tourDAO;
    }

    public void createOrder(Long userId, Long agentId, Long tourId, Long cost) {

        validateInputs(userId, agentId, tourId);
        Order order = new Order();
        order.setCost(cost);
        order.setUserId(userId);
        order.setAgentId(agentId);
        order.setTourId(tourId);
        dao.save(order);
    }

    public List<Order> findAll() {
        return dao.findAll();
    }

    private void validateInputs(Long userId, Long agentId, Long tourId) throws IllegalArgumentException {
        if (!userDAO.findById(userId).isPresent()) {
            throw new IllegalArgumentException("Cann't find user with such id");
        } else if (!agentDAO.findById(agentId).isPresent()) {
            throw new IllegalArgumentException("Cann't find agent with such id");
        } else if (!tourDAO.findById(tourId).isPresent()) {
            throw new IllegalArgumentException("Cann't find tour with such id");
        }
    }
}
