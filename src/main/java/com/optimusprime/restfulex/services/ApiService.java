package com.optimusprime.restfulex.services;

import api.domain.User;

import java.util.List;

public interface ApiService {

    List<User> getUsers(Integer limit);
}
