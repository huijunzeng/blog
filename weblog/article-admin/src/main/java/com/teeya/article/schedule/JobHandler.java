package com.teeya.article.schedule;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 文章执行器
 * @Author: ZJH
 * @Date: 2020/4/7 18:09
 */

@Slf4j
@Component
public class JobHandler {

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");
        for (int i = 0; i < 5; i++) {
            XxlJobLogger.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        return ReturnT.SUCCESS;
    }

    /**
     * 文章定时发布任务
     * @param param
     * @return
     * @throws Exception
     */
    @XxlJob("articlePublishJobHandler")
    public ReturnT<String> articlePublishJobHandler(String param) throws Exception {
        //XxlJobLogger.log("XXL-JOB, Hello World.");
        log.info("执行调度任务==============");
        System.out.println("8888888888888888888888");
        for (int i = 0; i < 5; i++) {
            XxlJobLogger.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        return ReturnT.SUCCESS;
    }

}
