package com.example.demo.entity;

import org.springframework.stereotype.Component;

import com.example.demo.form.LoginForm;
import com.example.demo.form.UserResistForm;

import lombok.Data;

/**
 * ユーザーテーブルEntity
 */
@Data
@Component
public class UserEntity {

	/*****フィールド*****/
	/**
	 * ユーザーID
	 */
	private String userid;

	/**
	 * ユーザー名
	 */
	private String username;

	/**
	 * パスワード
	 */
	private String password;

	/**
	 * 権限
	 */
	private String role;

	/**
	 * 論理削除フラグ
	 */
	private boolean logicalDeleteFlg;

	public void setEntityLogin(LoginForm form) {
		this.userid = form.getUsername();
		this.password = form.getPassword();
		return;
	}

	public void setEntityRegist(UserResistForm form) {
		this.userid = form.getUserid();
		this.username = form.getUsername();
		this.password = form.getPassword();
		this.role = form.getRole();
		this.logicalDeleteFlg = false;
		return;
	}

}
