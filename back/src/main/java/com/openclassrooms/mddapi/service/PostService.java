package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.PostCreationRequestDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostSimpleDto;
import com.openclassrooms.mddapi.exceptions.NotFoundException;
import com.openclassrooms.mddapi.mapper.PostMapper;
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
	private PostMapper postMapper;
	
	public PostService(PostRepository postRepository, ITopicService topicService, IUserService userService,
					   TopicMapper topicMapper, UserMapper userMapper, PostMapper postMapper) {
		this.postRepository = postRepository;
		this.topicService = topicService;
		this.userService = userService;
		this.topicMapper = topicMapper;
		this.userMapper = userMapper;
		this.postMapper = postMapper;
	}

	@Override
	public Post findById(Long id) {
		return this.postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post doesn't exist"));
	}

	@Override
	public PostSimpleDto getPost(String idPost) {
		    Post post = this.findById(Long.valueOf(idPost));
            Topic topic = post.getTopic() != null ? new Topic(post.getTopic().getId(), post.getTopic().getTitle(), post.getContent(), null) : null;
            PostDto postDto = this.postMapper.toDto(post);

            return new PostSimpleDto(postDto.getId(),
                    postDto.getTitle(),
                    postDto.getDateCreation(),
                    postDto.getContent(),
                    postDto.getUserDto() != null ? postDto.getUserDto().getUsername() : null,
                    postDto.getTopicDto() != null ? postDto.getTopicDto().getTitle() : null);

	}

	@Override
	public PostDto convertToDto(String name, PostCreationRequestDto postCreationRequest) {
		PostDto postDto = new PostDto();
		postDto.setTitle(postCreationRequest.getTitle());
		postDto.setContent(postCreationRequest.getContent());
		Topic topic = this.topicService.findById(postCreationRequest.getTopicId());
		postDto.setTopicDto(this.topicMapper.toDto(topic));
		User user = this.userService.getUserByEmail(name);
		postDto.setUserDto(this.userMapper.toDto(user));
		return postDto;
	}

	@Override
	public void addPost(String email, PostCreationRequestDto postCreationRequestDto) {
		PostDto postDto = this.convertToDto(email, postCreationRequestDto);
		Post post = this.postMapper.toEntity(postDto);

		post.setDateCreation(LocalDateTime.now());
		this.postRepository.save(post);
	}

	@Override
	public List<Post> findByUser(User user) {
		return this.postRepository.findByUser(user);
	}

	@Override
	public List<PostSimpleDto> getPostsOf(String email) {
		User user = this.userService.getUserByEmail(email);
		List<Post> posts = this.findByUser(user);

		List<PostDto> allPostDto = this.postMapper.toDto(posts);
		return allPostDto.stream()
				.map(postDto -> new PostSimpleDto(postDto.getId(),
						postDto.getTitle(),
						postDto.getDateCreation(),
						postDto.getContent(),
						postDto.getUserDto() != null ? postDto.getUserDto().getUsername() : null,
						postDto.getTopicDto() != null ? postDto.getTopicDto().getTitle() : null)).toList();
	}
}
