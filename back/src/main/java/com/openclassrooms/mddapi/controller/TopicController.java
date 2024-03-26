package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.service.ITopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/topic")
public class TopicController {
	
	private ITopicService topicService;
	
	public TopicController(ITopicService topicService) {
		this.topicService = topicService;		
	}


	@GetMapping
	public ResponseEntity<?> getTopics() {
		List<Topic> topics = topicService.getTopics();

		List<TopicDto> topicsDto =  topics.stream().map(topic -> {
			return new TopicDto(topic.getId(), topic.getTitle(), topic.getDescription());
		}).toList();
		return ResponseEntity.ok().body(topicsDto);
	}
}
