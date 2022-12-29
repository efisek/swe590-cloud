package com.emrefisek.termproject.service;

import com.emrefisek.termproject.model.Post;
import com.emrefisek.termproject.model.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getByUserId(String userId);
    Post getById(String postId);
    List<Post> getFlow(UUID userId);

    void post(UUID userId, Post post);

    void deletePost(UUID userId, UUID postId);
}
