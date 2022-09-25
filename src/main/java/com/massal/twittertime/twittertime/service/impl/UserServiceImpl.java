package com.massal.twittertime.twittertime.service.impl;

import com.massal.twittertime.twittertime.model.User;
import com.massal.twittertime.twittertime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.massal.twittertime.twittertime.repo.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired //Pas obligatoire comme on a mis @Service
    public UserServiceImpl (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUser() { return userRepository.findAll();}
}

