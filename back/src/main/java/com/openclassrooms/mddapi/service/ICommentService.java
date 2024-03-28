package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> getCommentsByPost(Long idPost);

    void addComment(Comment comment);
}
