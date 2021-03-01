package com.xkcoding.helloworld.factory;

import cn.hutool.core.collection.CollectionUtil;
import com.xkcoding.helloworld.annotions.DemoType;
import com.xkcoding.helloworld.service.IDemoService;
import com.xkcoding.helloworld.utils.SpringBeanUtil;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wanxianbo
 * @description Demo对象工厂
 * @date 2021/3/1 22:03
 */
@Component
public class DemoClassFactory {

    private final Map<String, IDemoService> maps = new ConcurrentHashMap<>();

    private void demoToBean() {
        Map<String, IDemoService> demoServiceMap = SpringBeanUtil.getBeansOfType(IDemoService.class);
        if (CollectionUtil.isNotEmpty(demoServiceMap)) {
            demoServiceMap.forEach((s, iDemoService) -> {
                DemoType annotation = iDemoService.getClass().getAnnotation(DemoType.class);
                if (Objects.nonNull(annotation)) {
                    String value = annotation.value();
                    maps.put(value, iDemoService);
                }
            });
        }
    }

    public IDemoService getDemoService(String code) {
        // 注入对象到自定义容器
        demoToBean();

        IDemoService iDemoService = maps.get(code);
        if (Objects.isNull(iDemoService)) {
            throw new RuntimeException("未定义 demoInstance");
        }
        return iDemoService;
    }
}
