package com.emrefisek.termproject.service.impl;

import com.emrefisek.termproject.exception.ResourceNotFoundException;
import com.emrefisek.termproject.model.Like;
import com.emrefisek.termproject.model.User;
import com.emrefisek.termproject.repository.LikeRepository;
import com.emrefisek.termproject.service.LikeService;
import com.emrefisek.termproject.service.PostService;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;

    public LikeServiceImpl(LikeRepository likeRepository, PostService postService) {
        this.likeRepository = likeRepository;
        this.postService = postService;
    }

    @Override
    public void like(UUID userId, Like like) {
        if(!like.getUser().getId().equals(userId)) {
            throw new AuthorizationServiceException("Not Authorized");
        }
        like.setId(UUID.randomUUID());
        likeRepository.save(like);
    }

    @Override
    public void removeLike(UUID userId, String postId) {
        likeRepository.delete(getByUserAndPostId(userId, postId));
    }

    @Override
    public Like getByUserAndPostId(UUID userId, String postId) {
        return likeRepository.findByUserAndPost(userId, UUID.fromString(postId)).orElseThrow(() -> new ResourceNotFoundException("like", "postId", postId));
    }
}
