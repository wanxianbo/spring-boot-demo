package com.xkcoding.helloworld.service;

import com.xkcoding.helloworld.annotions.DemoType;
import org.springframework.stereotype.Service;

/**
 * @author wanxianbo
 * @description
 * @date 2021/3/1 22:08
 */
@Service
@DemoType(value = "one")
public class DemoOneServiceImpl implements IDemoService{
    @Override
    public String getResult() {
        return "one ...";
    }
}
