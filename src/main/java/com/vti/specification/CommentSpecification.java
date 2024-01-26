package com.vti.specification;

import com.vti.entity.Comment;
import com.vti.entity.Post;
import com.vti.form.CommentFilterForm;
import com.vti.form.PostFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CommentSpecification {
    public static Specification<Comment> buildSpec(CommentFilterForm form){
        return form == null ? null : new Specification<Comment>() {
            @Override
            public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<>();
                String search = form.getSearch();
                if (StringUtils.hasText(search)){
                    String pattern = "%" + search.trim() + "%";
                    Predicate hasTitleLike = builder.like(root.get("body"),pattern);
                    predicates.add(hasTitleLike);
                }
                var minCreatedDate = form.getMinCreatedDate();
                if (minCreatedDate != null){
                    var minCreatedAt = LocalDateTime.of(minCreatedDate, LocalTime.MIN);
                    var predicate = builder.greaterThanOrEqualTo(root.get("createdAt").as(LocalDateTime.class),
                            minCreatedAt);
                    predicates.add(predicate);
                }

                var postId = form.getPostId();
                if(postId != null){
                    var predicate = builder.equal(root.get("post").get("id"),postId);
                    predicates.add(predicate);
                }

                var maxCreatedDate = form.getMaxCreatedDate();
                if (maxCreatedDate != null){
                    var maxCreatedAt = LocalDateTime.of(maxCreatedDate, LocalTime.MAX);
                    var predicate = builder.lessThanOrEqualTo(root.get("createdAt").as(LocalDateTime.class),
                            maxCreatedAt);
                    predicates.add(predicate);
                }
                return builder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
