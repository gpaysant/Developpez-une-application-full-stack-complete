package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.PostCreationRequest;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.IPostService;
import com.openclassrooms.mddapi.service.IUserService;
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
    private IUserService userService;
    private final PostMapper postMapper;

    public PostController(IPostService postService, PostMapper postMapper, IUserService userService) { //
        this.postService = postService;
        this.postMapper = postMapper;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") String id) {
        try {
            Post post = this.postService.findById(Long.valueOf(id));
            System.out.println(post);
            Topic topic = post.getTopic() != null ? new Topic(post.getTopic().getId(), post.getTopic().getTitle(), post.getContent(), null) : null;


            return ResponseEntity.ok().body(this.postMapper.toDto(post));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addPost(@Valid @RequestBody PostCreationRequest postCreationDto, BindingResult bindingResult) {
        PostDto postDto = this.postService.convertToDto(postCreationDto);
        Post post = this.postMapper.toEntity(postDto);
        this.postService.addPost(post);
        Topic topic = postDto.getTopicDto() != null ? new Topic(postDto.getTopicDto().getId(), postDto.getTopicDto().getTitle(), postDto.getTopicDto().getDescription(), null) : null;
        User user = postDto.getUserDto() != null ? new User(postDto.getUserDto().getId(), postDto.getUserDto().getUsername(), postDto.getUserDto().getEmail(), null) : null;
        return ResponseEntity.ok("article cree");
    }

    @GetMapping("/my")
    public ResponseEntity<?> getPosts(Principal authentication) {
        User user = this.userService.getUserByEmail(authentication.getName());
        List<Post> posts = this.postService.findByUser(user);

        return ResponseEntity.ok().body(this.postMapper.toDto(posts));

    }


}
