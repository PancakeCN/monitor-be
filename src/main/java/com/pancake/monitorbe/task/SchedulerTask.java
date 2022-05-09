package com.pancake.monitorbe.task;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/5/3 21:32
 */
@Log4j2
@Component
public class SchedulerTask {

    /**
     * @Scheduled(fixedRate = 6000) ：上一次开始执行时间点之后6秒再执行
     * @Scheduled(fixedDelay = 6000) ：上一次执行完毕时间点之后6秒再执行
     * @Scheduled(initialDelay=1000, fixedRate=6000) ：第一次延迟1秒后执行，之后按fixedRate的规则每6秒执行一次
     * @Scheduled(cron=""):详见cron表达式http://www.pppet.net/
     */

    @Async
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        log.info("=====>>>>>使用fixedRate执行定时任务");
    }

    @Async
    @Scheduled(fixedDelay = 10000)
    public void scheduled2() {
        log.info("=====>>>>>使用fixedDelay执行定时任务");
    }

    @Async
    @Scheduled(cron="*/6 * * * * ?")
    public void scheduled3(){
        log.info("=====>>>>>使用cron执行定时任务");
    }
}
