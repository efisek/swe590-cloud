package com.emrefisek.termproject.service;

import com.emrefisek.termproject.model.Like;
import com.emrefisek.termproject.model.User;

import java.util.UUID;

public interface LikeService {
    void like(UUID userId, Like like);
    void removeLike(UUID userId, String postId);
    Like getByUserAndPostId(UUID userId, String postId);
}
