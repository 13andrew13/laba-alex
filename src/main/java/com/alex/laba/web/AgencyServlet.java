package com.alex.laba.web;

import com.alex.laba.dao.AgencyDAO;
import com.alex.laba.exception.ValidationException;
import com.alex.laba.service.AgencyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AgencyServlet extends HttpServlet {
    private AgencyDAO dao = new AgencyDAO();
    private AgencyService service = new AgencyService(dao);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("agencies", service.findAllAgencies());
        req.getRequestDispatcher("agency.jsp").forward(req, resp);
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("agency_name");
        if (!ValidationUtils.validateString(name)) {
            throw new ValidationException("Agency name is not valid");

        } else {
            service.createAgency(name);
            req.setAttribute("agencies", service.findAllAgencies());
            req.getRequestDispatcher("agency.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}