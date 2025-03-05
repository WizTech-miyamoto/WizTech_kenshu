package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.EntityUser;

/**
 * ユーザー登録画面_DB処理_インターフェース
 * @author 宮本
 */
public interface UserRegistRepository {

	/*****メソッド*****/
	/**
	 * ユーザー登録処理_抽象
	 * @return 実行結果
	 */
	boolean regist(EntityUser user);
	
	/**
	 * ユーザー更新処理_抽象
	 * @return 実行結果
	 */
	boolean update(EntityUser user);

	/**
	 * ユーザー削除処理_抽象
	 * @return 実行結果
	 */
	boolean delete(EntityUser user);

	/**
	 * ユーザーID最大値取得処理_抽象
	 * @return 実行結果
	 */
	String getNextUseId();
	
	/**
	 * ユーザーリスト取得_抽象
	 * @return
	 */
	List<EntityUser> getUserList();
	
}
