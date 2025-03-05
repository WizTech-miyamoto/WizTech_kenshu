package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.EntityUser;
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
		this.loginResult = true;
	}

	/*****メソッド*****/
	/**
	 *　ログイン処理_実装
	 */
	@Override
	public boolean login(EntityUser user) {
		this.loginResult = repository.login(user);
		return this.loginResult;
	}

}
