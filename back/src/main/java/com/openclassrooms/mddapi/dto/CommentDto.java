package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {

    private Long id;
    @NotNull
    private String text;
    private PostDto postDto;
    private UserDto userDto;
}
