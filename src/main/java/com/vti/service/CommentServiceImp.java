package com.vti.service;

import com.vti.dto.CommentDto;
import com.vti.dto.PostDto;
import com.vti.entity.Comment;
import com.vti.form.CommentCreateForm;
import com.vti.form.CommentFilterForm;
import com.vti.form.CommentUpdateForm;
import com.vti.repository.CommentRepository;
import com.vti.repository.PostRepository;
import com.vti.specification.CommentSpecification;
import com.vti.specification.PostSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
@Autowired
    public CommentServiceImp(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto create(Long postId, CommentCreateForm form) {
        var post = postRepository.findById(postId).orElse(null);
        var comment = modelMapper.map(form , Comment.class);
        comment.setPost(post);
        var savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public Page<CommentDto> findAll(CommentFilterForm form ,Pageable pageable) {
        var spec = CommentSpecification.buildSpec(form);
    return commentRepository.findAll(spec,pageable)
                .map(comment -> modelMapper.map(comment, CommentDto.class).withSelfRel());
    }

    @Override
    public Page<CommentDto> findByPostId(Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId,pageable)
                .map(comment -> modelMapper.map(comment,CommentDto.class).withSelfRel());
    }

    @Override
    public CommentDto findById(Long id) {
        return commentRepository.findById(id)
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .orElse(null);
    }

    @Override
    public CommentDto update(Long id, CommentUpdateForm form) {
        var comment = commentRepository.findById(id).orElse(null);
        modelMapper.map(form, comment);
        var savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void delete(Long id) {
    commentRepository.deleteById(id);
    }
}
