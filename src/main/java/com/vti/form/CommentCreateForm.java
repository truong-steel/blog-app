package com.vti.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CommentCreateForm {
    @NotBlank(message="The author must be not blank.")
    @Length(max =255, message = "The author only has a maximum of 255 charactes")
    private String name;

    @Email(message ="Email must constain '@' character.")
    @NotBlank(message="Email must be not blank.")
    @Length(max =255, message = "Email only has a maximum of 255 charactes")
    private String email;

    @NotBlank(message="Comment field must be not blank.")
    @Length(max =255, message = "Comment field only has a maximum of 255 charactes")
    private String body;
}
