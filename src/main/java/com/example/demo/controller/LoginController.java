package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.EntityUser;
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
	private final String LOGOUT = "redirect:login";

	/**
	 * HTMLテンプレート名_リスト画面
	 */
	private final String FORWARD_HOME = "forward:home";

	/**
	 * DI用フィールド_サービス層処理
	 */
	private final LoginService service;

	/**
	 * DI用フィールド_ユーザーEntity
	 */
	private final EntityUser user;

	/*****コンストラクタ*****/
	//	@Autowired
	/**
	 * DI用コンストラクタ
	 * @param service サービス層処理
	 * @param user ユーザーEntity
	 */
	public LoginController(LoginService service, EntityUser user) {
		this.service = service;
		this.user = user;
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

	@PostMapping("/login")
	public String showList(HttpServletRequest req,
			@Validated @ModelAttribute LoginForm form,
			BindingResult result,
			Model model) {

		//	入力エラーの場合、ログイン画面に遷移
		if (result.hasErrors()) {
			return LOGIN;
		}
		
		System.out.println(req.getServletPath());
		System.out.println(req.getRequestURI());
		System.out.println(req.getRequestURL());
		
		/*****入力に問題ない場合、セッションを作成する*****/
		//	セッション作成（古いセッションがある場合は削除して再作成）
		req.getSession(true).invalidate();
		HttpSession session = req.getSession();

		session.setAttribute("loginUserId", form.getUserId());

		/*****業務処理（サービス層）呼び出し*****/
		//ユーザーIDまたはパスワードがユーザーテーブルに存在しない場合はエラー、ログイン画面に遷移する
		this.user.setEntityLogin(form);
		if (!this.service.login(this.user)) {
			result.addError(new FieldError(result.getObjectName(), "userId", "ユーザーIDまたはパスワードが間違っています"));
			return LOGIN;
		}

		//	ユーザー登録処理のコントローラーへフォワード
		return FORWARD_HOME;
	}

	@PostMapping("/logout")
	public String logout(HttpServletRequest req,
			@ModelAttribute LoginForm form) {
		req.getSession(true).invalidate();
		
		System.out.println(req.getServletPath());
		System.out.println(req.getRequestURI());
		System.out.println(req.getRequestURL());
		
		return LOGOUT;
	}
}
