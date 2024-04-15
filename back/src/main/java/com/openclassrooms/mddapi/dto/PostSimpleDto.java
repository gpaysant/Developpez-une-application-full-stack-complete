package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostSimpleDto {
    private Long id;
    @NotNull
    private String title;
    @CreatedDate
    private LocalDateTime dateCreation;
    @NotNull
    private String content;
    @NotNull
    private String nameCreator;
    @NotNull
    private String nameTopic;
}
