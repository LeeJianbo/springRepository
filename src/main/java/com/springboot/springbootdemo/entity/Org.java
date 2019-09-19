package com.springboot.springbootdemo.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

/**
 * description: Org
 * date: 2019-09-05 13:30
 * author: Lee
 */
@Data
@Entity
@Table(name = "SHARE_SWAP_ORG")
public class Org implements Serializable {
    @Id
    @GeneratedValue(
            generator = "idGenerator",
            strategy = GenerationType.AUTO
    )
    @GenericGenerator(
            name = "idGenerator",
            strategy = "com.troy.keeper.common.core.base.entity.generator.IdGenerator"
    )
    private Long id;
    @CreatedBy
    @Column(
            name = "created_by",
            nullable = false,
            updatable = false
    )
    @JsonIgnore
    @Audited
    private Long createdBy;
    @CreatedDate
    @Column(
            name = "created_date",
            nullable = false
    )
    @Audited
    private Long createdDate = (new Date()).getTime();
    @LastModifiedBy
    @Column(
            name = "last_modified_by"
    )
    @JsonIgnore
    @Audited
    private Long lastUpdatedBy;
    @LastModifiedDate
    @Column(
            name = "last_modified_date"
    )
    @Audited
    private Long lastUpdatedDate = (new Date()).getTime();
    @Column(
            name = "data_status"
    )
    private String status;
    /**
     * DXP机构id
     */
    @Column(name = "ID_STR")
    private String idStr;

    /**
     * 机构名
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 机构编码
     */
    @Column(name = "CODE")
    private String code;
    /**
     * 级别信息
     */
    @Column(name = "LEVEL_INFO")
    private Long levelInfo;
    /**
     * 父机构id
     */
    @Column(name = "PARENT_ID")
    private Long parentId;
    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;
}
