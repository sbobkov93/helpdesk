package com.example.helpdesk.service;

import com.example.helpdesk.entity.Client;
import com.example.helpdesk.repository.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    private ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public Client getById(Integer clientId) {
        return clientDao.getById(clientId);
    }

    @Override
    public void save(Client client) {
        clientDao.save(client);
    }
}
