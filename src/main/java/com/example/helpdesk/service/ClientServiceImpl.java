package com.example.helpdesk.service;

import com.example.helpdesk.entity.Client;
import com.example.helpdesk.repository.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public Optional<Client> findById(Integer clientId) {
        return clientDao.findById(clientId);
    }

    @Override
    public void save(Client client) {
        clientDao.save(client);
    }

    @Override
    public void delete(Integer clientId) {
        clientDao.deleteById(clientId);
    }

    @Override
    public Optional<Client> findByPrefix(Integer prefix) {
        return clientDao.findByPrefix(prefix);
    }

    @Override
    public Optional<Client> findByShortName(String shortName) {
        return clientDao.findByShortName(shortName);
    }

    @Override
    public Optional<Client> findByName(String name) {
        return clientDao.findByName(name);
    }
}
