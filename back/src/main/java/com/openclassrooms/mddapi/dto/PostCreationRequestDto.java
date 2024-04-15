package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostCreationRequestDto {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Long topicId;
}