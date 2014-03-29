package jp.gr.java_conf.star_diopside.solo.web.mvc.scheduler.controller;

import java.text.ParseException;
import java.util.Date;

import javax.validation.Valid;

import jp.gr.java_conf.star_diopside.solo.core.exception.SystemException;
import jp.gr.java_conf.star_diopside.solo.data.entity.Schedule;
import jp.gr.java_conf.star_diopside.solo.service.logic.scheduler.ScheduleManager;
import jp.gr.java_conf.star_diopside.solo.service.userdetails.LoginUserDetails;
import jp.gr.java_conf.star_diopside.solo.support.util.SimpleDateFormatters;
import jp.gr.java_conf.star_diopside.solo.web.mvc.scheduler.form.BA01S010Form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * スケジュール登録コントローラ
 */
@Controller
@RequestMapping("/scheduler/BA01S010")
public class BA01S010Controller {

    @Autowired
    private ScheduleManager scheduleManager;

    /**
     * 画面表示処理を行う。
     *
     * @param form フォーム情報
     * @return 処理結果
     */
    @RequestMapping(method = RequestMethod.GET)
    public String show(BA01S010Form form) {

        SecurityContext sc = SecurityContextHolder.getContext();
        LoginUserDetails user = (LoginUserDetails) sc.getAuthentication().getPrincipal();
        form.setUsername(user.getUserId());

        return "scheduler/BA01S010";
    }

    /**
     * 登録処理を行う。
     * 
     * @param form フォーム情報
     * @param errors エラー情報
     * @return 処理結果
     */
    @RequestMapping(method = RequestMethod.POST, params = "create")
    public String create(@Valid BA01S010Form form, Errors errors) {

        if (errors.hasErrors()) {
            return "scheduler/BA01S010";
        }

        Date scheduledOn;
        try {
            scheduledOn = SimpleDateFormatters.ISO_LOCAL_DATE.get().parse(form.getScheduledOn());
        } catch (ParseException e) {
            throw new SystemException(e);
        }

        Schedule schedule = new Schedule();
        schedule.setScheduledOn(scheduledOn);
        schedule.setDescription(form.getDescription());

        scheduleManager.create(form.getUsername(), schedule);

        return "scheduler/BA01S010";
    }
}
