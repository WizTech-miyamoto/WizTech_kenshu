package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.UserEntity;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;

/**
 * ログイン処理を行うコントローラ
 * @author 宮本
 */
@Controller
public class LoginController {

	/*****フィールド*****/
	/**
	 * HTMLテンプレート名_ログイン画面
	 */
	private final String LOGIN = "login";

	/**
	 * HTMLテンプレート名_ログイン画面
	 */
	private final String REDIRECT_LOGOUT = "redirect:login";

	/**
	 * HTMLテンプレート名_リスト画面
	 */
	private final String REDIRECT_DASHBOARD = "redirect:dashboard";

	/**
	 * DI用フィールド_サービス層処理
	 */
	private final LoginService service;

	/*****コンストラクタ*****/
	//	@Autowired
	/**
	 * DI用コンストラクタ
	 * @param service サービス層処理
	 * @param user ユーザーEntity
	 */
	public LoginController(LoginService service, UserEntity user) {
		this.service = service;
	}

	/*****メソッド*****/
	/**
	 * ログイン画面遷移
	 * @param form 入力フォーム
	 * @return HTMLテンプレート名
	 */

	@GetMapping("/login")
	public String showLogin(@ModelAttribute LoginForm form) {
		return LOGIN;
	}

//	@GetMapping("/")
//	public String forwardLogin(HttpServletRequest req,
//			@Validated @ModelAttribute LoginForm form,
//			BindingResult result,
//			Model model) {
//		System.out.println("forwardLogin");
//		return "forward:login";
//	}
	
	@PostMapping("/login")
	public String validationCheck(HttpServletRequest req,
			RedirectAttributes reAtt,
			@Validated @ModelAttribute LoginForm form,
			BindingResult result,
			Model model) {
		
		System.out.println("login");
		System.out.println("loginForm:" + form);
		System.out.println("validation:" + result);

		//	入力エラーの場合、ログイン画面に遷移
		if (result.hasErrors()) {
			return LOGIN;
		}
		/*****業務処理（サービス層）呼び出し*****/
		//ユーザーIDまたはパスワードがユーザーテーブルに存在しない場合はエラー、ログイン画面に遷移する
//		System.out.println("LoginStart");
//		if (!this.service.login(form.getUsername(), form.getPassword())) {
//			result.addError(new FieldError(result.getObjectName(), "userId", "ユーザーIDまたはパスワードが間違っています"));
//			return LOGIN;
//		}
		System.out.println("LoginEnd");

		//リダイレクト先へパラメータを渡す

		//	ユーザー登録処理のコントローラーへリダイレクト
		return REDIRECT_DASHBOARD;
	}

//	@PostMapping("/logout")
//	public String logout(HttpServletRequest req,
//			@ModelAttribute LoginForm form) {
//		req.getSession(true).invalidate();
//
//		return REDIRECT_LOGOUT;
//	}
}
