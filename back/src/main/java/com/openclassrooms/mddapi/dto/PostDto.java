package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {

    private Long id;
    @NotNull
    private String title;
    @CreatedDate
    private LocalDateTime dateCreation;
    @NotNull
    private String content;
    private TopicDto topicDto;
    private UserDto userDto;
    private List<CommentDto> comments;
}
