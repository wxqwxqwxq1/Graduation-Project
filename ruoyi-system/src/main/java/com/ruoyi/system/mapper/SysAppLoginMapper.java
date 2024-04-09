package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysAppLogin;

/**
 * sysAppLoginMapper接口
 *
 * @author wxq
 * @date 2024-03-30
 */
public interface SysAppLoginMapper
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
     * 删除sysAppLogin
     *
     * @param ownerId sysAppLogin主键
     * @return 结果
     */
    public int deleteSysAppLoginByOwnerId(Long ownerId);

    /**
     * 批量删除sysAppLogin
     *
     * @param ownerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysAppLoginByOwnerIds(Long[] ownerIds);

    /**
     * 根据username查询sysAppLogin
     *
     * @param username 需要删除的数据主键集合
     * @return 结果
     */
    public SysAppLogin selectAppLoginByUserName(String username);
}
