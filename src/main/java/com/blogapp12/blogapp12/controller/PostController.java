package com.blogapp12.blogapp12.controller;

import com.blogapp12.blogapp12.payload.PostDto;
import com.blogapp12.blogapp12.payload.PostResponse;
import com.blogapp12.blogapp12.service.PostService;
import com.blogapp12.blogapp12.utils.AppConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto)
    {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    @GetMapping
    public PostResponse listAllPost
            (@RequestParam(value="pageNo",defaultValue = AppConstant.DEFAULT_PAGE_NO,required = false)int pageNo,
             @RequestParam(value="pageSize",defaultValue = AppConstant.DEFAULT_PAGE_SIZE,required= false)int pageSize,
             @RequestParam(value = "sortBy",defaultValue = AppConstant.DEFAULT_SORT_BY,required = false)String sortBy,
             @RequestParam(value = "sortDir",defaultValue =AppConstant.DEFAULT_SORT_DIR ,required = false)String sortDir)

    {
        return postService.listAllPost(pageNo,pageSize,sortBy,sortDir);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(postService.findById(id),HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> upDateById(@RequestBody PostDto postDto,@PathVariable(name="id") Long id){
        return new ResponseEntity<>(postService.updateById(postDto,id),HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name="id") Long id){
        return new ResponseEntity<>("Delete post Successfully",HttpStatus.OK);
    }
}
