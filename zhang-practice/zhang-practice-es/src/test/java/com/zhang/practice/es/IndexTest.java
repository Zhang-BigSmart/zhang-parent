package com.zhang.practice.es;

import com.zhang.practice.es.config.EsDao;
import org.elasticsearch.action.get.GetResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @ClassName IndexTest
 * @Description:
 * @Author: zhangzh
 * @Date 2019/4/30 16:16
 */
public class IndexTest extends BaseTest {

    @Autowired
    private EsDao esDao;

    @Test
    public void test() {
        GetResponse res = esDao.findById("mc_shop", "shop", "t6YdnGoBHvLHDthBIxKr");
        System.out.println("查询结果index："+res.getIndex());
        System.out.println("查询结果type："+res.getType());
        System.out.println("查询结果id："+res.getId());
        System.out.println("查询结果source："+res.getSource());
    }
}
