package ru.dlobanov.timereporter;

import javax.annotation.Resource;
import javax.ejb.*;
import java.util.logging.Logger;

//@Singleton
//@Startup
public class TimerSessionBean {

    private static final Logger logger = Logger.getLogger("ru.dlobanov.timereporter.TimerSessionBean");

    private Timer timer;

    @Resource
    private TimerService timerService;

//    @PostConstruct
    public void setTimer() {
        ScheduleExpression scheduleExpression = new ScheduleExpression();
        scheduleExpression.hour("*");
        scheduleExpression.minute("*");
        scheduleExpression.second("*/1");
        timer = timerService.createCalendarTimer(scheduleExpression, new TimerConfig("", false));
    }

    @Timeout
    public void programmaticTimeout(Timer timer) {
        logger.info("Programmatic timeout occurred");
    }

    @Schedule(hour = "*", minute = "*", second = "*/2", persistent = false)
    public void automaticTimer() {
        logger.info("Automatic timeout occurred");
    }


}
