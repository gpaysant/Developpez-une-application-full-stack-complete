package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;

public interface ITopicService {

	List<Topic> getTopics();

	Topic findById(Long id);

	Topic addUserOnTopic(Long id, User user);

	Topic removeUserOnTopic(Long id, User user);
}
