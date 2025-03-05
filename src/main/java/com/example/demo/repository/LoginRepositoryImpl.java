package com.example.demo.repository;

import java.util.List;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EntityUser;

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
	private boolean loginResult;
	
	/**
	 * DI用コンストラクタ
	 * @param jdbcTemplate jdbcオブジェクト
	 */
	//	@Autowired
	public LoginRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.loginResult = true;
	}

	/*****メソッド*****/
	/**
	 * ログイン処理_実装
	 * @return 実行結果
	 */
	@Override
	public boolean login(EntityUser user) {
		String sql = "SELECT * FROM user_table WHERE user_id=? AND password=?";
//		String encodePass = new BCryptPasswordEncoder().encode(user.getPassword());
//		System.out.println(encodePass);
		
		List<EntityUser> result = jdbcTemplate.query(sql, new DataClassRowMapper<>(EntityUser.class), user.getUserId(),
				user.getPassword());
		this.loginResult = true;
		if (result.isEmpty()) {
			this.loginResult = false;
		}
		return this.loginResult;
	}

}
