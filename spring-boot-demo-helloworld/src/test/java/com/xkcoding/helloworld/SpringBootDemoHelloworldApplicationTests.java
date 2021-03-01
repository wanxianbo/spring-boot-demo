package com.xkcoding.helloworld;

import com.xkcoding.helloworld.factory.DemoClassFactory;
import com.xkcoding.helloworld.service.DemoOneServiceImpl;
import com.xkcoding.helloworld.service.IDemoService;
import com.xkcoding.helloworld.utils.SpringBeanUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoHelloworldApplicationTests {

    @Autowired
    private DemoClassFactory demoClassFactory;

    @Test
    public void contextLoads() {
        DemoOneServiceImpl bean = SpringBeanUtil.getBean(DemoOneServiceImpl.class);
//        DemoOneServiceImpl bean = SpringBeanUtil.getBean(DemoOneServiceImpl.class);
        System.out.println(bean.getResult());
    }

    @Test
    public void testDemoService() {
        IDemoService one = demoClassFactory.getDemoService("one");
        System.out.println(one.getResult());
    }

}
