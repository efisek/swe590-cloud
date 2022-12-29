package com.emrefisek.termproject.service.impl;

import com.emrefisek.termproject.exception.ResourceNotFoundException;
import com.emrefisek.termproject.model.User;
import com.emrefisek.termproject.repository.UserRepository;
import com.emrefisek.termproject.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getByUserId(String userId) {
        return userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
    }

    @Override
    public User getByUserId(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
    }

    @Override
    public List<User> getFollowers(String userId) {
        return getByUserId(userId).getFollowers();
    }

    @Override
    public List<User> getFollowing(String userId) {
        return getByUserId(userId).getFollowing();
    }

    @Override
    public void deleteById(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void followById(UUID currentUserId,String userId) {
        User user = userRepository.findById(currentUserId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
        user.getFollowing().add(getByUserId(userId));
        userRepository.save(user);
    }
}
