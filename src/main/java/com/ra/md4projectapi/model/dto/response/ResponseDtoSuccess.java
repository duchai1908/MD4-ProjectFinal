package com.ra.md4projectapi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDtoSuccess <T>{
    private T data;
    private HttpStatus status;
}
