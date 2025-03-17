package com.example.demo.repository;

import java.util.List;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

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
	private List<UserEntity> userList;

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
	 * @return 実行結果 boolean
	 */
	@Override
	public boolean regist(UserEntity user) {
		String sql = "INSERT INTO users_table (userId,username,password,role,logical_delete_flg) VALUES(?,?,?,?,?)";
		this.jdbcTemplate.update(sql,user.getUserid(),user.getUsername(),user.getPassword(),user.getRole(),false);
		System.out.println("登録処理完了");
		return true;
	}

	/**
	 * 更新処理_実装
	 * @return 実行結果 boolean
	 */
	@Override
	public boolean update(UserEntity user) {
		String sql = "UPDATE users_table SET username=?,password=?,role=? WHERE userid=?";
		this.jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getRole(),user.getUserid());
		System.out.println("更新処理完了");
		return true;
	}

	/**
	 * 削除処理_実装
	 * @return 実行結果 boolean
	 */
	@Override
	public boolean logicalDelete(UserEntity user) {
		String sql = "UPDATE users_table SET logical_delete_flg=true WHERE userid=?";
		this.jdbcTemplate.update(sql,user.getUserid());
		System.out.println("削除処理完了");
		return true;
	}

	/**
	 * 新ユーザーID取得処理_実装
	 * @return 新データのユーザーID String
	 */
	@Override
	public String getNextUseId() {
		String sql = "SELECT MAX(userid) AS maxId FROM users_table";
		int maxUserId = this.jdbcTemplate.queryForObject(sql, int.class) + 1;
		this.nextUserId = "%03d".formatted(maxUserId);
		return this.nextUserId;
	}

	/**
	 * ユーザーテーブルリスト取得処理_実装
	 * @return ユーザーテーブルリスト List<UserEntity>
	 */
	@Override
	public List<UserEntity> getUserList() {
		this.userList = jdbcTemplate.query("SELECT * FROM users_table WHERE logical_delete_flg=0", new DataClassRowMapper<>(UserEntity.class));
		return this.userList;
	}

}
