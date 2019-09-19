package com.springboot.springbootdemo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Table(name = "USER")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    @JsonIgnore
    @Audited
    private Long createdBy;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    @Audited
    private Long createdDate = new Date().getTime();

    @LastModifiedBy
    @Column(name = "last_modified_by")
    @JsonIgnore
    @Audited
    private Long lastUpdatedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @Audited
    private Long lastUpdatedDate = new Date().getTime();

    @Column(name = "data_status", columnDefinition = "varchar(255) default '1'")
    private String status ;

    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User that = (User) o;

        return getId().equals(that.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
