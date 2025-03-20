package com.example.demo.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class LoginUserDetail implements UserDetails {

	/*****フィールド*****/
	/**
	 * DI用フィールド_ログイン情報
	 */
	private final String userid;
	private final String password;
	private final Collection<? extends GrantedAuthority> roles;

	/**
	 * DI用コンストラクタ
	 * @param user ユーザーテーブルEntity
	 */
	//	@Autowired
	public LoginUserDetail(String username, String password, String roles) {
		this.userid = username;
		this.password = password;
		this.roles = Arrays.stream(roles.split(","))
				.map(role -> new SimpleGrantedAuthority(role))
				.toList();
	}

	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return this.userid;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return this.roles;
	}

}
