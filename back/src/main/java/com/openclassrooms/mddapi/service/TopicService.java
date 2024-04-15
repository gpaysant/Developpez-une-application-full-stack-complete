package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.exceptions.NotFoundException;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

@Service
public class TopicService implements ITopicService {

	private TopicRepository topicRepository;
	private UserService userService;
	
	public TopicService(TopicRepository topicRepository,
						UserService userService) {
		this.topicRepository = topicRepository;
		this.userService = userService;
	}

	public List<Topic> getTopics() {
		return topicRepository.findAll();
	}

	@Override
	public Topic findById(Long id) {
		return this.topicRepository.findById(id).orElse(null);
	}

	@Override
	public Topic addUserOnTopic(Long id, String email) {
		User user = this.userService.getUserByEmail(email);
		return this.topicRepository.findById(id).map(
				topic -> {
					topic.addUser(user);
					return this.topicRepository.save(topic);
                }).orElseThrow(() -> new NotFoundException("Topic doesn't exist"));
    }

	@Override
	public Topic removeUserOnTopic(Long id, String email) {
		User user = this.userService.getUserByEmail(email);
		return this.topicRepository.findById(id).map(
				topic -> {
					topic.removeUser(user);
					return this.topicRepository.save(topic);
				}).orElseThrow(() -> new NotFoundException("Topic doesn't exist"));
	}

	@Override
	public List<TopicDto> findTopicsOfSubscriber(String email) {
		User user = this.userService.getUserByEmail(email);
		List<Topic> topics = this.getTopics().stream().filter(
				topic -> topic.getUsers().contains(user)
		).toList();

		return  topics.stream().map(topic -> new TopicDto(topic.getId(), topic.getTitle(), topic.getDescription(), null)).toList();
	}

	@Override
	public List<TopicDto> getAllTopics() {
		return  this.getTopics().stream().map(topic -> new TopicDto(topic.getId(), topic.getTitle(), topic.getDescription(), null)).toList();
	}
}
