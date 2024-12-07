package com.blogapp12.blogapp12.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
  private Long id;
  private String title;
  private String description;
  private String content;

}
