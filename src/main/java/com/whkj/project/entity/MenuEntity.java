package com.whkj.project.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Data
@Repository
public class MenuEntity implements Serializable{

    private Integer id;

    private String menuName;

    private String perms;
}
