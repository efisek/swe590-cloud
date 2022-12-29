package com.emrefisek.termproject.repository;

import com.emrefisek.termproject.model.Post;
import com.emrefisek.termproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByUser(User user);
    List<Post> findAllByUserInOrderByInsertTimeDesc(List<User> userList);
}
