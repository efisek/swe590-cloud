package com.emrefisek.termproject.service.impl;

import com.emrefisek.termproject.exception.ResourceNotFoundException;
import com.emrefisek.termproject.model.Post;
import com.emrefisek.termproject.model.User;
import com.emrefisek.termproject.repository.PostRepository;
import com.emrefisek.termproject.service.PostService;
import com.emrefisek.termproject.service.UserService;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public List<Post> getByUserId(String userId) {
        return postRepository.findAllByUser(userService.getByUserId(userId));
    }

    @Override
    public Post getById(String postId) {
        return postRepository.findById(UUID.fromString(postId)).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
    }

    @Override
    public List<Post> getFlow(UUID userId) {
        return postRepository.findAllByUserInOrderByInsertTimeDesc(userService.getByUserId(userId).getFollowing());
    }

    @Override
    public void post(UUID userId, Post post) {
        post.setId(UUID.randomUUID());
        post.setInsertTime(LocalDateTime.now());
        post.setUser(userService.getByUserId(userId));
        postRepository.save(post);
    }

    @Override
    public void deletePost(UUID userId, UUID postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
        if(!post.getUser().getId().equals(userId)) {
            throw new AuthorizationServiceException("Not Authorized");
        }
        postRepository.delete(post);
    }
}
