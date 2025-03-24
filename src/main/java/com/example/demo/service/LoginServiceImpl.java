package com.example.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LoginRepository;

/**
 * ログイン画面_業務処理_実装
 * @author 宮本
 */
@Service
public class LoginServiceImpl implements LoginService {

	/*****フィールド*****/
	/**
	 * DI用フィールド_インフラ層処理
	 */
	private final LoginRepository repository;

	/**
	 * 戻り値(ログイン結果)
	 */
	private boolean loginResult;

	/*****コンストラクタ*****/

	/**
	 * DI用コンストラクタ
	 * @param repository インフラ層処理
	 */
	//	@Autowired
	public LoginServiceImpl(LoginRepository repository) {
		this.repository = repository;
		this.loginResult = false;
	}

	/*****メソッド*****/
	/**
	 *　ログイン処理_実装
	 * @return ログイン結果 boolean
	 */

	public boolean login(String username, String password) {
		this.loginResult = false;
		System.out.println("LoginServiceStart");
		String inPass = new BCryptPasswordEncoder().encode(password);
		String dbPass = repository.getPassword(username);
		System.out.println("inPass:"+inPass);
		System.out.println("dbPass:"+dbPass);
		if (inPass.equals(dbPass)) {
			this.loginResult = true;
		}
		return this.loginResult;
	}

}
