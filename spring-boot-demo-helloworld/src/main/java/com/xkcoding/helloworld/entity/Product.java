package com.xkcoding.helloworld.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wanxianbo
 * @description
 * @date 2021/3/2 22:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private Long id;
    private Integer num;
    private BigDecimal price;
    private String name;
    private String category;
}
