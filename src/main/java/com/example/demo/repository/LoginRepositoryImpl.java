package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * ログイン画面_DB処理_実装
 * @author 宮本
 */
@Repository
public class LoginRepositoryImpl implements LoginRepository {

	/*****フィールド*****/
	/**
	 * DI用フィールド_インフラ層処理
	 */
	private final JdbcTemplate jdbcTemplate;

	/**
	 * 戻り値（ログイン結果）
	 */
	private String loginPassword;

	/**
	 * DI用コンストラクタ
	 * @param jdbcTemplate jdbcオブジェクト
	 */
	//	@Autowired
	public LoginRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.loginPassword = null;
	}

	/*****メソッド*****/
	/**
	 * ログイン処理_実装
	 * @return 実行結果 boolean
	 */
	@Override
	public String getPassword(String username) {
		//		System.out.println("RepositoryStart");
		String sql = "SELECT password FROM users WHERE username=?";

		try {
			this.loginPassword = jdbcTemplate.queryForObject(sql, String.class, username);
		} catch (Exception e) {
			System.out.println("username is not found : " + e.getMessage());
			return null;
		}
		//		System.out.println("GetPassword="+this.loginPassword);
		return this.loginPassword;
	}

}
