package cn.zcp.bgmange.frame.service.impl;


import cn.zcp.bgmange.base.constant.BaseConstant;
import cn.zcp.bgmange.base.pojo.BaseResult;
import cn.zcp.bgmange.base.pojo.ComboBox;
import cn.zcp.bgmange.base.pojo.ComboTree;
import cn.zcp.bgmange.base.pojo.DatagridResult;
import cn.zcp.bgmange.base.service.impl.BaseMyBatisServiceImpl;
import cn.zcp.bgmange.base.utils.BaseUtils;
import cn.zcp.bgmange.frame.constant.FrameConstant;
import cn.zcp.bgmange.frame.dao.ISysMenuDao;
import cn.zcp.bgmange.frame.enums.BaseEnum;
import cn.zcp.bgmange.frame.exception.BaseException;
import cn.zcp.bgmange.frame.pojo.SysMenuCustom;
import cn.zcp.bgmange.frame.pojo.SysMenuQueryVo;
import cn.zcp.bgmange.frame.service.ISysMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 系统菜单业务逻辑接口的实现类
 * Created by Administrator on 2017/11/24.
 */
@Service
public class SysMenuServiceImpl extends BaseMyBatisServiceImpl<SysMenuCustom,SysMenuQueryVo>
		implements ISysMenuService {

	//注入mapper
	@Autowired
	private ISysMenuDao sysMenuDao;
	@Override
	protected BaseResult insertBefore(BaseResult baseResult, SysMenuCustom sysMenuCustom) {
		String sysMenuNo = null;
		SysMenuQueryVo sysMenuQueryVo = new SysMenuQueryVo();
		SysMenuCustom sc = new SysMenuCustom();
		List<SysMenuCustom> sysMenuCustomList;
		switch (sysMenuCustom.getLevelCode()){
			case FrameConstant.SYS_MENU_LEVEL_NAVIGATE:

				sc.setParentNo(sysMenuCustom.getParentNo());
				sc.setLevelCode(FrameConstant.SYS_MENU_LEVEL_NAVIGATE);
				sysMenuQueryVo.setSysMenuCustom(sc);
				sysMenuQueryVo.setOrderBy("createTime DESC");
				sysMenuCustomList = sysMenuDao.queryObjs(sysMenuQueryVo);
				if(!BaseUtils.collectionIsEmpty(sysMenuCustomList)) {
					SysMenuCustom smc = sysMenuCustomList.get(0);
					int temp = Integer.valueOf(smc.getSysMenuNo()) + 1;
					sysMenuNo = String.valueOf(temp);
				}else {
					sysMenuNo = "10";
				}

				break;
			case FrameConstant.SYS_MENU_LEVEL_ONE:

				sc.setParentNo(sysMenuCustom.getParentNo());
				sc.setLevelCode(FrameConstant.SYS_MENU_LEVEL_ONE);
				sysMenuQueryVo.setSysMenuCustom(sc);
				sysMenuQueryVo.setOrderBy("createTime DESC");
				sysMenuCustomList = sysMenuDao.queryObjs(sysMenuQueryVo);
				if(!BaseUtils.collectionIsEmpty(sysMenuCustomList)) {
					SysMenuCustom smc = sysMenuCustomList.get(0);
					String smcNo = smc.getSysMenuNo();
					int temp = Integer.valueOf(smcNo.substring(smcNo.length()-2)) + 1;
					sysMenuNo = sysMenuCustom.getParentNo() + "-" + temp;
				}else {
					sysMenuNo = sysMenuCustom.getParentNo() + "-10";
				}
				break;
			case FrameConstant.SYS_MENU_LEVEL_TWO:

				sc.setParentNo(sysMenuCustom.getParentNo());
				sc.setLevelCode(FrameConstant.SYS_MENU_LEVEL_TWO);
				sysMenuQueryVo.setSysMenuCustom(sc);
				sysMenuQueryVo.setOrderBy("createTime DESC");
				 sysMenuCustomList = sysMenuDao.queryObjs(sysMenuQueryVo);
				if(!BaseUtils.collectionIsEmpty(sysMenuCustomList)) {
					SysMenuCustom smc = sysMenuCustomList.get(0);
					String smcNo = smc.getSysMenuNo();
					int temp = Integer.valueOf(smcNo.substring(smcNo.length()-2)) + 1;
					sysMenuNo = sysMenuCustom.getParentNo() + "-" + temp;
				}else {
					sysMenuNo = sysMenuCustom.getParentNo() + "-10";
				}
				break;
			case FrameConstant.SYS_MENU_LEVEL_THREE:

				sc.setParentNo(sysMenuCustom.getParentNo());
				sc.setLevelCode(FrameConstant.SYS_MENU_LEVEL_THREE);
				sysMenuQueryVo.setSysMenuCustom(sc);
				sysMenuQueryVo.setOrderBy("createTime DESC");
				sysMenuCustomList = sysMenuDao.queryObjs(sysMenuQueryVo);
				if(!BaseUtils.collectionIsEmpty(sysMenuCustomList)) {
					SysMenuCustom smc = sysMenuCustomList.get(0);
					String smcNo = smc.getSysMenuNo();
					int temp = Integer.valueOf(smcNo.substring(smcNo.length()-2)) + 1;
					sysMenuNo = sysMenuCustom.getParentNo() + "-" + temp;
				}else {
					sysMenuNo = sysMenuCustom.getParentNo() + "-10";
				}
				break;
			case FrameConstant.SYS_MENU_LEVEL_BUTTON:

				sc.setParentNo(sysMenuCustom.getParentNo());
				sc.setLevelCode(FrameConstant.SYS_MENU_LEVEL_BUTTON);
				sysMenuQueryVo.setSysMenuCustom(sc);
				sysMenuQueryVo.setOrderBy("createTime DESC");
				sysMenuCustomList = sysMenuDao.queryObjs(sysMenuQueryVo);
				if(!BaseUtils.collectionIsEmpty(sysMenuCustomList)) {
					SysMenuCustom smc = sysMenuCustomList.get(0);
					String smcNo = smc.getSysMenuNo();
					int temp = Integer.valueOf(smcNo.substring(smcNo.length()-2)) + 1;
					sysMenuNo = sysMenuCustom.getParentNo() + "-" + temp;
				}else {
					sysMenuNo = sysMenuCustom.getParentNo() + "-10";
				}
				break;
			default:
				break;

		}

		if(null == sysMenuNo) {
			baseResult.setCode(BaseEnum.ADD_FAIL.getCode());
			baseResult.setMsg(BaseEnum.ADD_FAIL.getMsg());
		}else {
			sysMenuCustom.setSysMenuNo(sysMenuNo);
			sysMenuCustom.setUseStatus(BaseConstant.USE_STATUS_OK);
			sysMenuCustom.setCreateTime(new Date());
		}

		return baseResult;
	}

	@Override
	public List<ComboTree> getComboTrees(SysMenuQueryVo sysMenuQueryVo) {
		List<ComboTree> comboTrees = new ArrayList<>();
		ComboTree ct;
		SysMenuCustom sysMenuCustom;

		List<SysMenuCustom> sysMenuCustoms = sysMenuDao.queryObjs(sysMenuQueryVo);
		String requstFlag = sysMenuQueryVo.getRequestFlag();
		for (int i = 0, len = sysMenuCustoms.size(); i < len; i++){
			sysMenuCustom = sysMenuCustoms.get(i);
			if(null == sysMenuCustom) {
			    continue;
			}

			//判断是否是左菜单请求
			if(FrameConstant.SYS_MENU_REQUEST_FLAG_LEFT.equals(requstFlag)) {
				Subject currentUser = SecurityUtils.getSubject();
				if(!currentUser.isPermitted(sysMenuCustom.getSysMenuNo())) {
				    continue;
				}
				if(FrameConstant.SYS_MENU_LEVEL_BUTTON.equals(sysMenuCustom.getLevelCode())) {
				    continue;
				}
			}

			ct = new ComboTree();
			ct.setId(sysMenuCustom.getSysMenuNo());
			ct.setText(sysMenuCustom.getSysMenuName());
			ct.putAttribute("sysMenuOpenStyle",sysMenuCustom.getSysMenuOpenStyle());
			List<ComboTree> subComboTrees = getSubComboTrees(sysMenuQueryVo,sysMenuCustom.getSysMenuNo());
			if(BaseUtils.collectionIsEmpty(subComboTrees)) {
				ct.putAttribute("sysMenuUrl",sysMenuCustom.getSysMenuUrl());
			}else {
				ct.setState("closed");
				ct.setChildren(subComboTrees);
			}
			comboTrees.add(ct);
		}
		return comboTrees;
	}

	@Override
	public DatagridResult<SysMenuCustom> queryTreeGrid(SysMenuQueryVo sysMenuQueryVo){
		DatagridResult<SysMenuCustom> dr = new DatagridResult<>();
		SysMenuCustom sysMenuCustom = sysMenuQueryVo.getSysMenuCustom();
		if(null != sysMenuCustom && null!= sysMenuCustom.getLevelCode()) {
			if(sysMenuCustom.getLevelCode().isEmpty()) {
				//默认查询全部
				sysMenuCustom.setParentNo(FrameConstant.NAVIGATE_SYS_MENU_PARENTNO);
				sysMenuCustom.setLevelCode(FrameConstant.SYS_MENU_LEVEL_NAVIGATE);
				sysMenuQueryVo.setSysMenuCustom(sysMenuCustom);
			}
		}
		dr.setTotal(sysMenuDao.queryCount(sysMenuQueryVo));
		dr.setRows(getTreeGrid(sysMenuQueryVo));
		return dr;
	}

	private List<ComboTree> getSubComboTrees(SysMenuQueryVo sysMenuQueryVo,String parentNo) {

		SysMenuCustom sc = sysMenuQueryVo.getSysMenuCustom();
		if(null == sc) {
			sc = new SysMenuCustom();
		}
		sc.setParentNo(parentNo);

		sysMenuQueryVo.setSysMenuCustom(sc);

		return getComboTrees(sysMenuQueryVo);
	}

	@Override
	public List<ComboBox> queryComboBox(SysMenuQueryVo entityQueryVo) {
		List<ComboBox> comboBoxes = new ArrayList<>();
		ComboBox comboBox = new ComboBox();
		comboBox.setId(FrameConstant.NAVIGATE_SYS_MENU_PARENTNO);
		comboBox.setText(FrameConstant.NAVIGATE_SYS_MENU_PARENTNAME);
		comboBoxes.add(comboBox);
		comboBoxes.addAll(baseDao.queryComboBox(entityQueryVo));
		return comboBoxes;
	}



	private List<SysMenuCustom> getTreeGrid(SysMenuQueryVo sysMenuQueryVo) {
		SysMenuCustom sysMenuCustom;
		SysMenuCustom smc;
		List<SysMenuCustom> sysMenuCustoms = sysMenuDao.queryObjs(sysMenuQueryVo);
		for (int i = 0, len = sysMenuCustoms.size(); i < len; i++){
			sysMenuCustom = sysMenuCustoms.get(i);
			smc = new SysMenuCustom();
			smc.setParentNo(sysMenuCustom.getSysMenuNo());
//			switch (sysMenuCustom.getLevelCode()){
//				case FrameConstant.SYS_MENU_LEVEL_NAVIGATE:
//					smc.setLevelCode(FrameConstant.SYS_MENU_LEVEL_ONE);
//					break;
//				default:
//					break;
//			}
			SysMenuQueryVo smqv = new SysMenuQueryVo();
			smqv.setSysMenuCustom(smc);
			//获取子菜单
			List<SysMenuCustom> subTreeGrid = getTreeGrid(smqv);

			if(!BaseUtils.collectionIsEmpty(subTreeGrid)) {
				sysMenuCustom.setState("closed");
				sysMenuCustom.setChildren(subTreeGrid);
			}


		}
		return sysMenuCustoms;
	}


	@Override
	@Transactional
	public BaseResult delete(SysMenuCustom sysMenuCustom) {
		int flag = sysMenuDao.deleteSysMenuSysRole(sysMenuCustom.getSysMenuNo());
		if(flag > 0) {
			return super.delete(sysMenuCustom);
		}else {
			throw new BaseException(BaseEnum.DEL_FAIL);
		}
	}

	@Override
	@Transactional
	public BaseResult deleteList(String[] pks) {
		List<SysMenuCustom> sysMenuCustoms =  sysMenuDao.queryObjsBypks(Arrays.asList(pks));
		int len = sysMenuCustoms.size();
		String[] sysMenus = new String[len];
		for (int i = 0; i < len; i++){
			sysMenus[i] = sysMenuCustoms.get(i).getSysMenuNo();
		}
		int flag = sysMenuDao.deleteMulSysMenuSysRole(sysMenus);
		if(flag > 0) {
			return super.deleteList(pks);
		}else {
			throw new BaseException(BaseEnum.DEL_FAIL);
		}
	}

}
