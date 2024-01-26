package com.vti.dto;

import com.vti.controller.PostController;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter

public class PostDto extends RepresentationModel<PostDto> {
    private Long id;
    private String title;
    private String description;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentDto> comments;

    public PostDto withSelfRel() {
        if (comments != null){
            for (CommentDto comment : comments) {
                comment.withSelfRel();
            }
        }

        var controller = methodOn(PostController.class);

        var dto = controller.findById(id);

        var link = linkTo(dto).withSelfRel();

        return add(link);

    }
}
