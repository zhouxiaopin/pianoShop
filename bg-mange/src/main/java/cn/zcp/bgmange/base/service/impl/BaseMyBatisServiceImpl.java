package cn.zcp.bgmange.base.service.impl;


import cn.zcp.bgmange.base.pojo.ComboBox;
import cn.zcp.bgmange.base.dao.IBaseDao;
import cn.zcp.bgmange.base.pojo.BaseResult;
import cn.zcp.bgmange.base.pojo.DatagridResult;
import cn.zcp.bgmange.base.service.IBaseMyBatisService;
import cn.zcp.bgmange.base.utils.ResultUtils;
import cn.zcp.bgmange.frame.enums.BaseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
public class BaseMyBatisServiceImpl<T,V> implements IBaseMyBatisService<T,V> {
    @Autowired
    protected IBaseDao<T,V> baseDao;

    protected BaseResult insertBefore(BaseResult baseResult, T t){
        return baseResult;
    }

    protected BaseResult updateBefore(BaseResult baseResult,T t){
        return baseResult;
    }

    @Override
    @Transactional
    public BaseResult insert(T entityCustom){
        BaseResult baseResult = new BaseResult();
        baseResult = insertBefore(baseResult,entityCustom);
        if(!baseResult.isStatus()) {
            return baseResult;
        }
        int flag = baseDao.insert(entityCustom);
        if(flag >= 1) {
            return ResultUtils.baseResult(BaseEnum.ADD_SUCCESS);
        }else{
            return ResultUtils.baseResult(BaseEnum.ADD_FAIL);
        }
//        BaseResult baseResult = new BaseResult();
//        try {
//            baseResult = insertBefore(baseResult,entityCustom);
//            if(!baseResult.isStatus()) {
//                return baseResult;
//            }
//            int flag = baseMapper.insert(entityCustom);
//            if(flag >= 1) {
//                baseResult.setCode(BaseConstant.ADD_SUCCESS_CODE);
//                baseResult.setMsg(BaseConstant.ADD_SUCCESS_MSG);
//
//            }else{
//                baseResult.setCode(BaseConstant.FAIL_CODE);
//                baseResult.setMsg(BaseConstant.ADD_FAIL_MSG);
//            }
//        }catch (Exception e){
//            baseResult.setCode(BaseConstant.EXCEPTION_CODE);
//            baseResult.setMsg(BaseConstant.EXCEPTION_MSG);
//        }
//
//        return baseResult;
    }

    @Override
    @Transactional
    public BaseResult update(T entityCustom){
        BaseResult baseResult = new BaseResult();
        baseResult = updateBefore(baseResult,entityCustom);
        if(!baseResult.isStatus()) {
            return baseResult;
        }
        int flag = baseDao.update(entityCustom);
        if(flag >= 1) {
            return ResultUtils.baseResult(BaseEnum.MDF_SUCCESS);

        }else{
            return ResultUtils.baseResult(BaseEnum.MDF_FAIL);
        }
//        BaseResult baseResult = new BaseResult();
//        try {
//            baseResult = updateBefore(baseResult,entityCustom);
//            if(!baseResult.isStatus()) {
//                return baseResult;
//            }
//            int flag = baseMapper.update(entityCustom);
//            if(flag >= 1) {
//                baseResult.setCode(BaseConstant.MDF_SUCCESS_CODE);
//                baseResult.setMsg(BaseConstant.MDF_SUCCESS_MSG);
//
//            }else{
//                baseResult.setCode(BaseConstant.FAIL_CODE);
//                baseResult.setMsg(BaseConstant.MDF_FAIL_MSG);
//            }
//        }catch (Exception e){
//            baseResult.setCode(BaseConstant.EXCEPTION_CODE);
//            baseResult.setMsg(BaseConstant.EXCEPTION_MSG);
//        }
//        return baseResult;
    }

    @Override
    @Transactional
    public BaseResult delete(T entityCustom){
        int flag = baseDao.delete(entityCustom);
        return deleteAfter(flag);

//        BaseResult baseResult = null;
//        try {
//            int flag = baseMapper.delete(entityCustom);
//            baseResult = deleteAfter(flag);
//        }catch (Exception e){
//            if(null == baseResult) {
//                baseResult = new BaseResult();
//            }
//            baseResult.setCode(BaseConstant.EXCEPTION_CODE);
//            baseResult.setMsg(BaseConstant.EXCEPTION_MSG);
//        }
//        return baseResult;
    }

    @Override
    @Transactional
    public BaseResult deleteList(String[] pks){
        int flag = baseDao.deleteList(pks);
        return deleteAfter(flag);

//        BaseResult baseResult = null;
//        try {
//            int flag = baseMapper.deleteList(pks);
//            baseResult = deleteAfter(flag);
//        }catch (Exception e){
//            if(null == baseResult) {
//                baseResult = new BaseResult();
//            }
//            baseResult.setCode(BaseConstant.EXCEPTION_CODE);
//            baseResult.setMsg(BaseConstant.EXCEPTION_MSG);
//        }
//        return baseResult;
    }

    @Override
    public T queryObj(String pk){
        return baseDao.queryObj(pk);
    }

    @Override
    public DatagridResult<T> queryObjs(V entityQueryVo){
        DatagridResult<T> dr = new DatagridResult<>();
        dr.setTotal(baseDao.queryCount(entityQueryVo));
        dr.setRows(baseDao.queryObjs(entityQueryVo));
        return dr;
    }

    @Override
    public List<ComboBox> queryComboBox(V entityQueryVo) {
        List<ComboBox> comboBoxes;
        try {
            comboBoxes = baseDao.queryComboBox(entityQueryVo);
        }catch (Exception e){
            e.printStackTrace();
            comboBoxes = new ArrayList<>();
        }
        return comboBoxes;
    }

    private BaseResult deleteAfter(int flag){
        if(flag >= 1) {
            return ResultUtils.baseResult(BaseEnum.DEL_SUCCESS);
        }else{
            return ResultUtils.baseResult(BaseEnum.DEL_FAIL);
        }
//        BaseResult br = new BaseResult();
//        if(flag >= 1) {
//            br.setCode(BaseConstant.DEL_SUCCESS_CODE);
//            br.setMsg(BaseConstant.DEL_SUCCESS_MSG);
//
//
//        }else{
//            br.setCode(BaseConstant.FAIL_CODE);
//            br.setMsg(BaseConstant.DEL_FAIL_MSG);
//        }
//        return br;
    }
}
