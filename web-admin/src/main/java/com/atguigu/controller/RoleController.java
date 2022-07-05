package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;


    private final static String PAGE_INDEX = "role/index";
    private final static String SUCCESS_PAGE = "common/successPage";


/*    @RequestMapping
    public String index(ModelMap model) {
        List<Role> list = roleService.findAll();

        model.addAttribute("list", list);
        return PAGE_INDEX;
    }*/

    /*
    * 分页及带条件查询
    * */
    @RequestMapping
    public String index(ModelMap modelMap, HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        PageInfo<Role> page = roleService.findPage(filters);

        modelMap.addAttribute("page", page);
        modelMap.addAttribute("filters", filters);

        return "role/index";
    }



    /*
     * 跳转到 新增页面
     * */
    @GetMapping("/create")
    public String create() {
        return "role/create";
    }

    @RequestMapping("/save")
    public String save(Role role) {
        roleService.insert(role);

        return SUCCESS_PAGE;
    }

    // 删除角色
    @RequestMapping("/delete/{roleId}")
    public String delete(@PathVariable("roleId") Long id) {
        roleService.delete(id);
        // 因为删除操作不需要返回结果，所以直接重定向即可
        // 重定向到查询所有角色的方法
        return "redirect:/role";
    }


    // 回显信息，通过 id 查询角色信息
    @RequestMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") Long id, Map<String, Role> map) {
        Role role = roleService.getById(id);
        // 将查询到的 role 对象放到 request 域中，edit.html 页面中进行回显内容
        map.put("role", role);
        // 去修改的页面
        return "role/edit";
    }

    // 修改信息
    @RequestMapping("/update")
    public String update(Role role) {
        roleService.update(role);

        return SUCCESS_PAGE;
    }

}
