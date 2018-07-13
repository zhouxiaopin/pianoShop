package cn.zcp.wechartapi.base.pojo;

import java.util.Collection;

/**
 * 分页查询返回实体类
 * @param <T>   实体
 */
public class DatagridResult<T> {
    private Long total;
    private Collection<T> rows;

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setRows(Collection<T> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public Collection<T> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return "DatagridResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}

