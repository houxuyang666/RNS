package com.tdkj.RNS.common;

import com.tdkj.RNS.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.util.calendar.BaseCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description 定时任务
 * @ClassName Schedule
 * @Author Chang
 * @date 2020.05.28 14:46
 */
@Slf4j
@Component
public class Schedule {

    //@Scheduled(cron = "0 */1 * * * ?")
    public void ScheduleClear() {

        log.info("12312311111111111111111111");
    }

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(dateFormat.format(date));
        //DateUtil.formatFullTime(new Date().getTime());
    }
}



