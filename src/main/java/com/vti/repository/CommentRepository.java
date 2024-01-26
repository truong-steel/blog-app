package com.vti.repository;

import com.vti.dto.CommentDto;
import com.vti.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
    //findBy , deleteBy , countBy , existsBy
    // Dùng ano @Query để thay đổi dữ liệu bằng câu lệnh của SQL/HQL
    // Tại class Impl cần thêm ano @Transaction cùng ano @Service
    Page<Comment> findByPostId(Long postId, Pageable pageable);
}
