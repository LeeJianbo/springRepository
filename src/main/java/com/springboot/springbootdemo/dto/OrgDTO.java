package com.springboot.springbootdemo.dto;

import com.springboot.springbootdemo.dto.base.BaseDTO;
import com.springboot.springbootdemo.dto.validateGroup.*;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * description: OrgDTO
 * date: 2019-09-05 14:46
 * author: Lee
 */
@Data
public class OrgDTO extends BaseDTO {
    /**
     * 机构名
     */
    @NotBlank(message = "机构名不能为空！", groups = {Add.class, Update.class})
    private String name;
    /**
     * 机构编码
     */
    @NotBlank(message = "机构编码不能为空！", groups = {Add.class})
    private String code;
    /**
     * 父机构id
     */
    @NotNull(message = "父机构不能为空！", groups = {Add.class})
    private Long parentId;
    /**
     * 级别信息
     */
    private Long levelInfo;
    /**
     * 备注
     */
    private String remark;
    /**
     * 子机构列表
     */
    private List<OrgDTO> child;
}
