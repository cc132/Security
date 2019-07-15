package com.example.demo.validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@SuppressWarnings("all")
public class UserInfoDto {
	@Null(groups = {GroupA.class})
	@NotNull(groups = {GroupB.class},message = "{validation.common.not.null}")
	private Long id;

    @NotBlank(groups = {GroupB.class},message = "{validation.common.not.null}")
    @Length(groups = {GroupB.class},min=3, max=10)
    private String username;

    @NotBlank(groups = {GroupB.class})
    @Email(groups = {GroupB.class})
    private String email;

    @NotBlank(groups = {GroupB.class})
    @Pattern(groups = {GroupB.class},regexp="^((13[0-9])|(15[^4,\\D])|(18[0,3-9]))\\d{8}$", message="手机号格式不正确")
    private String phone;

    @Min(groups = {GroupB.class},value=18)
    @Max(groups = {GroupB.class},value = 200)
    private int age;

    @NotBlank(groups = {GroupB.class})
    @Length(groups = {GroupB.class},min=6, max=12, message="昵称长度为6到12位")
    private String nickname;
}
