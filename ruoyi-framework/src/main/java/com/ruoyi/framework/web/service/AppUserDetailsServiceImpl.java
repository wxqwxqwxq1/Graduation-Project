package com.ruoyi.framework.web.service;

import com.ruoyi.system.domain.SysAppLogin;
import com.ruoyi.system.service.ISysAppLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysUserService;

import java.util.Set;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service("appUserDetailsServiceImpl")
public class AppUserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysAppLoginService appLoginService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
//        根据用户名查询
        SysAppLogin user = appLoginService.selectAppLoginByUserName(username);
//        SysUser user = userService.selectUserByUserName(username);
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException(MessageUtils.message("user.not.exists"));
        }

        passwordService.Appvalidate(user);

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysAppLogin sysAppUser)
    {
//        public SysAppLogin(Long ownerId, String userName, String passWord, String ownerPhone)
//        public LoginUser(Long userId, Long deptId, SysUser user, Set<String> permissions)
        SysUser sysUser = new SysUser(1L);
//        sysUser.setUserId(1L);
        sysUser.setDeptId(103L);
        sysUser.setRoleId(1L);
        sysUser.setUserName(sysAppUser.getUserName());
        sysUser.setPassword(sysAppUser.getPassWord());
        sysUser.setPhonenumber(sysAppUser.getOwnerPhone());
        // 获取用户权限集合
        Set<String> permissions = permissionService.getAppRolePermission(sysAppUser);
        System.out.println(permissions);

        LoginUser loginUser = new LoginUser(1L, 1L ,sysUser, permissions);
        System.out.println(loginUser.toString());
        return new LoginUser(1L, 1L ,sysUser, permissions);
    }
}
