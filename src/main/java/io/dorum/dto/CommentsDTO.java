package io.dorum.dto;

import lombok.Data;

@Data
public class CommentsDTO {
   int postId;
   int id;
   String name;
   String email;
   String body;
}
