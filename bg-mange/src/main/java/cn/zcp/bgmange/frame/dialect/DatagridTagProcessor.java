package cn.zcp.bgmange.frame.dialect;


import cn.zcp.bgmange.base.constant.BaseConstant;
import cn.zcp.bgmange.base.utils.StringUtils;
import cn.zcp.bgmange.frame.constant.FrameConstant;
import cn.zcp.bgmange.frame.dao.ISysMenuDao;
import cn.zcp.bgmange.frame.pojo.SysMenuCustom;
import cn.zcp.bgmange.frame.pojo.SysMenuQueryVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.WebEngineContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.List;

public class DatagridTagProcessor extends BaseTagProcessor {

    private static final String TAG_NAME  = "datagrid";//标签名
    private static final int PRECEDENCE = 10000;//优先级

    protected String id;
    protected String controRequestPath; //控制器请求映射
    protected String dgTitle;           //头标题
    protected String idField;           //主键属性
    protected String columns;           //数据网格的列
    protected String searchParams;      //搜索按钮的参数
    protected String toolbar;           //顶部工具条
    protected boolean singleSelect;       //是否是单选
    protected boolean border;            //是否有边框
    protected String commonQueryDivId;   //查询面板最外层的div的id
    protected boolean page = true;       //是否分页 默认分页
    protected String intiSearchParams;    //初始化请求远程数据的时候发送额外的参数
    protected boolean hasQueryDiv = true;  //是否需要查询div
    protected String buttonParentNo;        //按钮的父编号

    protected String searchArea;        //顶部搜索区域

//    @Autowired
    protected ISysMenuDao sysMenuDao;
//    protected ApplicationPeroptyUtils applicationPeroptyUtils;



