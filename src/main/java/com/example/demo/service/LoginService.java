package com.example.demo.service;

/**
 * ログイン画面_業務処理_インターフェース
 * @author 宮本
 */
public interface LoginService {

	/*****メソッド*****/
	/**
	 * ログイン処理_抽象
	 * @return 実行結果
	 */
	//20250311 SpringSecurity使用
	boolean login(String username,String password);

	//旧処理
//	boolean login(UsersEntity user);

}
