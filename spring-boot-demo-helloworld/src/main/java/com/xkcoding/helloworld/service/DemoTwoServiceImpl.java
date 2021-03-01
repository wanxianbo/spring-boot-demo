package com.xkcoding.helloworld.service;

import com.xkcoding.helloworld.annotions.DemoType;
import org.springframework.stereotype.Service;

/**
 * @author wanxianbo
 * @description
 * @date 2021/3/1 22:09
 */
@Service
@DemoType(value = "two")
public class DemoTwoServiceImpl implements IDemoService{
    @Override
    public String getResult() {
        return "two ...";
    }
}