    public DatagridTagProcessor(String dialectPrefix) {
        super(
                TemplateMode.HTML, // 此处理器将仅应用于HTML模式
                dialectPrefix,     // 要应用于名称的匹配前缀
                TAG_NAME,          // 标签名称：匹配此名称的特定标签
                true,              // 将标签前缀应用于标签名称
                null,              // 无属性名称：将通过标签名称匹配
                false,             // 没有要应用于属性名称的前缀
                PRECEDENCE);       // 优先(内部方言自己的优先)

    }
    public DatagridTagProcessor(TemplateMode templateMode, String dialectPrefix, String elementName, boolean prefixElementName, String attributeName, boolean prefixAttributeName, int precedence) {
        super(templateMode, dialectPrefix, elementName, prefixElementName, attributeName, prefixAttributeName, precedence);
    }
    /**
     * context 页面上下文
     * tag  标签
     *
     */
    @Override
    protected void doProcess(
            final ITemplateContext context,
            final IProcessableElementTag tag,
            final IElementTagStructureHandler structureHandler) {
        context.getTemplateData();
        /**
         * 获取应用程序上下文。
         */
        ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
//        MatterMapper dao=appCtx.getBean(MatterMapper.class);
        sysMenuDao = appCtx.getBean(ISysMenuDao.class);

//        applicationPeroptyUtils = appCtx.getBean(ApplicationPeroptyUtils.class);

//        String path = applicationPeroptyUtils.getContextPath();
        String path = ((WebEngineContext) context).getRequest().getContextPath();

        setAttributeValue(tag);


        StringBuilder html = new StringBuilder();

        String commonShowDivId = "_t_"+System.nanoTime();
        html.append("<div id='"+commonShowDivId+"' class='commonShowDiv'>");



        /*搜索框开始*/
        //查询按钮id
        String serachBtnId = null;
        if(isHasQueryDiv()) {
            if (StringUtils.isEmpty(commonQueryDivId)) {
                commonQueryDivId = "_t_"+System.nanoTime();
            }
            html.append("<div id='"+commonQueryDivId+"' class='commonQueryDiv'>");
            html.append("<fieldset class='fieldset'>");
//        html.append("<legend class='legend'>信息查询</legend>");
//        html.append("部门编号:&nbsp;<input id='departmentNo' class='easyui-textbox' />&nbsp;");
//        html.append("部门名称:&nbsp;<input id='departmentName' class='easyui-textbox' />");

            searchArea = searchArea.replace("&lt;","<").replace("&gt;",">");
            html.append(searchArea);
/*            JspFragment content = this.getJspBody();
            content.invoke(sw);
            html.append(sw.toString());*/
            //查询按钮id
            serachBtnId = "_t_"+System.nanoTime();
            html.append("<a id='"+serachBtnId+"' class='easyui-linkbutton' data-options=\"iconCls:'icon-search'\">查询</a>");
            html.append("</fieldset>");

            html.append("</div>");

        }

        /*搜索框结束*/

        //网格div的id
        String dataInfoAreaId = "_t_"+System.nanoTime();

        html.append(" <div id='"+dataInfoAreaId+"' style='width:100%;min-width:1024px;height: 100%;'>");
//        html.append(" <div id='"+dataInfoAreaId+"' style='width:100%;min-width:1024px;height: 100%;min-height: 400px;'>");

        if (StringUtils.isEmpty(id)) {
            id = "_t_"+System.nanoTime();
        }
//        html.append("<table id='"+id+"' style='width:100%;min-height: 400px;height: 100%;'");
        html.append("<table id='"+id+"'");
        html.append(" >");
        html.append("</table>");

        html.append("</div>");

        html.append("</div>");

        html.append("<script th:inline=\"none\" type='text/javascript'>");
        html.append("$(function () {");
        //计算高度
        html.append("var height = $('#"+commonShowDivId+"').height() - $('#"+commonQueryDivId+"').height();");
        html.append("$('#"+dataInfoAreaId+"').height(height);");

        //搜索按钮事件
        if(isHasQueryDiv()) {
            html.append("$('#"+serachBtnId+"').on('click',function () {");
            html.append("$('#"+id+"').datagrid('load',");
            html.append(getSearchParams());
            html.append(");");
            html.append("});");
        }


        columns = columns.replace("width:ticc.getFixWidth(","width:ticc.getFixWidth('#"+dataInfoAreaId+"',");
        html.append("ticcEasyUiFrame.intiDatagrid('");
        if(StringUtils.isEmpty(getDgTitle())) {
            html.append("',");
        }else{
            html.append(getDgTitle()+"',");
        }
        String requestPath = path+getControRequestPath();
        html.append("'#"+id+"','"+requestPath+"','"+getIdField()+"',"+isSingleSelect()+","+isBorder()+","+isPage()+","+columns+",");
//        html.append("[]");
//        html.append(getToolbarHtml(id,getIdField(),requestPath));
        html.append(getToolbarHtml(path, id));
        html.append(")");
        html.append("});");
        html.append("</script>");

       /*
        * 指示引擎用指定的模型替换整个元素。
        */
//        structureHandler.replaceWith(model, false);
//        System.out.println(html.toString());
        structureHandler.replaceWith(html.toString(), false);

    }

    //设置属性值
    public void setAttributeValue(final IProcessableElementTag tag){
        /*
        * 从标签读取“m”属性。标签中的这个可选属性将告诉我们需要什么样的数据
        *
        */
        setId("");
        String  id = tag.getAttributeValue("id");
        if(!StringUtils.isEmpty(id)) {
            setId(id);
        }
        String controRequestPath = tag.getAttributeValue("controRequestPath"); //控制器请求映射
        if(!StringUtils.isEmpty(controRequestPath)) {
            setControRequestPath(controRequestPath);
        }
        String dgTitle = tag.getAttributeValue("dgTitle");          //头标题
        if(!StringUtils.isEmpty(dgTitle)) {
            setDgTitle(dgTitle);
        }
        String idField = tag.getAttributeValue("idField");          //主键属性
        if(!StringUtils.isEmpty(idField)) {
            setIdField(idField);
        }
        String columns = tag.getAttributeValue("columns");          //数据网格的列
        if(!StringUtils.isEmpty(columns)) {
            setColumns(columns);
        }
        String searchParams = tag.getAttributeValue("searchParams");      //搜索按钮的参数
        if(!StringUtils.isEmpty(searchParams)) {
            setSearchParams(searchParams);
        }
        String toolbar = tag.getAttributeValue("toolbar");           //顶部工具条
        if(!StringUtils.isEmpty(toolbar)) {
            setToolbar(toolbar);
        }
        String singleSelect = tag.getAttributeValue("singleSelect");       //是否是单选
        if(!StringUtils.isEmpty(singleSelect)) {
            setSingleSelect(Boolean.valueOf(singleSelect));
        }
        String border = tag.getAttributeValue("border");           //是否有边框
        if(!StringUtils.isEmpty(border)) {
            setBorder(Boolean.valueOf(border));
        }
        String commonQueryDivId = tag.getAttributeValue("commonQueryDivId");   //查询面板最外层的div的id
        if(!StringUtils.isEmpty(commonQueryDivId)) {
            setCommonQueryDivId(commonQueryDivId);
        }
        String page = tag.getAttributeValue("page");       //是否分页 默认分页
        if(!StringUtils.isEmpty(page)) {
            setPage(Boolean.valueOf(page));
        }
        String intiSearchParams = tag.getAttributeValue("intiSearchParams");    //初始化请求远程数据的时候发送额外的参数
        if(!StringUtils.isEmpty(intiSearchParams)) {
            setIntiSearchParams(intiSearchParams);
        }
        String hasQueryDiv = tag.getAttributeValue("hasQueryDiv");;  //是否需要查询div
        if(!StringUtils.isEmpty(hasQueryDiv)) {
            setHasQueryDiv(Boolean.valueOf(hasQueryDiv));
        }
        String buttonParentNo = tag.getAttributeValue("buttonParentNo");       //按钮的父编号
        if(!StringUtils.isEmpty(buttonParentNo)) {
            setButtonParentNo(buttonParentNo);
        }
        String searchArea = tag.getAttributeValue("searchArea");       //顶部搜索区域
        if(!StringUtils.isEmpty(searchArea)) {
            setSearchArea(searchArea);
        }
    }

