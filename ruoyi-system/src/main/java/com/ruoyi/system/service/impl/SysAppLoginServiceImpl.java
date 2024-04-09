package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysAppLoginMapper;
import com.ruoyi.system.domain.SysAppLogin;
import com.ruoyi.system.service.ISysAppLoginService;

/**
 * sysAppLoginService业务层处理
 *
 * @author wxq
 * @date 2024-03-30
 */
@Service
public class SysAppLoginServiceImpl implements ISysAppLoginService
{
    @Autowired
    private SysAppLoginMapper sysAppLoginMapper;

    /**
     * 查询sysAppLogin
     *
     * @param ownerId sysAppLogin主键
     * @return sysAppLogin
     */
    @Override
    public SysAppLogin selectSysAppLoginByOwnerId(Long ownerId)
    {
        return sysAppLoginMapper.selectSysAppLoginByOwnerId(ownerId);
    }

    /**
     * 查询sysAppLogin列表
     *
     * @param sysAppLogin sysAppLogin
     * @return sysAppLogin
     */
    @Override
    public List<SysAppLogin> selectSysAppLoginList(SysAppLogin sysAppLogin)
    {
        return sysAppLoginMapper.selectSysAppLoginList(sysAppLogin);
    }

    /**
     * 新增sysAppLogin
     *
     * @param sysAppLogin sysAppLogin
     * @return 结果
     */
    @Override
    public int insertSysAppLogin(SysAppLogin sysAppLogin)
    {
        return sysAppLoginMapper.insertSysAppLogin(sysAppLogin);
    }

    /**
     * 修改sysAppLogin
     *
     * @param sysAppLogin sysAppLogin
     * @return 结果
     */
    @Override
    public int updateSysAppLogin(SysAppLogin sysAppLogin)
    {
        return sysAppLoginMapper.updateSysAppLogin(sysAppLogin);
    }

    /**
     * 批量删除sysAppLogin
     *
     * @param ownerIds 需要删除的sysAppLogin主键
     * @return 结果
     */
    @Override
    public int deleteSysAppLoginByOwnerIds(Long[] ownerIds)
    {
        return sysAppLoginMapper.deleteSysAppLoginByOwnerIds(ownerIds);
    }

    /**
     * 删除sysAppLogin信息
     *
     * @param ownerId sysAppLogin主键
     * @return 结果
     */
    @Override
    public int deleteSysAppLoginByOwnerId(Long ownerId)
    {
        return sysAppLoginMapper.deleteSysAppLoginByOwnerId(ownerId);
    }

    @Override
    public SysAppLogin selectAppLoginByUserName(String username) {
        return sysAppLoginMapper.selectAppLoginByUserName(username);
    }
}
