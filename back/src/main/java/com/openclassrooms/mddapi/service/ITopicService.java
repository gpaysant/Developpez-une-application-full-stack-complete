package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;

import java.util.List;

public interface ITopicService {

	List<Topic> getTopics();

	Topic findById(Long id);

	Topic addUserOnTopic(Long id, User user);

	Topic removeUserOnTopic(Long id, User user);
}
