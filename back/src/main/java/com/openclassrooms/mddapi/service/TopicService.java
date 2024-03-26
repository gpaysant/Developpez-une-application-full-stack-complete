package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.exceptions.NotFoundException;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

@Service
public class TopicService implements ITopicService {

	private TopicRepository topicRepository;
	private final UserMapper userMapper;
	
	public TopicService(TopicRepository topicRepository, UserMapper userMapper) {
		this.topicRepository = topicRepository;
		this.userMapper = userMapper;
	}

	@Override
	public List<Topic> getTopics() {
		return topicRepository.findAll();
	}

	@Override
	public Topic findById(Long id) {
		return this.topicRepository.findById(id).orElse(null);
	}

	@Override
	public Topic addUserOnTopic(Long id, User user) {
		return this.topicRepository.findById(id).map(
				topic -> {
					topic.addUser(user);
					return this.topicRepository.save(topic);
                }).orElseThrow(() -> new NotFoundException("Topic doesn't exist"));
    }

	@Override
	public Topic removeUserOnTopic(Long id, User user) {
		return this.topicRepository.findById(id).map(
				topic -> {
					topic.removeUser(user);
					return this.topicRepository.save(topic);
				}).orElseThrow(() -> new NotFoundException("Topic doesn't exist"));
	}
}
