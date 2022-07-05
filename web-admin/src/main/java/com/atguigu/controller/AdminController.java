package com.atguigu.controller;

import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @RequestMapping
    public String index(ModelMap modelMap, HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        PageInfo<Admin> page = adminService.findPage(filters);

        modelMap.addAttribute("page", page);
        modelMap.addAttribute("filters", filters);
        return "admin/index";
    }

    @GetMapping("/create")
    public String create() {
        return "admin/create";
    }


    @GetMapping("/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id) {
        Admin admin = adminService.getById(id);
        model.addAttribute("admin", admin);
        return "admin/edit";
    }


    @PostMapping(value = "/update")
    public String update(Admin admin) {

        adminService.update(admin);

        return "common/successPage";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/save")
    public String save(Admin admin) {
        admin.setHeadUrl("http://img.touxiangwu.com/uploads/allimg/2021122415/t25ob4gs45v.jpg");
        adminService.insert(admin);
        return "common/successPage";
    }


}
