package com.gdut.jh.demo.Realm;

import com.gdut.jh.demo.pojo.entity.User;
import com.gdut.jh.demo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        return s;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = token.getPrincipal().toString();

        User user = userService.getUserByUsername(userName);
        if( user == null){
            throw new AuthenticationException("用户不存在！");
        }
        ByteSource salt = ByteSource.Util.bytes(user.getSalt());
        String passwordInDB = user.getPassword();
        return new SimpleAuthenticationInfo(token.getPrincipal(),passwordInDB,salt,getName());
    }
}
