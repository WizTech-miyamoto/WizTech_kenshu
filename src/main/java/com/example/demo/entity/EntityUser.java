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
public class EntityUser {

	/*****フィールド*****/
	/**
	 * ユーザーID
	 */
	private String userId;

	/**
	 * ユーザー名
	 */
	private String userName;

	/**
	 * パスワード
	 */
	private String password;

	/**
	 * 権限
	 */
	private String authority;

	/**
	 * 論理削除フラグ
	 */
	private boolean logicalDeleteFlg;

	public void setEntityLogin(LoginForm form) {
		this.userId = form.getUserId();
		this.password = form.getPassword();
		return;
	}

	public void setEntityRegist(UserResistForm form) {
		this.userId = form.getUserId();
		this.userName = form.getUserName();
		this.password = form.getPassword();
		this.authority = form.getAuthority();
		this.logicalDeleteFlg = false;
		return;
	}

}
