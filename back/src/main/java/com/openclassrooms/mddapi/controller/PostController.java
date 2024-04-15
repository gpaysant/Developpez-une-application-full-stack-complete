package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.PostCreationRequestDto;
import com.openclassrooms.mddapi.dto.PostSimpleDto;
import com.openclassrooms.mddapi.service.IPostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/post")
public class PostController {

    private IPostService postService;

    public PostController(IPostService postService) { //
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") String id) {
        try {
            PostSimpleDto postSimpleDto = this.postService.getPost(id);

            return ResponseEntity.ok().body(postSimpleDto);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addPost(Principal authentication, @Valid @RequestBody PostCreationRequestDto postCreationDto, BindingResult bindingResult) {
        this.postService.addPost(authentication.getName(), postCreationDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/my")
    public ResponseEntity<?> getPosts(Principal authentication) {
        List<PostSimpleDto> allPostSimpleDto = this.postService.getPostsOf(authentication.getName());

        return ResponseEntity.ok().body(allPostSimpleDto);
    }

}
