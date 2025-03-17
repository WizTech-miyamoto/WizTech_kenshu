package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * ホーム録画面_ユーザーリストフォーム
 * @author 宮本
 */
@Data
public class UserResistForm {
	/*****フィールド*****/
	/**
	 * ユーザーID
	 */
	@NotBlank
	private String userid;

	/**
	 * ユーザー名
	 */
	@NotBlank
	@Size(min = 1, max = 20)
	private String username;

	/**
	 * パスワード
	 */
	@NotBlank
	@Pattern(regexp = "[a-zA-Z0-9]*")
	@Size(min = 1, max = 8)
	private String password;

	/**
	 * 権限
	 */
	@NotBlank
	@Pattern(regexp = "[A-Z]*")
	private String role;
}
