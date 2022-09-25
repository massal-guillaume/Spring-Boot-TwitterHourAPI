package com.massal.twittertime.twittertime.service;

import com.massal.twittertime.twittertime.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    List<User> getUser();

}
