package com.emrefisek.termproject.controller;

import com.emrefisek.termproject.model.Like;
import com.emrefisek.termproject.security.CurrentUser;
import com.emrefisek.termproject.security.UserPrincipal;
import com.emrefisek.termproject.service.LikeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/like")
    @PreAuthorize("hasRole('USER')")
    public void like(@CurrentUser UserPrincipal userPrincipal, @RequestBody Like like) {
        likeService.like(userPrincipal.getId(), like);
    }

    @DeleteMapping("/like/{postId}")
    @PreAuthorize("hasRole('USER')")
    public void dislike(@CurrentUser UserPrincipal userPrincipal, @PathVariable String postId) {
        likeService.removeLike(userPrincipal.getId(), postId);
    }
}
