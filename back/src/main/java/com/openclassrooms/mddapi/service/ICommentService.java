package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentCreationDto;
import com.openclassrooms.mddapi.dto.CommentSendingDto;
import com.openclassrooms.mddapi.model.Comment;

import java.util.List;

public interface ICommentService {
    List<CommentSendingDto> getCommentsByPost(Long idPost);

    List<Comment> getComments(Long idPost);

    void addComment(String name, CommentCreationDto commentCreationDto);
}
