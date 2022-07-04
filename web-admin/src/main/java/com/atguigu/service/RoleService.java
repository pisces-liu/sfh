package com.atguigu.service;

import com.atguigu.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface RoleService {
    List<Role> findAll();

    /*
    * 添加角色
    * */
    Integer insert(Role role);

    /*
    * 通过 id 删除角色
    * */
    void delete(Long id);

    /*
    * 通过 id 查询角色，用于回显信息
    * */
    Role getById(Long id);

    /*
    * 修改角色信息
    * */
    Integer update(Role role);

    PageInfo<Role> findPage(Map<String, Object> filters);
}
