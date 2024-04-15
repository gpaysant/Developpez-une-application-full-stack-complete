package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentCreationDto;
import com.openclassrooms.mddapi.dto.CommentSendingDto;
import com.openclassrooms.mddapi.service.ICommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/comment")
public class CommentController {

    private final ICommentService commentService;


    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/add")
    public ResponseEntity<?> addComment(Principal authentication, @Valid @RequestBody CommentCreationDto commentCreationDto, BindingResult bindingResult) {
        try {
            this.commentService.addComment(authentication.getName(), commentCreationDto);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComment(@PathVariable("id") String id) {
        try {
            List<CommentSendingDto> commentSendingDtoList = this.commentService.getCommentsByPost(Long.valueOf(id));
            return ResponseEntity.ok().body(commentSendingDtoList);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
