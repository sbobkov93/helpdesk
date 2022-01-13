package com.example.helpdesk.service;

import com.example.helpdesk.entity.AuthenticationData;

public interface AuthenticationDataService {

    AuthenticationData findAuthenticationDataByUserName(String userName);

}
