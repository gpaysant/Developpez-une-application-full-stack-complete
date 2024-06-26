package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

    /**
     * Create custom request to get all posts of one user, who is subscriber of topic
     * @param user
     * @return List<Post>
     */
    @Query("SELECT p FROM Post p WHERE p.topic IN (SELECT t FROM Topic t WHERE :user MEMBER OF t.users)")
    List<Post> findByUser(User user);
}
