package com.whkj.project.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Data
@Repository
public class RoleEntity implements Serializable {

    private Integer id;

    private String roleName;

    private String remarks;

    private String perms;
}
