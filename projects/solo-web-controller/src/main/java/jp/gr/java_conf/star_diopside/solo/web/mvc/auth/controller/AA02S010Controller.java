package jp.gr.java_conf.star_diopside.solo.web.mvc.auth.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import jp.gr.java_conf.star_diopside.solo.core.exception.BusinessException;
import jp.gr.java_conf.star_diopside.solo.service.logic.UserService;
import jp.gr.java_conf.star_diopside.solo.support.util.ExceptionUtils;
import jp.gr.java_conf.star_diopside.solo.web.mvc.auth.form.AA01S010Form;
import jp.gr.java_conf.star_diopside.solo.web.mvc.auth.form.AA02S010Form;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.code.kaptcha.Constants;

/**
 * AA02S010(ユーザ新規登録)画面コントローラ
 */
@Controller
@RequestMapping("/auth/AA02S010")
public class AA02S010Controller {

    @Autowired
    private UserService userService;

    /**
     * 画面表示処理を行う。
     * 
     * @param form フォーム情報
     * @return 処理結果
     */
    @RequestMapping(method = RequestMethod.GET)
    public String show(AA02S010Form form) {
        return "auth/AA02S010";
    }

    /**
     * 戻る処理を行う。
     * 
     * @param form フォーム情報
     * @return 処理結果
     */
    @RequestMapping(method = RequestMethod.POST, params = "back")
    public String back(AA02S010Form form) {
        return "redirect:/auth/AA01S010";
    }

    /**
     * 新規登録処理を行う。
     * 
     * @param form フォーム情報
     * @param errors エラー情報
     * @param session セッション情報
     * @param attr リダイレクト属性情報
     * @return 処理結果
     */
    @RequestMapping(method = RequestMethod.POST, params = "register")
    public String register(@Valid AA02S010Form form, Errors errors, HttpSession session, RedirectAttributes attr) {

        if (errors.hasErrors()) {
            return "auth/AA02S010";
        }

        // パスワードの一致チェックを行う。
        if (!StringUtils.equals(form.getPassword(), form.getPasswordConfirm())) {
            errors.reject("Error.NotMatchPasswordConfirm");
            return "auth/AA02S010";
        }

        // キャプチャ文字の一致チェックを行う。
        String captcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!StringUtils.equals(captcha, form.getCaptcha())) {
            errors.rejectValue("captcha", "Error.NotMatchCaptcha");
            return "auth/AA02S010";
        }

        // ユーザ登録を行う。
        try {
            userService.createUser(form.getUsername(), form.getNickname(), form.getPassword());
        } catch (BusinessException e) {
            ExceptionUtils.reject(errors, e);
            return "auth/AA02S010";
        }

        // ユーザログイン画面に遷移する。
        AA01S010Form nextForm = new AA01S010Form();
        nextForm.setUsername(form.getUsername());
        attr.addFlashAttribute(nextForm);

        return "redirect:/auth/AA01S010";
    }
}
