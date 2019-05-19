package com.alex.laba.service;

import com.alex.laba.dao.hibernate.AgencyDAO;
import com.alex.laba.dao.hibernate.TourDAO;
import com.alex.laba.data.Tour;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class TourService {
    private TourDAO dao;
    private AgencyDAO agencyDAO;

    public TourService(TourDAO dao, AgencyDAO agencyDAO) {
        this.dao = dao;
        this.agencyDAO = agencyDAO;
    }

    public void createTour(String description, String name, Long agencyId) throws IllegalArgumentException {

        validateInputs(agencyId);
        Tour tour = new Tour();
        tour.setDescription(description);
        tour.setAgency(agencyId);
        tour.setName(name);
        dao.save(tour);
    }

    public List<Tour> findAll() {
        return dao.findAll();
    }

    private void validateInputs(Long agencyId) throws IllegalArgumentException {
        if (!agencyDAO.findById(agencyId).isPresent()) {
            throw new IllegalArgumentException("Cann't find agency with such id");
        }
    }
}
