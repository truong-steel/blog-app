package com.vti.service;

import com.vti.dto.CommentDto;
import com.vti.form.CommentCreateForm;
import com.vti.form.CommentFilterForm;
import com.vti.form.CommentUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    CommentDto create(Long postId, CommentCreateForm form);
    Page<CommentDto> findAll(CommentFilterForm form ,Pageable pageable);
    Page<CommentDto> findByPostId(Long postId,Pageable pageable);
    CommentDto findById(Long id);
    CommentDto update(Long id, CommentUpdateForm form);
    void delete(Long id);
}
