package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopicDto {

	private Long id;

	private String title;

	private String description;
	private List<UserDto> usersDto;

}
