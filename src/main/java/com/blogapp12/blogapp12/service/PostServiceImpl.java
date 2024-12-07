package com.blogapp12.blogapp12.service;

import com.blogapp12.blogapp12.entity.Post;
import com.blogapp12.blogapp12.exception.ResourceNotFoundException;
import com.blogapp12.blogapp12.payload.PostDto;
import com.blogapp12.blogapp12.payload.PostResponse;
import com.blogapp12.blogapp12.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // Map to Entity
        Post post=maptoEntity(postDto);
        Post newPost=postRepo.save(post);
        //Map to Dto
        PostDto postDto1=maptoDto(newPost);
        return postDto1;
    }
    public Post maptoEntity(PostDto postDto){
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
    public PostDto maptoDto(Post post){
        PostDto postDto=new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        return postDto;
    }

    @Override
    public PostResponse listAllPost(int pageNo, int pageSize,String sortBy,String sortDir) {
       Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():
               Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        Page<Post> page=postRepo.findAll(pageable);
        List<Post> posts=page.getContent();
        List<PostDto> content=posts.stream().map(x->maptoDto(x)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setPageNo(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalNoElement(page.getNumberOfElements());
        postResponse.setTotalPageSize(page.getTotalPages());
        postResponse.setLastPage(page.isLast());
        return postResponse;
    }

    @Override
    public PostDto findById(long id) {
        Post post=postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","id",id));
        return maptoDto(post);
    }

    @Override
    public void deleteById(long id) {
        Post post=postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","id",id));
        postRepo.delete(post);
    }

    @Override
    public PostDto updateById(PostDto postDto, long id) {
        Post post=postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post newPost=postRepo.save(post);
        return maptoDto(newPost);

    }
}
