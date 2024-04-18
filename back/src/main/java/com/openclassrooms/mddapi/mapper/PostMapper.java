package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Custom PostMapper to create expected mapping
 */
@Component
@Mapper(componentModel = "spring", uses = { }, imports = {Arrays.class, Collectors.class, Post.class, User.class, Topic.class, Collections.class, Optional.class, TopicDto.class, UserDto.class})
public abstract class PostMapper implements EntityMapper<PostDto, Post> {

    /**
     * This method create a mapper between PostDto and Post.
     * This custom method is created to avoid cyclic relation between post, topic and user.
     * @param postDto
     * @return Post
     */
    @Mappings({
            @Mapping(target = "topic", expression = "java(postDto.getTopicDto() != null ? new Topic(postDto.getTopicDto().getId(), postDto.getTopicDto().getTitle(), postDto.getTopicDto().getDescription(), null) : null)"),
            @Mapping(target = "user", expression = "java(postDto.getUserDto() != null ? new User(postDto.getUserDto().getId(), postDto.getUserDto().getUsername(), postDto.getUserDto().getEmail(), null) : null)"),
    })
    public abstract Post toEntity(PostDto postDto);


    /**
     * This method create a mapper between Post and PostDto.
     * This custom method is created to avoid cyclic relation between post, topic and user.
     * @param post
     * @return PostDto
     */
    @Mappings({
            @Mapping(target = "topicDto" ,expression = "java(post.getTopic() != null ? new TopicDto(post.getTopic().getId(), post.getTopic().getTitle(), post.getContent(), null) : null)"),
            @Mapping(target = "userDto" ,expression = "java(post.getUser() != null ? new UserDto(post.getUser().getId(), post.getUser().getUsername(), post.getUser().getEmail(), null) : null)"),
    })
    public abstract PostDto toDto(Post post);
}