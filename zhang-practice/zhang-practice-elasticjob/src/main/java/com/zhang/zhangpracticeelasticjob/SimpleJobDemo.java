package com.zhang.zhangpracticeelasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.zen.elasticjob.spring.boot.annotation.ElasticJobConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ElasticJobConfig(cron = "0/10 * * * * ?", shardingTotalCount = 3,
        shardingItemParameters = "0=pig1,1=pig2,2=pig3",
        startedTimeoutMilliseconds = 5000L,
        completedTimeoutMilliseconds = 10000L)
public class SimpleJobDemo implements SimpleJob {

    @Override
    public void execute(ShardingContext content) {
        System.out.println("---------------------------------------");
        System.out.println("JobName:" + content.getJobName());
        System.out.println("JobParameter:" + content.getJobParameter());
        System.out.println("ShardingItem:" + content.getShardingItem());
        System.out.println("ShardingParameter:" + content.getShardingParameter());
        System.out.println("ShardingTotalCount:" + content.getShardingTotalCount());
        System.out.println("TaskId:" + content.getTaskId());
        System.out.println("---------------------------------------");
    }
}
