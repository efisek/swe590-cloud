package com.emrefisek.termproject.repository;

import com.emrefisek.termproject.model.Like;
import com.emrefisek.termproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LikeRepository  extends JpaRepository<Like, UUID> {
    Optional<Like> findByUserAndPost(UUID userId, UUID postId);
}
