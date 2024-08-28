package io.dorum.dto;

import lombok.Data;

@Data
public class PostDTO {
//    @JsonIgnore
    String userId;
    String id;
    String title;
    String body;
}
