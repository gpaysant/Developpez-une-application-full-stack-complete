package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.PostCreationRequest;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;

import java.util.List;

public interface IPostService {

    Post findById(Long id);

    PostDto convertToDto(PostCreationRequest postCreationRequest);

    void addPost(Post post);

    List<Post> findByUser(User user);
}
