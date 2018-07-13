package cn.zcp.bgmange.base.pojo;

/**
 * 基本查询包装类
 * Created by Administrator on 2017/10/11.
 */
public class BaseQueryVo extends DatagridPage{
    //排序
    private String orderBy;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
