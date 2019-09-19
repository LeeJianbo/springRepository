package com.springboot.springbootdemo.dto;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserDTO {

    private Long id;

    @NotNull(message = "创建人不能为空")
    private Long createdBy;

    private Long createdDate = new Date().getTime();

    private Long lastUpdatedBy;

    private Long lastUpdatedDate = new Date().getTime();

    private String status;

    @NotBlank(message = "姓名不能为空")
    private String name;

}
