package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LoginRepository;

@Service
public class LoginUserDetailService implements UserDetailsService {

	/*****フィールド*****/
	/**
	 * DI用フィールド_インフラ層処理
	 */
	@Autowired
	private final LoginRepository loginRepository;

	/**
	 * DI用コンストラクタ
	 * @param repository インフラ層処理
	 */
	//		@Autowired
	public LoginUserDetailService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("LoginUserDeatailServiceStart");
		System.out.println("username:" + username);
		String loginPassword = this.loginRepository.getPassword(username);
		if (loginPassword == null) {
			throw new UsernameNotFoundException("入力されたユーザーIDは存在しません：" + username);
		}
		System.out.println("loginPassword:" + loginPassword);
		return new LoginUserDetail(username, loginPassword,"ADMIN");
	}

}
