package com.example.demo.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.UserRegistRepository;

/**
 * ログイン画面_業務処理_実装
 * @author 宮本
 */
@Service
public class UserRegistServiceImpl implements UserRegistService {

	/*****フィールド*****/
	/**
	 * DI用フィールド_インフラ層処理
	 */
	private final UserRegistRepository repository;

	/**
	 * 戻り値(処理結果)
	 */
	private boolean registResult;

	/**
	 * 戻り値（ユーザーリスト）
	 */
	private List<UsersEntity> userList;

	/**
	 * 戻り値(ユーザーID最大値)
	 */
	private String nextUserId;

	/*****コンストラクタ*****/

	/**
	 * DI用コンストラクタ
	 * @param repository インフラ層処理
	 */
	//	@Autowired
	public UserRegistServiceImpl(UserRegistRepository repository) {
		this.repository = repository;
		this.registResult = true;
		this.userList = null;
	}

	/*****メソッド*****/
	/**
	 *　ユーザー登録処理_実装
	 * @return 実行結果 boolean
	 */
	@Override
	public boolean regist(UsersEntity user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		this.registResult = this.repository.regist(user);
		return this.registResult;
	}

	/**
	 *　ユーザー更新処理_実装
	 * @return 実行結果 boolean
	 */
	@Override
	public boolean update(UsersEntity user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		this.registResult = this.repository.update(user);
		return this.registResult;
	}

	/**
	 *　ユーザー削除処理_実装
	 * @return 実行結果 boolean
	 */
	@Override
	public boolean logicalDelete(UsersEntity user) {
		this.registResult = this.repository.logicalDelete(user);
		return this.registResult;
	}

	/**
	 * ユーザーID最大値取得処理_実装
	 * @return 実行結果 @return 新データのユーザーID String
	 */
	@Override
	public String getNextUseId() {
		this.nextUserId = this.repository.getNextUseId();
		return this.nextUserId;
	}
	
	/**
	 * ユーザーテーブルリスト取得処理_実装
	 * @return ユーザーテーブルリスト List<UsersEntity>
	 */
	@Override
	public List<UsersEntity> getUserList() {
		this.userList = repository.getUserList();
		return this.userList;
	}

}
