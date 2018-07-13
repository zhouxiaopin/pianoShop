package cn.zcp.wechartapi.base.service;


import cn.zcp.wechartapi.base.pojo.BaseResult;
import cn.zcp.wechartapi.base.pojo.ComboBox;
import cn.zcp.wechartapi.base.pojo.DatagridResult;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
public interface IBaseMyBatisService<T,V> {
    //添加单个对象
    BaseResult insert(T entityCustom);

    //修改单个对象
    BaseResult update(T entityCustom);

    //删除单个对象
    BaseResult delete(T entityCustom);

    //通过主键（数组）批量删除数据
    BaseResult deleteList(String[] pks);

    //查询单个对象
    T queryObj(String pk);

    //分页查询数据列表
     DatagridResult<T> queryObjs(V entityQueryVo);

    //获取下拉框
    List<ComboBox> queryComboBox(V entityQueryVo);

}
