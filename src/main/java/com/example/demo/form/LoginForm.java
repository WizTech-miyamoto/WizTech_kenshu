package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * ログイン画面_入力フォーム
 * @author 宮本
 */
@Data
public class LoginForm {

	/*****フィールド*****/
	/**
	 * ユーザー名
	 */
	@NotBlank
	@Size(min = 1, max = 3)
	private String userId;

	/**
	 * パスワード
	 */
	@NotBlank
	@Pattern(regexp = "[a-zA-Z0-9]*")
	@Size(min = 1, max = 8)
	private String password;
}
