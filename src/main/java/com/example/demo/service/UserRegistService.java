package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.UserEntity;

/**
 * ユーザー登録画面_業務処理_インターフェース
 * @author 宮本
 */
public interface UserRegistService {

	/*****メソッド*****/
	/**
	 * ユーザー登録処理_抽象
	 * @return 実行結果
	 */
	boolean regist(UserEntity user);

	/**
	 * ユーザー更新処理_抽象
	 * @return 実行結果
	 */
	boolean update(UserEntity user);

	/**
	 * ユーザー削除処理_抽象
	 * @return 実行結果
	 */
	boolean logicalDelete(UserEntity user);

	/**
	 * ユーザーID最大値取得処理_抽象
	 * @return 実行結果
	 */
	String getNextUseId();
	
	/**ユーザーリスト取得_抽象
	 * @return
	 */
	List<UserEntity> getUserList();
}
