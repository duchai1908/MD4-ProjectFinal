package com.ra.md4projectapi.model.dto.response;

import com.ra.md4projectapi.constants.EHttpStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseWrapper<T> {
    EHttpStatus ehttpStatus;
    int statusCode;
    T data;
}
