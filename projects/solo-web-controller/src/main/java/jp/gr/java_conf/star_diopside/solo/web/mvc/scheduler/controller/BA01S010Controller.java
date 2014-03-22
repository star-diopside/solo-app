package jp.gr.java_conf.star_diopside.solo.web.mvc.scheduler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * スケジュール検索コントローラ
 */
@Controller
@RequestMapping("/scheduler/BA01S010")
public class BA01S010Controller {

    /**
     * 画面表示処理を行う。
     *
     * @return 処理結果
     */
    @RequestMapping(method = RequestMethod.GET)
    public String show() {
        return "scheduler/BA01S010";
    }
}
