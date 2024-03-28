package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.ICommentService;
import com.openclassrooms.mddapi.service.ITopicService;
import com.openclassrooms.mddapi.service.IUserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", uses = {IUserService.class, ITopicService.class, ICommentService.class, CommentMapper.class }, imports = {Arrays.class, Collectors.class, Post.class, User.class, Topic.class, Collections.class, Optional.class, TopicDto.class, UserDto.class})
public abstract class PostMapper implements EntityMapper<PostDto, Post> {

    @Autowired
    IUserService userService;
    @Autowired
    ITopicService topicService;

    @Autowired
    ICommentService commentService;

    @Autowired
    CommentMapper commentMapper;

    @Mappings({
            @Mapping(target = "topic", expression = "java(postDto.getTopicDto() != null ? new Topic(postDto.getTopicDto().getId(), postDto.getTopicDto().getTitle(), postDto.getTopicDto().getDescription(), null) : null)"),
            @Mapping(target = "user", expression = "java(postDto.getUserDto() != null ? new User(postDto.getUserDto().getId(), postDto.getUserDto().getUsername(), postDto.getUserDto().getEmail(), null) : null)"),
    })
    public abstract Post toEntity(PostDto postDto);


    @Mappings({
            @Mapping(target = "topicDto" ,expression = "java(post.getTopic() != null ? new TopicDto(post.getTopic().getId(), post.getTopic().getTitle(), post.getContent(), null) : null)"),
            @Mapping(target = "userDto" ,expression = "java(post.getUser() != null ? new UserDto(post.getUser().getId(), post.getUser().getUsername(), post.getUser().getEmail(), null) : null)"),
            @Mapping(target = "comments", expression = "java(this.commentMapper.toDto(this.commentService.getCommentsByPost(Long.valueOf(post.getId()))))")
    })
    public abstract PostDto toDto(Post post);
}