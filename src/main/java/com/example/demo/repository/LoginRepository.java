package com.example.demo.repository;

/**
 * ログイン画面_DB処理_インターフェース
 * @author 宮本
 */
public interface LoginRepository {

	/*****メソッド*****/
	/**
	 * ログイン処理_抽象
	 * @return ログインユーザーのパスワード
	 */
	String getPassword(String userid);
	
}
