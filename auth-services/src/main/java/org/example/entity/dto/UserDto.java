package org.example.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
public class UserDto {
    String clientId;
    String clientSecret;
}
