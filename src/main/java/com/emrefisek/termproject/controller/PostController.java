package com.emrefisek.termproject.controller;

import com.emrefisek.termproject.model.Post;
import com.emrefisek.termproject.security.CurrentUser;
import com.emrefisek.termproject.security.UserPrincipal;
import com.emrefisek.termproject.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/flow")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Post>> getFlow(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(postService.getFlow(userPrincipal.getId()));
    }

    @PostMapping("/post")
    @PreAuthorize("hasRole('USER')")
    public void postMessage(@CurrentUser UserPrincipal userPrincipal, @RequestBody Post post) {
        postService.post(userPrincipal.getId(), post);
    }

    @DeleteMapping("/post/{postId}")
    @PreAuthorize("hasRole('USER')")
    public void postMessage(@CurrentUser UserPrincipal userPrincipal, @PathVariable String postId) {
        postService.deletePost(userPrincipal.getId(), UUID.fromString(postId));
    }
}
