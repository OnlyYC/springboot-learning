package com.liaoyb.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liaoyb
 * @date 2018-07-07 9:39
 */
@AllArgsConstructor
@Data
public class User {
    private String id;
    private String name;
    private Integer age;
}
