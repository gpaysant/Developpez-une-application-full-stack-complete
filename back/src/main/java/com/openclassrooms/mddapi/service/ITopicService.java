package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.model.Topic;

import java.util.List;

public interface ITopicService {

	Topic findById(Long id);

	Topic addUserOnTopic(Long id, String user);

	Topic removeUserOnTopic(Long id, String user);

    List<TopicDto> findTopicsOfSubscriber(String email);

	List<TopicDto> getAllTopics();
}
