package com.example.demo.service;

import com.example.demo.entity.EntityUser;

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
	boolean login(EntityUser user);

}
