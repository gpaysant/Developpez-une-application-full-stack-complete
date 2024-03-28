package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.ITopicService;
import com.openclassrooms.mddapi.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/topic")
public class TopicController {
	
	private ITopicService topicService;
	private IUserService userService;
	
	public TopicController(ITopicService topicService, IUserService userService) {
		this.topicService = topicService;
		this.userService = userService;
	}


	@GetMapping
	public ResponseEntity<?> getTopics() {
		List<Topic> topics = topicService.getTopics();

		List<TopicDto> topicsDto =  topics.stream().map(topic -> {
			return new TopicDto(topic.getId(), topic.getTitle(), topic.getDescription(), null);
		}).toList();
		return ResponseEntity.ok().body(topicsDto);
	}

	@PostMapping("/subscribe/{id}")
	public ResponseEntity<?> subscribeTopic(@PathVariable("id") String id, Principal authentication) {
		try {
			User user = this.userService.getUserByEmail(authentication.getName());
			this.topicService.addUserOnTopic(Long.valueOf(id), user);
			return ResponseEntity.ok("Abonné au topic");
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/unsubscribe/{id}")
	public ResponseEntity<?> unsubscribeTopic(@PathVariable("id") String id, Principal authentication) {
		try {
			User user = this.userService.getUserByEmail(authentication.getName());
			this.topicService.removeUserOnTopic(Long.valueOf(id), user);

			return ResponseEntity.ok("Désabonné du topic");
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
