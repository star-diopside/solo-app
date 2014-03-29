package jp.gr.java_conf.star_diopside.solo.data.repository;

import jp.gr.java_conf.star_diopside.solo.data.entity.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * スケジュールリポジトリインタフェース
 */
public interface ScheduleRepository extends JpaRepository<Schedule, String> {

    @Query("select max(s.scheduleId) from Schedule s where s.scheduleId like ?1")
    String findMaxScheduleIdByScheduleIdLike(String scheduleIdLike);

}
