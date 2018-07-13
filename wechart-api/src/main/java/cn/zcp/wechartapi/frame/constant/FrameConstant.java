package cn.zcp.wechartapi.frame.constant;

/**
 *
 * Created by Administrator on 2017/11/24.
 */
public interface FrameConstant {
    /*********系统菜单开始*********/
    //导航节点
    String SYS_MENU_LEVEL_NAVIGATE = "00";
    //一级菜单
    String SYS_MENU_LEVEL_ONE = "01";
    //二级菜单
    String SYS_MENU_LEVEL_TWO = "02";
    //三级菜单
    String SYS_MENU_LEVEL_THREE = "03";
    //按钮
    String SYS_MENU_LEVEL_BUTTON = "04";
    //打开方式--在当前窗口打开
    String SYS_MENU_OPEN_STYLE_SELF= "01";
    //打开方式--在新窗口打开
    String SYS_MENU_OPEN_STYLE_BLANK= "02";

    //执行的js
    String DATAGRID_CHECK_ONE = "01";
    String DATAGRID_LEAST_CHECK_ONE = "02";

    //系统菜单的父菜单编号（导航节点的为0)
    String NAVIGATE_SYS_MENU_PARENTNO = "0";
    String NAVIGATE_SYS_MENU_PARENTNAME = "无";
    //左边菜单树请求
    String SYS_MENU_REQUEST_FLAG_LEFT = "1";

    /*********系统菜单结束*********/

}
