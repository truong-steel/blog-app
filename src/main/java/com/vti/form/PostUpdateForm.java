package com.vti.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PostUpdateForm {
    @NotBlank(message="The title must not be blank.")
    @Length(max = 255 ,message = "The post title has a maximum of 255 characters.")
    private String title;

    @NotBlank(message="The post description must not be blank.")
    @Length(max = 255 ,message = "The post title has a maximum of 255 characters.")
    private String description;

    @NotBlank(message="The post content must not be blank.")
    @Length(max = 255 ,message = "The post title has a maximum of 255 characters.")
    private String content;
}