    //获取工具栏
    protected String getToolbarHtml(String contextPath,String elementId){
        //根据父编号查找按钮
        SysMenuCustom sc = new SysMenuCustom();
        if(StringUtils.isEmpty(getButtonParentNo())) {
            return "[]";
        }
        sc.setParentNo(getButtonParentNo());
        sc.setLevelCode(FrameConstant.SYS_MENU_LEVEL_BUTTON);
        sc.setUseStatus(BaseConstant.USE_STATUS_OK);
        SysMenuQueryVo sysMenuQueryVo = new SysMenuQueryVo();
        sysMenuQueryVo.setSysMenuCustom(sc);
        sysMenuQueryVo.setOrderBy("createTime ASC");
        List<SysMenuCustom> sysMenuCustoms = sysMenuDao.queryObjs(sysMenuQueryVo);
        SysMenuCustom sysMenuCustom;
        Subject currentUser = SecurityUtils.getSubject();
        StringBuilder btnHtml = new StringBuilder();
        btnHtml.append("[");
        boolean isHasBtn = false;
        for (int i = 0, len = sysMenuCustoms.size(); i < len; i++){
            sysMenuCustom = sysMenuCustoms.get(i);
            //判断是否有权限
            if(!currentUser.isPermitted(sysMenuCustom.getSysMenuNo())) {
                continue;
            }

            String iconCls = sysMenuCustom.getSysMenuIconCls();
            String menuName = sysMenuCustom.getSysMenuName();
            String href = contextPath+sysMenuCustom.getSysMenuUrl();

            if(isHasBtn) {
                btnHtml.append(",'-',");
            }

            btnHtml.append("{");
            btnHtml.append("iconCls: '"+iconCls+"',");
            btnHtml.append("text: '"+menuName+"',");
            btnHtml.append("handler: function(){");
            String sysMenuExecuteJs = sysMenuCustom.getSysMenuExecuteJs();
            if(null == sysMenuExecuteJs) {
                sysMenuExecuteJs = "";
            }
            String sysMenuOpenStyle = sysMenuCustom.getSysMenuOpenStyle();
            if(null == sysMenuOpenStyle) {
                sysMenuOpenStyle = FrameConstant.SYS_MENU_OPEN_STYLE_SELF;
            }

            switch (sysMenuExecuteJs){
                case FrameConstant.DATAGRID_CHECK_ONE:
                    btnHtml.append("if (!ticcEasyUiFrame.datagridCheckOne('#"+elementId+"')){");
                    btnHtml.append("return;");
                    btnHtml.append("}");
                    if(sysMenuOpenStyle.equals(FrameConstant.SYS_MENU_OPEN_STYLE_SELF)) {
                        btnHtml.append("$('.dialog').dialog({");
                        btnHtml.append(" title: '"+menuName+"',");
                        btnHtml.append("href: '"+href+"',");
                        btnHtml.append("queryParams: {'id':$('#"+elementId+"').datagrid('getChecked')[0]['"+idField+"']},");
                        btnHtml.append("onClose:function () {");
                        btnHtml.append(gridBackCall(elementId));
                        btnHtml.append("}");
                        btnHtml.append("});");
                    }else {
                        btnHtml.append("window.open('"+href+"');");
                    }

                    btnHtml.append("}");
                    btnHtml.append("}");
                    break;
                case FrameConstant.DATAGRID_LEAST_CHECK_ONE:
                    if(sysMenuOpenStyle.equals(FrameConstant.SYS_MENU_OPEN_STYLE_SELF)) {
                        btnHtml.append("var rows = $('#"+elementId+"').datagrid('getChecked');");
                        btnHtml.append("var len = rows.length;");
                        btnHtml.append("if(0 >= len){");
                        btnHtml.append("ticc.alertInfo('至少选择一行');");
                        btnHtml.append("return;");
                        btnHtml.append("}");
                        btnHtml.append("var ids = [];");
                        btnHtml.append("for(var i = 0; i < len; i++){");
                        btnHtml.append("ids.push(rows[i]['"+idField+"']);");
                        btnHtml.append("}");
                        btnHtml.append("ticc.confirm('你确定要删除吗？',function (val) {");
                        btnHtml.append("if (!val){");
                        btnHtml.append("return;");
                        btnHtml.append("}");
                        btnHtml.append("ticc.ajaxRequest('"+href+"',");
                        btnHtml.append("{'ids':ids},function (data) {");
                        btnHtml.append("if(true === data.status){");
                        btnHtml.append(gridBackCall(elementId));
                        btnHtml.append("}else {");
                        btnHtml.append("ticc.alertError(data.msg);");
                        btnHtml.append(" }");
                        btnHtml.append("});");
                        btnHtml.append("});");
                    }else {
                        btnHtml.append("window.open('"+href+"');");
                    }
                    btnHtml.append("}");
                    btnHtml.append("}");
                    break;
                default:
                    if(sysMenuOpenStyle.equals(FrameConstant.SYS_MENU_OPEN_STYLE_SELF)) {
                        btnHtml.append("$('.dialog').dialog({");
                        btnHtml.append("title: '添加',");
                        btnHtml.append("href: '"+href+"',");
                        btnHtml.append("onClose:function () {");
                        btnHtml.append(gridBackCall(elementId));
                        btnHtml.append("}");
                        btnHtml.append("});");
                    }else {
                        btnHtml.append("window.open('"+href+"');");
                    }

                    btnHtml.append("}");
                    btnHtml.append("}");
                    break;
            }

            isHasBtn = true;

        }
        btnHtml.append("]");
        return btnHtml.toString();
    }

