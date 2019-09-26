package com.springboot.springbootdemo.util;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 * @author lee
 * @date 2019/09/25
 */
@Data
public class MapperParam<T> {
    // 要转换的对象
    private T obj;
    // 要转换的对象list
    private List<T> list;
    // 要转换的对象page
    private Page<T> page;
    // 忽略的字段列表
    private List<String> ignoreColumnList;
    // redis的key列表(取list的key),
    private List<String> keyList;
    // 源column列表,顺序必须和keyList的顺序一致
    private List<String> sourceColumnList;
    // userId的字段list
    private List<String> userIdColumnList;


}
