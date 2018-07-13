package cn.zcp.wechartapi.base.dao;

import cn.zcp.wechartapi.base.pojo.ComboBox;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 基本mapper接口
 * @param <T>   实体扩展类
 * @param <V>   实体包装类
 */
@Component
public interface IBaseDao<T,V> {

    //添加单个对象
    int insert(T entityCustom);

    //修改单个对象
    int update(T entityCustom);

    //删除单个对象
    int delete(T entityCustom);

    //通过主键（数组）批量删除数据
    int deleteList(String[] pks);

    //查询单个对象
    T queryObj(String pk);

    //分页查询数据列表
    List<T> queryObjs(V entityQueryVo);

    //分页查询，返回总记录数
    Long queryCount(V entityQueryVo);

    //获取下拉框
    List<ComboBox> queryComboBox(V entityQueryVo);

}