    //添加按钮
    protected String getAddBtn(String elementId,String idField,String controRequestPath){
        StringBuilder html = new StringBuilder();
        html.append("{");
        html.append("iconCls: 'icon-add',");
        html.append("text: '添加',");
        html.append("handler: function(){");
        html.append("$('.dialog').dialog({");
        html.append("title: '添加',");
        html.append("href: '"+controRequestPath+"/initAdd.action',");
        html.append("onClose:function () {");
//        html.append("$('#"+elementId+"').datagrid('reload');");
        html.append(gridBackCall(elementId));
        html.append("}");
        html.append("});");
        html.append("}");
        html.append("}");

        return html.toString();
    }
    //修改按钮
    protected String getUpdateBtn(String elementId,String idField,String controRequestPath){
        StringBuilder html = new StringBuilder();
        html.append("{");
        html.append("iconCls: 'icon-edit',");
        html.append("text: '修改',");
        html.append("handler: function(){");
        html.append("if (!ticcEasyUiFrame.datagridCheckOne('#"+elementId+"')){");
        html.append("return;");
        html.append("}");
        html.append("$('.dialog').dialog({");
        html.append(" title: '修改',");
        html.append("href: '"+controRequestPath+"/initUpdate.action',");
        html.append("queryParams: {'id':$('#"+elementId+"').datagrid('getChecked')[0]['"+idField+"']},");
        html.append("onClose:function () {");
//        html.append("$('#"+elementId+"').datagrid('reload');");
        html.append(gridBackCall(elementId));
        html.append("}");
        html.append("});");
        html.append("}");
        html.append("}");
        return html.toString();
    }
    //删除按钮
    protected String getDeleteBtn(String elementId,String idField,String controRequestPath){
        StringBuilder html = new StringBuilder();
        html.append("{");
        html.append("iconCls: 'icon-remove',");
        html.append("text: '删除',");
        html.append("handler: function(){");
        html.append("var rows = $('#"+elementId+"').datagrid('getChecked');");
        html.append("var len = rows.length;");
        html.append("if(0 >= len){");
        html.append("ticc.alertInfo('至少选择一行');");
        html.append("return;");
        html.append("}");
        html.append("var ids = [];");
        html.append("for(var i = 0; i < len; i++){");
        html.append("ids.push(rows[i]['"+idField+"']);");
        html.append("}");
        html.append("ticc.confirm('你确定要删除吗？',function (val) {");
        html.append("if (!val){");
        html.append("return;");
        html.append("}");
        html.append("ticc.ajaxRequest('"+controRequestPath+"/delete.action',");
        html.append("{'ids':ids},function (data) {");
        html.append("if(true === data.status){");
//        html.append("$('#"+elementId+"').datagrid('reload');");
        html.append(gridBackCall(elementId));
        html.append("}else {");
        html.append("ticc.alertError('提示',data.msg,'error');");
        html.append(" }");
        html.append("});");
        html.append("});");
        html.append("}");
        html.append("}");
        return html.toString();
    }
    //查看详情按钮
    protected String getQueryBtn(String elementId,String idField,String controRequestPath){
        StringBuilder html = new StringBuilder();
        html.append("{");
        html.append("iconCls: 'icon-search',");
        html.append("text: '查看',");
        html.append("handler: function(){");
        html.append("if (!ticcEasyUiFrame.datagridCheckOne('#"+elementId+"')){");
        html.append("return;");
        html.append("}");
        html.append("$('.dialog').dialog({");
        html.append("title: '查看',");
        html.append("href: '"+controRequestPath+"/initQueryDetail.action',");
        html.append("queryParams: {'id':$('#"+elementId+"').datagrid('getChecked')[0]['"+idField+"']},");
        html.append("onClose:function () {");
//        html.append("$('#"+elementId+"').datagrid('reload');");
        html.append(gridBackCall(elementId));
        html.append("}");
        html.append("});");
        html.append("}");
        html.append("}");
        return html.toString();
    }

