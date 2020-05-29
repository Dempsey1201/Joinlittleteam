package com.example.javaee.shiro;
import com.example.javaee.entity.user.User;
import com.example.javaee.mapper.user.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 0:01 2020/5/28
 */


public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("token.getPrincipal:" + token.getPrincipal());
        System.out.println("token.getCredentials:" + token.getCredentials());
        String userName = token.getPrincipal().toString();
        User user=new User();
        try {
            user = userMapper.query(userName);
        }
        catch (Exception e){}
        if (user != null) {
            // Object principal, Object credentials, String realmName
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getEmail(), user.getPassword(), getName());
            return authcInfo;
        } else {
            return null;
        }
    }

}
