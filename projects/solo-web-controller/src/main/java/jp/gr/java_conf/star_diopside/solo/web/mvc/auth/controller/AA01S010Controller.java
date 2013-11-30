package jp.gr.java_conf.star_diopside.solo.web.mvc.auth.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import jp.gr.java_conf.star_diopside.solo.web.mvc.auth.form.AA01S010Form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * AA01S010(ユーザログイン)画面コントローラ
 */
@Controller
@RequestMapping("/auth/AA01S010")
public class AA01S010Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(AA01S010Controller.class);
    private static final Map<String, String> EXCEPTION_MAP;

    static {
        HashMap<String, String> map = new HashMap<>();
        map.put(BadCredentialsException.class.getName(), "Error.BadCredential");
        map.put(UsernameNotFoundException.class.getName(), "Error.BadCredential");
        map.put(AccountExpiredException.class.getName(), "Error.UserInvalid");
        EXCEPTION_MAP = Collections.unmodifiableMap(map);
    }

    /**
     * 画面表示処理を行う。
     * 
     * @param form フォーム情報
     * @return 処理結果
     */
    @RequestMapping(method = RequestMethod.GET)
    public String show(AA01S010Form form) {
        return "auth/AA01S010";
    }

    /**
     * 認証エラー処理を行う。
     * 
     * @param form フォーム情報
     * @param errors エラー情報
     * @param session セッション情報
     * @return 処理結果
     */
    @RequestMapping(value = "failure", method = RequestMethod.GET)
    public String failure(AA01S010Form form, Errors errors, HttpSession session) {

        // 認証エラー例外を取得する。
        Exception exception = (Exception) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        if (exception != null) {
            String errorCode = EXCEPTION_MAP.get(exception.getClass().getName());
            if (errorCode != null) {
                errors.reject(errorCode);
            } else {
                LOGGER.debug(exception.getMessage(), exception);
                errors.reject("Error.Authentication");
            }
        }

        return "auth/AA01S010";
    }

    /**
     * ログイン処理を行う。
     * 
     * @param form フォーム情報
     * @param errors エラー情報
     * @return 処理結果
     */
    @RequestMapping(method = RequestMethod.POST, params = "login")
    public String login(@Valid AA01S010Form form, Errors errors) {

        if (errors.hasErrors()) {
            return "auth/AA01S010";
        } else {
            return "forward:/j_spring_security_check";
        }
    }
}
