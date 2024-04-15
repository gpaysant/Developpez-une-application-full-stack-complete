package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.PostCreationRequestDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostSimpleDto;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;

import java.util.List;

public interface IPostService {

    Post findById(Long id);

    PostSimpleDto getPost(String idPost);

    PostDto convertToDto(String name, PostCreationRequestDto postCreationRequest);

    void addPost(String email, PostCreationRequestDto postCreationRequestDto);

    List<Post> findByUser(User user);

    List<PostSimpleDto> getPostsOf(String email);
}
