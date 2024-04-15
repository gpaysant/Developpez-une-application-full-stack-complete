package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentCreationDto;
import com.openclassrooms.mddapi.dto.CommentSendingDto;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {
    private CommentRepository commentRepository;
    private CommentMapper commentMapper;
    private IUserService userService;
    private IPostService postService;


    public CommentService(CommentRepository commentRepository,
                          IUserService userService,
                          IPostService postService,
                          CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public List<CommentSendingDto> getCommentsByPost(Long idPost) {
        List<Comment> comments = getComments(idPost);
        return this.commentMapper.toDto(comments).stream().map(
                commentDto -> new CommentSendingDto(
                        commentDto.getText(),
                        commentDto.getUserDto() != null ? commentDto.getUserDto().getUsername() : null)
        ).toList();
    }

    @Override
    public List<Comment> getComments(Long idPost) {
        return this.commentRepository.findByPost_id(idPost);
    }

    @Override
    public void addComment(String username, CommentCreationDto commentCreationDto) {
        User user = this.userService.getUserByEmail(username);
        Post post = this.postService.findById(commentCreationDto.getPostId());
        Comment comment = Comment.builder()
                .text(commentCreationDto.getText())
                .post(post)
                .user(user)
                .build();
        this.commentRepository.save(comment);
    }

}