package com.ra.md4projectapi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataError <T>{
    private  T messages;
    private HttpStatus status;
}
