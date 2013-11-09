package jp.gr.java_conf.star_diopside.solo.web.mvc.controller.auth;

import jp.gr.java_conf.star_diopside.solo.web.mvc.form.auth.AA02S010Form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * AA02S010(ユーザ新規登録)画面コントローラ
 */
@Controller
@RequestMapping("/auth/AA02S010")
public class AA02S010Controller {

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
}
