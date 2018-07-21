package priv.jesse.mall.handler;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import priv.jesse.mall.constant.SysConstant;
import priv.jesse.mall.service.SpiderService;
import com.google.common.collect.Maps;
/**
 * 爬虫调度处理器
 */
@Component
public class SpiderHandler {
    @Autowired
    private SpiderService spiderService;

    private static final Logger logger = LoggerFactory.getLogger(SpiderHandler.class);

    public void spiderData() {
        logger.info("爬虫开始....");
        Date startDate = new Date();
        // 使用现线程池提交任务
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        //引入countDownLatch进行线程同步，使主线程等待线程池的所有任务结束，便于计时
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for(int i = 1; i < 101; i += 2) {
            Map<String, String> params = Maps.newHashMap();
            params.put("keyword", "电脑");
            params.put("enc", "utf-8");
            params.put("page", i + "");
            executorService.submit(() -> {
                spiderService.spiderData(SysConstant.BASE_URL, params);
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        Date endDate = new Date();

        FastDateFormat fdf = FastDateFormat.getInstance(SysConstant.DEFAULT_DATE_FORMAT);
        logger.info("爬虫结束....");
        logger.info("[开始时间:" + fdf.format(startDate) + ",结束时间:" + fdf.format(endDate) + ",耗时:"
                + (endDate.getTime() - startDate.getTime()) + "ms]");

    }
}