package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
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
 * Custom CommentMapper to create expected mapping
 */
@Component
@Mapper(componentModel = "spring", imports = {Arrays.class, Collectors.class, Post.class, User.class, Comment.class, Collections.class, Optional.class, PostDto.class, UserDto.class})
public interface CommentMapper extends EntityMapper<CommentDto, Comment> {

    /**
     * This method create a mapper between Post and PostDto.
     * This custom method is created to avoid cyclic relation between post, topic and user.
     * @param comment
     * @return CommentDto
     */
    @Mappings({
            @Mapping(target = "userDto" ,expression = "java(comment.getUser() != null ? new UserDto(comment.getUser().getId(), comment.getUser().getUsername(), comment.getUser().getEmail(), null) : null)"),
            @Mapping(target = "postDto", expression = "java(comment.getPost() != null ? new PostDto(comment.getPost().getId(), comment.getPost().getTitle(), comment.getPost().getDateCreation(), comment.getPost().getContent(), null, null) : null)")
    })
    public abstract CommentDto toDto(Comment comment);
}