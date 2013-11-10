package jp.gr.java_conf.star_diopside.solo.web.mvc.root.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * メニュー画面コントローラ
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    /**
     * 画面表示処理を行う。
     * 
     * @return 処理結果
     */
    @RequestMapping(method = RequestMethod.GET)
    public String show() {
        return "menu";
    }
}
