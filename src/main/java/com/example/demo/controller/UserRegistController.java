package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.EntityUser;
import com.example.demo.form.LoginForm;
import com.example.demo.form.UserResistForm;
import com.example.demo.service.UserRegistService;

/**
 * ユーザー登録処理を行うコントローラ
 * @author 宮本
 */
@Controller
public class UserRegistController {

	/*****フィールド*****/
	/**
	 * HTMLテンプレート名_ユーザー登録画面
	 */
	private final String USER_REGIST = "userRegist/regist";

	/**
	 * HTMLテンプレート名_リスト画面
	 */
	private final String HOME = "home";

	/**
	 * DI用フィールド_ユーザー登録サービス層処理
	 */
	private final UserRegistService service;

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
	public UserRegistController(UserRegistService service, EntityUser user) {
		this.service = service;
		this.user = user;
	}

	/*****メソッド*****/
	/**
	 * ホーム画面遷移
	 * @param req リクエスト情報
	 * @param form 入力フォーム
	 * @param result Validation結果
	 * @return HTMLテンプレート名
	 */
	@PostMapping("/home")
	public String showList(HttpServletRequest req,
			@Validated @ModelAttribute LoginForm form,
			BindingResult result,
			Model model) {

		//ユーザーテーブルからユーザー情報のリストを取得する
		List<EntityUser> userList = this.service.getUserList();

		model.addAttribute("loginUserId", req.getSession().getAttribute("loginUserId"));
		model.addAttribute("userList", userList);

		System.out.println(req.getServletPath());
		System.out.println(req.getRequestURI());
		System.out.println(req.getRequestURL());
		
		//	ホーム画面への遷移
		return HOME;
	}

	/**
	 * ユーザー登録画面遷移
	 * @param form 入力フォーム
	 * @return HTMLテンプレート名
	 */
	@PostMapping("/userRegist/regist")
	public String showUserRegist(HttpServletRequest req,
			@ModelAttribute UserResistForm form,
			Model model) {

		model.addAttribute("loginUserId", req.getSession().getAttribute("loginUserId"));
		if (req.getParameter("regist") != null) {
			model.addAttribute("mode", "登録");
			model.addAttribute("modeNum", 1);
			form.setUserId(this.service.getNextUseId());
		} else if (req.getParameter("edit") != null) {
			model.addAttribute("mode", "編集");
			model.addAttribute("modeNum", 2);
		} else if (req.getParameter("delete") != null) {
			model.addAttribute("mode", "削除");
			model.addAttribute("modeNum", 3);
		}

		System.out.println(req.getServletPath());
		System.out.println(req.getRequestURI());
		System.out.println(req.getRequestURL());
		
		return USER_REGIST;
	}

	/**
	 * ユーザー登録実行処理
	 * @param form 入力フォーム
	 * @return HTMLテンプレート名
	 */
	@PostMapping("/userRegist/registExcecute")
	public String showUserRegistExcecute(HttpServletRequest req,
			@Validated @ModelAttribute UserResistForm form,
			BindingResult result,
			Model model) {

		if (req.getParameter("back") == null) {
			//	入力エラーの場合、ユーザー登録画面に遷移
			if (result.hasErrors()) {
				model.addAttribute("mode", req.getParameter("modeName"));
				model.addAttribute("modeNum", req.getParameter("modeNum"));
				model.addAttribute("loginUserId", req.getAttribute("loginUserId"));
				return USER_REGIST;
			}

			this.user.setEntityRegist(form);

			switch (req.getParameter("modeNum")) {
			case "1":
				System.out.println("登録処理開始");
				this.service.regist(this.user);
				break;
			case "2":
				System.out.println("更新処理開始");
				this.service.update(this.user);
				break;
			case "3":
				System.out.println("削除処理開始");
				this.service.delete(this.user);
			}
		}
		//ユーザーテーブルからユーザー情報のリストを取得する
		List<EntityUser> userList = this.service.getUserList();
		model.addAttribute("loginUserId", req.getSession().getAttribute("loginUserId"));
		model.addAttribute("userList", userList);

		System.out.println(req.getServletPath());
		System.out.println(req.getRequestURI());
		System.out.println(req.getRequestURL());
		
		return HOME;
	}

}
