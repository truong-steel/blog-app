package com.vti.controller;

import com.vti.dto.CommentDto;
import com.vti.form.CommentCreateForm;
import com.vti.form.CommentFilterForm;
import com.vti.form.CommentUpdateForm;
import com.vti.service.CommentService;
import com.vti.validation.PostIdExists;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class CommentController {
    public final CommentService commentService;
@Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/api/v1/posts/{postId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(@PostIdExists @PathVariable("postId") Long postId , @RequestBody @Valid CommentCreateForm form){
    return commentService.create(postId,form);
    }
//    @RequestBody chỉ dùng cho phương thức tạo mới
    @GetMapping("/api/v1/comments")
    public Page<CommentDto> findAll(CommentFilterForm form ,Pageable pageable){
    return commentService.findAll(form ,pageable);
    }
    @GetMapping("/api/v1/posts/{postId}/comments")
    public Page<CommentDto> findByPostId(@PathVariable("postId") Long postId, Pageable pageable){
    return commentService.findByPostId(postId,pageable);
    }
    @GetMapping("/api/v1/comments/{id}")
    public CommentDto findById(@PathVariable("id") Long id){
    return commentService.findById(id);
    }

    @PutMapping("/api/v1/comments/{id}")
    public CommentDto update(@PathVariable("id") Long id , @RequestBody @Valid CommentUpdateForm form){
    return commentService.update(id,form);
    }
    @DeleteMapping("/api/v1/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id){
    commentService.delete(id);
    }
}
