
package cn.zcp.bgmange.frame.service.impl;


import cn.zcp.bgmange.base.constant.BaseConstant;
import cn.zcp.bgmange.base.pojo.BaseResult;
import cn.zcp.bgmange.base.service.impl.BaseMyBatisServiceImpl;
import cn.zcp.bgmange.base.utils.BaseUtils;
import cn.zcp.bgmange.base.utils.ResultUtils;
import cn.zcp.bgmange.frame.dao.IAdminDao;
import cn.zcp.bgmange.frame.enums.BaseEnum;
import cn.zcp.bgmange.frame.exception.BaseException;
import cn.zcp.bgmange.frame.pojo.AdminCustom;
import cn.zcp.bgmange.frame.pojo.AdminQueryVo;
import cn.zcp.bgmange.frame.pojo.AdminRole;
import cn.zcp.bgmange.frame.service.IAdminService;
import cn.zcp.bgmange.frame.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统菜单信息的业务逻辑实现类
 * @author pin
 *
 */
@Service
public class AdminServiceImpl extends BaseMyBatisServiceImpl<AdminCustom,AdminQueryVo>
		implements IAdminService {
	
	//注入mapper
	@Autowired
	private IAdminDao adminDao;

	@Override
	protected BaseResult insertBefore(BaseResult baseResult, AdminCustom adminCustom) {

		adminCustom.setPassword(ShiroUtils.getMd5Pwd(null,adminCustom.getPassword()));
		adminCustom.setUseStatus(BaseConstant.USE_STATUS_OK);
		adminCustom.setCreateTime(new Date());
		return baseResult;
	}

	@Override
	public AdminCustom loginVerify(String account, String password) {
		AdminCustom adminCustom = new AdminCustom();
		adminCustom.setAccount(account);
		AdminQueryVo adminQueryVo = new AdminQueryVo();
		adminQueryVo.setAdminCustom(adminCustom);
		List<AdminCustom> adminCustoms = adminDao.queryObjs(adminQueryVo);
		AdminCustom ac = null;
		if(BaseUtils.collectionIsEmpty(adminCustoms)) {
		    throw new BaseException(BaseEnum.LOGIN_ADMIN_NO_EXIST);
		}
		for (int i = 0, len = adminCustoms.size(); i < len; i++){
			ac = adminCustoms.get(i);
			if(account.equals(ac.getAccount())) {
			    if(password.equals(ac.getPassword())) {
			    	if(BaseConstant.USE_STATUS_OK.equals(ac.getUseStatus())) {
						break;
			    	}else{
						throw new BaseException(BaseEnum.LOGIN_ADMIN_NO_USE);
					}
			    }else{
					throw new BaseException(BaseEnum.LOGIN_PWD_FAIL);
				}
			}
		}
		return ac;
	}

	@Override
	public BaseResult insertAdminRole(AdminCustom adminCustom) {


		//1.清除之前的记录
		int flag = 1;
		if(!BaseUtils.collectionIsEmpty(adminDao.queryAdminRole(adminCustom.getId().toString()))) {
			flag = adminDao.deleteAdminRole(adminCustom.getId().toString());
		}

		if(flag >= 1) {
			//2.插入现在的记录
			List<AdminRole> adminRoles = new ArrayList<>();
			String adminId = adminCustom.getId().toString();
			AdminRole adminRole;
			List<String> roleNos = adminCustom.getRoleNos();
			for (int i = 0, len = roleNos.size(); i < len; i++){
				adminRole = new AdminRole();
				adminRole.setAdminId(adminId);
				adminRole.setRoleNo(roleNos.get(i));
				adminRoles.add(adminRole);
			}
			flag = adminDao.insertAdminRole(adminRoles);

		}

		if(flag >= 1) {
			return ResultUtils.baseResult(BaseEnum.ADD_SUCCESS);
		}else{
			return ResultUtils.baseResult(BaseEnum.ADD_FAIL);
		}

	}

	@Override
	@Transactional
	public BaseResult delete(AdminCustom adminCustom) {
		int flag = adminDao.deleteAdminRole(adminCustom.getId().toString());
		if(flag > 0) {
			return super.delete(adminCustom);
		}else {
			throw new BaseException(BaseEnum.DEL_FAIL);
		}
	}

	@Override
	@Transactional
	public BaseResult deleteList(String[] pks) {
		int flag = adminDao.deleteMulAdminSysRole(pks);
		if(flag > 0) {
			return super.deleteList(pks);
		}else {
			throw new BaseException(BaseEnum.DEL_FAIL);
		}
	}
}
