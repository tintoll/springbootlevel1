package io.tintoll.springbootlevel1.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class Member {
    @Length(min = 2)
    @Length(max = 20)
    @Email
    @NotBlank
    private String username;

    @Length(min = 2)
    private String password;
}
