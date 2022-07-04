package com.atguigu.service.impl;

import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.atguigu.util.CastUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Integer insert(Role role) {
        return roleDao.insert(role);
    }

    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }

    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    @Override
    public Integer update(Role role) {
        return roleDao.update(role);
    }

    @Override
    public PageInfo<Role> findPage(Map<String, Object> filters) {
        // 当前页数
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        // 每页显示的记录页数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 10);

        // 通过 PageHelper 插件开启分页
        PageHelper.startPage(pageNum, pageSize);
        // 调用 roleDao 中分页及带条件查询的方法
        Page<Role> page = roleDao.findPage(filters);

        return new PageInfo<>(page, 5);
    }


}
