package com.vti.service;

import com.vti.dto.PostDto;
import com.vti.entity.Post;
import com.vti.form.PostCreateForm;
import com.vti.form.PostFilterForm;
import com.vti.form.PostUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    Page<PostDto> findAll(PostFilterForm form, Pageable pageable);
    PostDto findById(Long id);
    PostDto create(PostCreateForm form);
    PostDto update(Long id, PostUpdateForm form);

    void deleteById(Long id);
}
