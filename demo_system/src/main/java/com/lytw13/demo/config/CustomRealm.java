package com.lytw13.demo.config;

import com.lytw13.demo.model.TbMenu;
import com.lytw13.demo.model.TbRole;
import com.lytw13.demo.model.TbUser;
import com.lytw13.demo.service.MenuService;
import com.lytw13.demo.service.RoleService;
import com.lytw13.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证————");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        TbUser tbUser = new TbUser();
        tbUser.setName(username);
        //获得该用户角色
        List<TbUser> userList = (List<TbUser>) userService.list(tbUser).getResultData();
        Set<String> set = new HashSet<>();
        Set<String> menuSet = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        for (TbUser user:
                userList) {
            for (TbRole role:
            user.getRoleList()) {
              set.add(role.getName());
              TbRole role1 = (TbRole) roleService.get(role.getId()).getResultData();
                for (TbMenu menu:
                        role1.getTbMenuList()) {
                    menuSet.add(menu.getPermission());
                }
            }
        }
        //设置该用户拥有的角色
        info.setRoles(set);
        info.setStringPermissions(menuSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
        TbUser user = new TbUser();
        user.setName(token.getUsername());
        user.setPassword(String.valueOf(token.getPassword()));
        Object resultData = userService.list(user).getResultData();
        List<TbUser> list = (List)resultData;
        if(list == null || list.size() < 1) {
            throw new AccountException("用户名或密码错误不正确");
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(), list.get(0).getPassword(), getName());
    }
}
