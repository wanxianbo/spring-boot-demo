package com.xkcoding.helloworld;

import com.google.common.collect.Lists;
import com.xkcoding.helloworld.entity.Product;
import com.xkcoding.helloworld.factory.DemoClassFactory;
import com.xkcoding.helloworld.service.DemoOneServiceImpl;
import com.xkcoding.helloworld.service.IDemoService;
import com.xkcoding.helloworld.utils.SpringBeanUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Test
    public void testCollectorsGroupBy() {
        Product prod1 = new Product(1L, 1, new BigDecimal("15.5"), "面包", "零食");
        Product prod2 = new Product(2L, 2, new BigDecimal("20"), "饼干", "零食");
        Product prod3 = new Product(3L, 3, new BigDecimal("30"), "月饼", "零食");
        Product prod4 = new Product(4L, 3, new BigDecimal("10"), "青岛啤酒", "啤酒");
        Product prod5 = new Product(5L, 10, new BigDecimal("15"), "百威啤酒", "啤酒");

        ArrayList<Product> list = Lists.newArrayList(prod1, prod2, prod3, prod4, prod5);

        // 按照类目分组：
        Map<String, List<Product>> collect1 = list.stream().collect(Collectors.groupingBy(Product::getCategory));
        collect1.forEach((key, value) -> System.out.println(key + ":" + value));

        // 按照几个属性拼接分组：
        Map<String, List<Product>> collect2 = list.stream().collect(Collectors.groupingBy(item -> item.getCategory() + "_" + item.getName()));
        collect2.forEach((key, value) -> System.out.println(key + ":" + value));

        // 根据不同条件分组
        Map<String, List<Product>> collect3 = list.stream().collect(Collectors.groupingBy(item -> {
            if (item.getNum() < 3) {
                return "3";
            } else {
                return "other";
            }
        }));
        collect3.forEach((key, value) -> System.out.println(key + ":" + value));

        // 进行二级分组
        Map<String, Map<String, List<Product>>> collect4 = list.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.groupingBy(item -> {
            if (item.getNum() < 3) {
                return "3";
            } else {
                return "other";
            }
        })));
        System.out.println("4:----------------------");
        collect4.forEach((key, value) -> System.out.println(key + ":" + value));

        // 按子组收集数据

        // 计数
        Map<String, Long> countCollect = list.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        System.out.println("count:----------------------");
        countCollect.forEach((key, value) -> System.out.println(key + ":" + value));

        // 求和
        Map<String, Integer> sumCollect = list.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.summingInt(Product::getNum)));
        System.out.println("sum:----------------------");
        sumCollect.forEach((key, value) -> System.out.println(key + ":" + value));

        // 把收集器的结果转换为另一种类型
        Map<String, Product> collect5 = list.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Product::getNum)), Optional::get)));
        System.out.println("collect5:----------------------");
        collect5.forEach((key, value) -> System.out.println(key + ":" + value));

        Map<Long, Product> productMap = list.stream().collect(Collectors.toMap(Product::getId, Function.identity(), (k1, k2) -> k1));

        int sum = list.stream().mapToInt(Product::getNum).sum();
        System.out.println(sum);

        BigDecimal reduce = list.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(reduce.doubleValue());

    }


}
