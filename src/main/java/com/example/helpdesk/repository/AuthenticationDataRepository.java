package com.example.helpdesk.repository;

import com.example.helpdesk.entity.AuthenticationData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationDataRepository extends JpaRepository<AuthenticationData, Integer> {

    AuthenticationData findAuthenticationDataByUserName(String userName);

}
