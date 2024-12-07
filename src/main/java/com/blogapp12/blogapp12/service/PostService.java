package com.blogapp12.blogapp12.service;

import com.blogapp12.blogapp12.payload.PostDto;
import com.blogapp12.blogapp12.payload.PostResponse;

import java.util.List;

public interface PostService {

   PostDto createPost(PostDto postDto);
   PostResponse listAllPost(int pageNo, int pageSize,String sortBy,String sortDir);
   PostDto findById(long id);
   void deleteById(long id);
   PostDto updateById(PostDto posDto,long id);


}
