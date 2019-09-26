package com.springboot.springbootdemo.dto.base;

import lombok.Data;

/**
 * description: BaseDTO
 * date: 2019-08-23 14:34
 * author: Lee
 */
@Data
public class BaseDTO {
    private Long id;
    private Long createdBy;
    private Long createdDate;
    private Long lastUpdatedBy;
    private Long lastUpdatedDate;
    private String status;
}