    //操作表格后的回调方法
    protected String gridBackCall(String elementId){
        return "$('#"+elementId+"').datagrid('reload');";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getControRequestPath() {
        return controRequestPath;
    }

    public void setControRequestPath(String controRequestPath) {
        this.controRequestPath = controRequestPath;
    }

    public String getDgTitle() {
        return dgTitle;
    }

    public void setDgTitle(String dgTitle) {
        this.dgTitle = dgTitle;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(String searchParams) {
        this.searchParams = searchParams;
    }

    public String getToolbar() {
        return toolbar;
    }

    public void setToolbar(String toolbar) {
        this.toolbar = toolbar;
    }

    public boolean isSingleSelect() {
        return singleSelect;
    }

    public void setSingleSelect(boolean singleSelect) {
        this.singleSelect = singleSelect;
    }

    public boolean isBorder() {
        return border;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

    public String getCommonQueryDivId() {
        return commonQueryDivId;
    }

    public void setCommonQueryDivId(String commonQueryDivId) {
        this.commonQueryDivId = commonQueryDivId;
    }

    public boolean isPage() {
        return page;
    }

    public void setPage(boolean page) {
        this.page = page;
    }

    public String getIntiSearchParams() {
        return intiSearchParams;
    }

    public void setIntiSearchParams(String intiSearchParams) {
        this.intiSearchParams = intiSearchParams;
    }

    public boolean isHasQueryDiv() {
        return hasQueryDiv;
    }

    public void setHasQueryDiv(boolean hasQueryDiv) {
        this.hasQueryDiv = hasQueryDiv;
    }

    public String getButtonParentNo() {
        return buttonParentNo;
    }

    public void setButtonParentNo(String buttonParentNo) {
        this.buttonParentNo = buttonParentNo;
    }

    public String getSearchArea() {
        return searchArea;
    }

    public void setSearchArea(String searchArea) {
        this.searchArea = searchArea;
    }
}