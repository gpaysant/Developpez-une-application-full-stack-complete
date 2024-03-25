package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class TopicDto {

	private Long id;

	private String title;

	private String description;

	public TopicDto(Long id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}
}
