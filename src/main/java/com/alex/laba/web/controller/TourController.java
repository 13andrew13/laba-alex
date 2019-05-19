package com.alex.laba.web.controller;

import com.alex.laba.service.TourService;
import com.alex.laba.web.ValidationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TourController {
    private final TourService service;

    public TourController(TourService service) {
        this.service = service;
    }

    @GetMapping("/tour")
    public ModelAndView findAllTours(HttpServletRequest request, ModelAndView modelAndView) {
        request.setAttribute("tours", service.findAll());
        modelAndView.setViewName("tour");
        return modelAndView;
    }

    @PostMapping("/tour")
    public ModelAndView createTour(
            HttpServletRequest request,
            ModelAndView modelAndView,
            @RequestParam(name = "tour_description") String description,
            @RequestParam(name = "tour_name") String tourName,
            @RequestParam(name = "tour_agency") String agencyID
    ) {
        if (!ValidationUtils.validateInt(agencyID)) {
            setError(request, "Agency id should contain only digits!");
            return modelAndView;
        }
        try {
            service.createTour(description, tourName, Long.parseLong(agencyID));
        } catch (IllegalArgumentException exception) {
            setError(request, exception.getMessage());
            return modelAndView;
        }
        request.setAttribute("tours", service.findAll());
        return modelAndView;
    }

    private void setError(HttpServletRequest request, String message) {
        request.setAttribute("tours", service.findAll());
        request.setAttribute("errorMessage", message);
    }
}