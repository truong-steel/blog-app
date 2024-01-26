package com.vti.service;

import com.vti.dto.PostDto;
import com.vti.entity.Post;
import com.vti.form.PostCreateForm;
import com.vti.form.PostFilterForm;
import com.vti.form.PostUpdateForm;
import com.vti.repository.PostRepository;
import com.vti.specification.PostSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    @Override
    public Page<PostDto> findAll(PostFilterForm form ,Pageable pageable){
        var spec = PostSpecification.buildSpec(form);
        return postRepository.findAll(spec ,pageable)
                .map(post -> modelMapper.map(post,PostDto.class).withSelfRel());
    }

    @Override
    public PostDto findById(Long id) {
      return postRepository.findById(id)
              .map(post -> modelMapper.map(post, PostDto.class).withSelfRel())
              .orElse(null);
    }

    @Override
    public PostDto create(PostCreateForm form) {
        var post = modelMapper.map(form, Post.class);
        var savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);

    }

    @Override
    public PostDto update(Long id, PostUpdateForm form) {
        var post = postRepository.findById(id).orElse(null);
        modelMapper.map(form, post);
        var savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Autowired

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper)
    {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


}
