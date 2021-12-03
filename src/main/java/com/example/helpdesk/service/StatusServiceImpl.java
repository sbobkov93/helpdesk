package com.example.helpdesk.service;

import com.example.helpdesk.entity.Status;
import com.example.helpdesk.repository.StatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService{

    private StatusDao statusDao;

    @Autowired
    public StatusServiceImpl(StatusDao statusDao) {
        this.statusDao = statusDao;
    }

    @Override
    public List<Status> findAll() {
        return statusDao.findAll();
    }

    @Override
    public Status getById(Integer statusId) {
        return statusDao.getById(statusId);
    }

    @Override
    public void save(Status status) {
        statusDao.save(status);
    }

    @Override
    public void delete(Integer statusId) {

    }
}
