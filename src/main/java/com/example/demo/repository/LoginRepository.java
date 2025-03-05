package com.example.demo.repository;

import com.example.demo.entity.EntityUser;

/**
 * ログイン画面_DB処理_インターフェース
 * @author 宮本
 */
public interface LoginRepository {

	/*****メソッド*****/
	/**
	 * ログイン処理_抽象
	 * @return 実行結果
	 */
	boolean login(EntityUser user);
	
}
