package jp.gr.java_conf.star_diopside.solo.service.logic.scheduler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import jp.gr.java_conf.star_diopside.solo.data.entity.Schedule;
import jp.gr.java_conf.star_diopside.solo.data.entity.UserSchedule;
import jp.gr.java_conf.star_diopside.solo.data.repository.ScheduleRepository;
import jp.gr.java_conf.star_diopside.solo.data.repository.UserScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * スケジュール管理クラス
 */
@Service
public class ScheduleManagerImpl implements ScheduleManager {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserScheduleRepository userScheduleRepository;

    @Override
    @Transactional
    public void create(String userId, Schedule schedule) {

        Date current = new Date();
        String today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        int nextNumber = 1;

        String maxScheduleId = scheduleRepository.findMaxScheduleIdByScheduleIdLike(today + "%");
        if (maxScheduleId != null) {
            String maxNumber = maxScheduleId.substring(today.length());
            nextNumber = Integer.parseInt(maxNumber) + 1;
        }

        schedule.setScheduleId(today + String.format("%08d", nextNumber));
        schedule.setCreatedAt(current);
        schedule.setCreatedUserId(userId);
        schedule.setUpdatedAt(current);
        schedule.setUpdatedUserId(userId);
        schedule.setVersion(0);

        scheduleRepository.save(schedule);

        UserSchedule userSchedule = new UserSchedule();

        userSchedule.setUserId(userId);
        userSchedule.setScheduleId(schedule.getScheduleId());
        userSchedule.setCreatedAt(current);
        userSchedule.setCreatedUserId(userId);
        userSchedule.setUpdatedAt(current);
        userSchedule.setUpdatedUserId(userId);
        userSchedule.setVersion(0);

        userScheduleRepository.save(userSchedule);
    }
}
