package jp.gr.java_conf.star_diopside.solo.data.repository;

import jp.gr.java_conf.star_diopside.solo.data.entity.UserSchedule;
import jp.gr.java_conf.star_diopside.solo.data.entity.UserScheduleId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * ユーザスケジュールリポジトリインタフェース
 */
public interface UserScheduleRepository extends JpaRepository<UserSchedule, UserScheduleId> {

    /**
     * ユーザIDを条件にユーザスケジュール情報を削除する。
     * 
     * @param userId ユーザID
     */
    @Modifying
    @Query("delete from UserSchedule u where u.userId = ?1")
    void deleteByUserId(String userId);

}
