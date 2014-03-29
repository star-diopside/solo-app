package jp.gr.java_conf.star_diopside.solo.service.logic.scheduler;

import jp.gr.java_conf.star_diopside.solo.data.entity.Schedule;

/**
 * スケジュール管理インタフェース
 */
public interface ScheduleManager {

    /**
     * スケジュールを登録する。
     * 
     * @param userId ユーザID
     * @param schedule スケジュール情報
     */
    void create(String userId, Schedule schedule);

}
