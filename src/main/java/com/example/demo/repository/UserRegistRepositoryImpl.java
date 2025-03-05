package com.example.demo.repository;

import java.util.List;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EntityUser;

/**
 * ログイン画面_DB処理_実装
 * @author 宮本
 */
@Repository
public class UserRegistRepositoryImpl implements UserRegistRepository {

	/*****フィールド*****/
	/**
	 * DI用フィールド_インフラ層処理
	 */
	private final JdbcTemplate jdbcTemplate;

	/**
	 * 戻り値（処理結果）
	 */
	private String nextUserId;

	/**
	 * 戻り値（ユーザーリスト）
	 */
	private List<EntityUser> userList;

	/*****コンストラクタ*****/

	/**
	 * DI用コンストラクタ
	 * @param jdbcTemplate jdbcオブジェクト
	 */
	//	@Autowired
	public UserRegistRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/*****メソッド*****/
	/**
	 * 登録処理_実装
	 * @return 実行結果
	 */
	@Override
	public boolean regist(EntityUser user) {
		String sql = "INSERT INTO user_table (user_Id,user_Name,password,authority,logical_delete_flg) VALUES(?,?,?,?,?)";
		this.jdbcTemplate.update(sql,user.getUserId(),user.getUserName(),user.getPassword(),user.getAuthority(),false);
		System.out.println("登録処理完了");
		return true;
	}

	/**
	 * 更新処理_実装
	 * @return 実行結果
	 */
	@Override
	public boolean update(EntityUser user) {
		String sql = "UPDATE user_table SET user_Name=?,password=?,authority=? WHERE user_id=?";
		this.jdbcTemplate.update(sql,user.getUserName(),user.getPassword(),user.getAuthority(),user.getUserId());
		System.out.println("更新処理完了");
		return true;
	}

	/**
	 * 削除処理_実装
	 * @return 実行結果
	 */
	@Override
	public boolean delete(EntityUser user) {
		String sql = "UPDATE user_table SET logical_delete_flg=true WHERE user_id=?";
		this.jdbcTemplate.update(sql,user.getUserId());
		System.out.println("削除処理完了");
		return true;
	}

	@Override
	public String getNextUseId() {
		String sql = "SELECT MAX(user_id) AS maxId FROM user_table";
		int maxUserId = this.jdbcTemplate.queryForObject(sql, int.class) + 1;
		this.nextUserId = String.format("%03d", maxUserId);
		return this.nextUserId;
	}

	@Override
	public List<EntityUser> getUserList() {
		this.userList = jdbcTemplate.query("SELECT * FROM user_table WHERE logical_delete_flg=0", new DataClassRowMapper<>(EntityUser.class));
		return this.userList;
	}

}
