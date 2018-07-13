package cn.zcp.wechartapi.base.pojo;

/**
 * 网格分页实体类
 */
public class DatagridPage {
    private Integer page;//当前页
    private Integer rows;//页大小
    private Long start;//起始索引

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public void setStart(Long start) {
        this.start = start;
    }


    public Integer getPage() {
        return page;
    }

    public Integer getRows() {
        return rows;
    }

    public Long getStart() {
        if(null != page && null != rows) {
            this.start = Long.valueOf((page-1)*rows);
        }
        return start;
    }

}
