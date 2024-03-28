package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentCreationDto;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.ICommentService;
import com.openclassrooms.mddapi.service.IPostService;
import com.openclassrooms.mddapi.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/comment")
public class CommentController {

    private ICommentService commentService;
    private final CommentMapper commentMapper;
    private final IUserService userService;
    private final IPostService postService;

    public CommentController(ICommentService commentService, CommentMapper commentMapper, IUserService userService, IPostService postService) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.userService = userService;
        this.postService = postService;
    }


    @PostMapping("/add/")
    public ResponseEntity<?> addComment(Principal authentication, @Valid @RequestBody CommentCreationDto commentCreationDto, BindingResult bindingResult) {
        try {
            User user = this.userService.getUserByEmail(authentication.getName());
            Post post = this.postService.findById(commentCreationDto.getPostId());
            Comment comment = Comment.builder()
                .text(commentCreationDto.getText())
                .post(post)
                .user(user)
                .build();
            this.commentService.addComment(comment);

            return ResponseEntity.ok().body("Commentaire ajoute avec succes");
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
