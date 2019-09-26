package com.springboot.springbootdemo.dto;

import com.springboot.springbootdemo.dto.base.BaseDTO;
import lombok.Data;

/**
 * description: ResDTO
 * date: 2019-09-25 11:46
 * author: Lee
 */
@Data
public class ResDTO extends BaseDTO {
    /**
     * 成功失败标识符
     **/
    private Boolean suceess;

    /**
     * 成功失败状态
     **/
    private String status;

    /**
     * 信息
     **/
    private String msg;

    /**
     * 返回数据
     **/
    private Object data;
}
