package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.PostCreationRequest;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.exceptions.NotFoundException;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService implements IPostService {

	private PostRepository postRepository;
	private ITopicService topicService;
	private IUserService userService;
	private TopicMapper topicMapper;
	private UserMapper userMapper;
	
	public PostService(PostRepository postRepository, ITopicService topicService, IUserService userService,
					   TopicMapper topicMapper, UserMapper userMapper) {
		this.postRepository = postRepository;
		this.topicService = topicService;
		this.userService = userService;
		this.topicMapper = topicMapper;
		this.userMapper = userMapper;
	}

	@Override
	public Post findById(Long id) {
		return this.postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post doesn't exist"));
	}

	@Override
	public PostDto convertToDto(PostCreationRequest postCreationRequest) {
		PostDto postDto = new PostDto();
		postDto.setTitle(postCreationRequest.getTitle());
		postDto.setContent(postCreationRequest.getContent());
		Topic topic = this.topicService.findById(postCreationRequest.getTopicId());
		postDto.setTopicDto(this.topicMapper.toDto(topic));
		User user = this.userService.findById(postCreationRequest.getUserId());
		postDto.setUserDto(this.userMapper.toDto(user));
		return postDto;
	}

	@Override
	public void addPost(Post post) {
		post.setDateCreation(LocalDateTime.now());
		this.postRepository.save(post);
	}

	@Override
	public List<Post> findByUser(User user) {
		return this.postRepository.findByUser(user);
	}

}
