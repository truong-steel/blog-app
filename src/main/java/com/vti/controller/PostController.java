package com.vti.controller;

import com.vti.dto.PostDto;
import com.vti.form.PostCreateForm;
import com.vti.form.PostFilterForm;
import com.vti.form.PostUpdateForm;
import com.vti.service.PostService;
import com.vti.validation.PostIdExists;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class PostController {
    private final PostService postService;

@Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

@GetMapping("/api/v1/posts")
public Page<PostDto> findAll(PostFilterForm form, Pageable pageable){
    return postService.findAll(form ,pageable);
}

@GetMapping("/api/v1/posts/{id}")
public PostDto findById(@PathVariable("id") Long id){
    return postService.findById(id);
}

@PostMapping("/api/v1/posts")
@ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody @Valid @PostIdExists PostCreateForm form ){
    return postService.create(form);
    }

@PutMapping("/api/v1/posts/{id}")
    public PostDto update(@PathVariable("id") @Valid Long id ,@RequestBody PostUpdateForm form){
    return postService.update(id , form);
    }
@DeleteMapping("/api/v1/posts/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id){
    postService.deleteById(id);
    }
}
