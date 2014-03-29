package jp.gr.java_conf.star_diopside.solo.data.repository;

import jp.gr.java_conf.star_diopside.solo.data.entity.UserSchedule;
import jp.gr.java_conf.star_diopside.solo.data.entity.UserSchedulePk;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ユーザスケジュールリポジトリインタフェース
 */
public interface UserScheduleRepository extends JpaRepository<UserSchedule, UserSchedulePk> {

}
