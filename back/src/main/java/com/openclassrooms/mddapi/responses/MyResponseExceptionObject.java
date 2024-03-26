package com.openclassrooms.mddapi.responses;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class MyResponseExceptionObject {
    private String error;
}