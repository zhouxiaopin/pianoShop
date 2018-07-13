package cn.zcp.bgmange.frame.service.impl;


import cn.zcp.bgmange.base.constant.BaseConstant;
import cn.zcp.bgmange.base.pojo.BaseResult;
import cn.zcp.bgmange.base.pojo.ComboTree;
import cn.zcp.bgmange.base.service.impl.BaseMyBatisServiceImpl;
import cn.zcp.bgmange.base.utils.BaseUtils;
import cn.zcp.bgmange.base.utils.ResultUtils;
import cn.zcp.bgmange.frame.dao.ISysRoleDao;
import cn.zcp.bgmange.frame.enums.BaseEnum;
import cn.zcp.bgmange.frame.exception.BaseException;
import cn.zcp.bgmange.frame.pojo.SysMenuSysRole;
import cn.zcp.bgmange.frame.pojo.SysRoleCustom;
import cn.zcp.bgmange.frame.pojo.SysRoleQueryVo;
import cn.zcp.bgmange.frame.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 系统角色业务逻辑接口的实现类
 * Created by Administrator on 2017/11/28.
 */
@Service
public class SysRoleServiceImpl extends BaseMyBatisServiceImpl<SysRoleCustom,SysRoleQueryVo>
        implements ISysRoleService {

    //注入mapper
    @Autowired
    private ISysRoleDao sysRoleDao;

    @Override
    protected BaseResult insertBefore(BaseResult baseResult, SysRoleCustom sysRoleCustom) {
        StringBuilder roleNo = new StringBuilder();
        List<SysRoleCustom> sysRoleCustoms = sysRoleDao.queryObjs(null);
        if(BaseUtils.collectionIsEmpty(sysRoleCustoms)) {
            roleNo.append("00000001");
        }else {
            SysRoleCustom src = sysRoleCustoms.get(0);
            int tempRoleNo = Integer.parseInt(src.getRoleNo());
            tempRoleNo++;
            roleNo.append(tempRoleNo);
            while (roleNo.length() < 8){
                roleNo.insert(0,"0");
            }
        }


        sysRoleCustom.setRoleNo(roleNo.toString());

        sysRoleCustom.setCreateTime(new Date());
        sysRoleCustom.setUseStatus(BaseConstant.USE_STATUS_OK);
        return baseResult;
    }


    @Override
    public List<ComboTree> getComboTree(SysRoleQueryVo sysRoleQueryVo) {
        List<SysRoleCustom> roles = sysRoleDao.queryObjs(sysRoleQueryVo);
        SysRoleCustom roleCustom;
        List<ComboTree> comboTrees = new ArrayList<>();
        ComboTree ct;
        for (int i = 0, len = roles.size(); i < len; i++){
            roleCustom = roles.get(i);
            ct = new ComboTree();
            ct.setId(roleCustom.getRoleNo());
            ct.setText(roleCustom.getRoleName());
            comboTrees.add(ct);
        }
        return comboTrees;
    }

    @Override
    @Transactional
    public BaseResult insertSysMenuSysRole(SysRoleCustom sysRoleCustom) {
        //1.清除之前的记录
        int flag = 1;
        String roleNo = sysRoleCustom.getRoleNo();
        if(!BaseUtils.collectionIsEmpty(sysRoleDao.querySysMenuSysRole(roleNo))) {
            flag = sysRoleDao.deleteSysMenuSysRole(roleNo);
        }
        if(flag >= 1) {
            //2.插入现在的记录
            List<SysMenuSysRole> sysMenuSysRoles = new ArrayList<>();
            SysMenuSysRole sysMenuSysRole;
            List<String> sysMenuNo = sysRoleCustom.getSysMenuNos();
            for (int i = 0, len = sysMenuNo.size(); i < len; i++){
                sysMenuSysRole = new SysMenuSysRole();
                sysMenuSysRole.setSysRoleNo(roleNo);
                sysMenuSysRole.setSysMenuNo(sysMenuNo.get(i));
                sysMenuSysRoles.add(sysMenuSysRole);
            }
            flag = sysRoleDao.insertSysMenuSysRole(sysMenuSysRoles);
        }
        if(flag >= 1) {
            return ResultUtils.baseResult(BaseEnum.ADD_SUCCESS);
        }else{
            return ResultUtils.baseResult(BaseEnum.ADD_FAIL);
        }
    }

    @Override
    @Transactional
    public BaseResult delete(SysRoleCustom sysRoleCustom) {
        String roleNo = sysRoleCustom.getRoleNo();
        int flag1 = sysRoleDao.deleteSysMenuSysRole(roleNo);
        int flag2 = sysRoleDao.deleteAdminSysRole(roleNo);
        if(flag1 > 0 && flag2 > 0) {
            return super.delete(sysRoleCustom);
        }else {
            throw new BaseException(BaseEnum.DEL_FAIL);
        }
    }

    @Override
    @Transactional
    public BaseResult deleteList(String[] pks) {
        List<SysRoleCustom> sysRoleCustoms =  sysRoleDao.queryObjsBypks(Arrays.asList(pks));
        int len = sysRoleCustoms.size();
        String[] roleNos = new String[len];
        for (int i = 0; i < len; i++){
            roleNos[i] = sysRoleCustoms.get(i).getRoleNo();
        }
        int flag1 = sysRoleDao.deleteMulSysMenuSysRole(roleNos);
        int flag2 = sysRoleDao.deleteMulAdminSysRole(roleNos);
        if(flag1 > 0 && flag2 > 0) {
            return super.deleteList(pks);
        }else {
            throw new BaseException(BaseEnum.DEL_FAIL);
        }
    }
}
