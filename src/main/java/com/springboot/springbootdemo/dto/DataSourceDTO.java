package com.springboot.springbootdemo.dto;

import com.springboot.springbootdemo.dto.validateGroup.Add;
import com.springboot.springbootdemo.dto.validateGroup.Update;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * description: DataSourceDTO
 * date: 2019-09-25 11:48
 * author: Lee
 */
@Data
public class DataSourceDTO {
    private String id;
    /**
     * 数据源名称
     */
    @NotBlank(message = "数据源名称不能为空！", groups = {Add.class, Update.class})
    private String name;
    /**
     * 节点id
     */
    @NotBlank(message = "节点不能为空！", groups = {Add.class, Update.class})
    private String logicNodeId;
    /**
     * 节点名称
     */
    private String logicNodeName;
    /**
     * 数据源类型
     */
    @NotBlank(message = "数据源类型不能为空！", groups = {Add.class, Update.class})
    private String dbType;
    /**
     * 数据源url
     */
    @NotBlank(message = "数据源url不能为空！", groups = {Add.class, Update.class})
    private String jdbcUrl;
    /**
     * 数据源用户名
     */
    @NotBlank(message = "数据源用户名不能为空！", groups = {Add.class, Update.class})
    private String jdbcUser;
    /**
     * 数据源密码
     */
    @NotBlank(message = "数据源密码不能为空！", groups = {Add.class, Update.class})
    private String jdbcPwd;
    /**
     * 数据源验证sql
     */
    @NotBlank(message = "数据源验证SQL不能为空！", groups = {Add.class, Update.class})
    private String validConnSql;
    /**
     * schema名
     */
    @NotBlank(message = "schema不能为空！", groups = {Add.class, Update.class})
    private String schemaName;
    /**
     * 最小连接数
     */
    @NotNull(message = "最小连接数不能为空！", groups = {Add.class, Update.class})
    private Integer minPoolSize;
    /**
     * 最大连接数
     */
    @NotNull(message = "最大连接数不能为空！", groups = {Add.class, Update.class})
    private Integer maxPoolSize;
    /**
     * 备注
     */
    private String remark;
    /**
     * 部署状态（0:未部署，1:部署）
     */
    private String deployState;
    /**
     * 更新时间
     */
    private String updateTime;
}
