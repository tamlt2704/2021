package io.spmegatuto.service;


import io.spmegatuto.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String userName);

    List<User> findAllUSers();
}