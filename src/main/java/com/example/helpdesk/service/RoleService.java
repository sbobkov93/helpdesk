package com.example.helpdesk.service;

import com.example.helpdesk.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> findById(Integer id);

    List<Role> findAll();

}
