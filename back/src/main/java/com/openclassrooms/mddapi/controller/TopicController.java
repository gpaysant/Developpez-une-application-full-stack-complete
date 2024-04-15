package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.service.ITopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
		List<TopicDto> topicsDto = this.topicService.getAllTopics();

		return ResponseEntity.ok().body(topicsDto);
	}

	@PostMapping("/subscribe/{id}")
	public ResponseEntity<?> subscribeTopic(@PathVariable("id") String id, Principal authentication) {
		try {
			this.topicService.addUserOnTopic(Long.valueOf(id), authentication.getName());

			return ResponseEntity.ok().build();
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/unsubscribe/{id}")
	public ResponseEntity<?> unsubscribeTopic(@PathVariable("id") String id, Principal authentication) {
		try {
			this.topicService.removeUserOnTopic(Long.valueOf(id), authentication.getName());

			return ResponseEntity.ok().build();
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/subscribed")
	public ResponseEntity<?> getTopics(Principal authentication) {
		List<TopicDto> topicsDto = topicService.findTopicsOfSubscriber(authentication.getName());

		return ResponseEntity.ok().body(topicsDto);
	}

}
