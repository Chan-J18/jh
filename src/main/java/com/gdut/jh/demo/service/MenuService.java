package com.gdut.jh.demo.service;

import com.gdut.jh.demo.dao.MenuDao;
import com.gdut.jh.demo.dao.UserRoleDao;
import com.gdut.jh.demo.pojo.entity.Menu;
import com.gdut.jh.demo.pojo.entity.User;
import com.gdut.jh.demo.pojo.entity.role_menu;
import com.gdut.jh.demo.pojo.entity.user_role;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
    @Autowired
    MenuDao menuDao;
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleMenuService roleMenuService;

    public List<Menu> getMenusByCurrentUser() {
        // 从数据库中获取当前用户
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getUserByUsername(username);

        // 获得当前用户对应的角色的 id 列表
        int rid = userRoleService.getByUid(user.getId()).getRid();

        // 查询出这些角色对应的所有菜单项
        List<Integer> menuIds = roleMenuService.findAllByRid(rid)
                .stream().map(role_menu::getMid).collect(Collectors.toList());
        List<Menu> menus = menuDao.findAllById(menuIds).stream().distinct().collect(Collectors.toList());

        // 处理菜单项的结构
        handleMenus(menus);
        return menus;
    }

    public void handleMenus(List<Menu> menus) {
        menus.forEach(m -> {
            List<Menu> children = menuDao.findByParentId(m.getId());
            m.setChildren(children);
        });

        menus.removeIf(m -> m.getParentId() != 0);
    }
}
