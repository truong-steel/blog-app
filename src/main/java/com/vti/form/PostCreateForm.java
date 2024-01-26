package com.vti.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PostCreateForm {
    @NotBlank(message="{post.title.NotBlank.message}")
    @Length(max = 255 ,message = "{post.title.Length.message}")
    private String title;

    @NotBlank(message="The post description must not be blank.")
    @Length(max = 255 ,message = "The post title has a maximum of 255 characters.")
    private String description;

    @NotBlank(message="The post content must not be blank.")
    @Length(max = 255 ,message = "The post title has a maximum of 255 characters.")
    private String content;
}
