package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysAppLogin;

/**
 * sysAppLoginService接口
 *
 * @author wxq
 * @date 2024-03-30
 */
public interface ISysAppLoginService
{
    /**
     * 查询sysAppLogin
     *
     * @param ownerId sysAppLogin主键
     * @return sysAppLogin
     */
    public SysAppLogin selectSysAppLoginByOwnerId(Long ownerId);

    /**
     * 查询sysAppLogin列表
     *
     * @param sysAppLogin sysAppLogin
     * @return sysAppLogin集合
     */
    public List<SysAppLogin> selectSysAppLoginList(SysAppLogin sysAppLogin);

    /**
     * 新增sysAppLogin
     *
     * @param sysAppLogin sysAppLogin
     * @return 结果
     */
    public int insertSysAppLogin(SysAppLogin sysAppLogin);

    /**
     * 修改sysAppLogin
     *
     * @param sysAppLogin sysAppLogin
     * @return 结果
     */
    public int updateSysAppLogin(SysAppLogin sysAppLogin);

    /**
     * 批量删除sysAppLogin
     *
     * @param ownerIds 需要删除的sysAppLogin主键集合
     * @return 结果
     */
    public int deleteSysAppLoginByOwnerIds(Long[] ownerIds);

    /**
     * 删除sysAppLogin信息
     *
     * @param ownerId sysAppLogin主键
     * @return 结果
     */
    public int deleteSysAppLoginByOwnerId(Long ownerId);

    /**
     * 查询sysAppLogin信息
     *
     * @param username sysAppLogin主键
     * @return 结果
     */
    public SysAppLogin selectAppLoginByUserName(String username);
}
