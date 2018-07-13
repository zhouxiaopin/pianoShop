package cn.zcp.bgmange.frame.dialect;


import cn.zcp.bgmange.base.utils.StringUtils;
import cn.zcp.bgmange.frame.dao.ISysMenuDao;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.WebEngineContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.templatemode.TemplateMode;

public class TreegridTagProcessor extends DatagridTagProcessor {

    private static final String TAG_NAME  = "treegrid";//标签名
    private static final int PRECEDENCE = 10000;//优先级

    private String treeField;//树节点字段



    public TreegridTagProcessor(String dialectPrefix) {
        super(
                TemplateMode.HTML, // 此处理器将仅应用于HTML模式
                dialectPrefix,     // 要应用于名称的匹配前缀
                TAG_NAME,          // 标签名称：匹配此名称的特定标签
                true,              // 将标签前缀应用于标签名称
                null,              // 无属性名称：将通过标签名称匹配
                false,             // 没有要应用于属性名称的前缀
                PRECEDENCE);       // 优先(内部方言自己的优先)

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
        /**
         * 获取应用程序上下文。
         */
        ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
//        MatterMapper dao=appCtx.getBean(MatterMapper.class);
        sysMenuDao = appCtx.getBean(ISysMenuDao.class);
//        applicationPeroptyUtils = appCtx.getBean(ApplicationPeroptyUtils.class);

//        String path = applicationPeroptyUtils.getBaseUrl();
        String path = ((WebEngineContext) context).getRequest().getContextPath();
        setAttributeValue(tag);


        StringBuilder html = new StringBuilder();

        String commonShowDivId = "_t_"+System.nanoTime();
        html.append("<div id='"+commonShowDivId+"' class='commonShowDiv'>");


        //搜索框
        if (StringUtils.isEmpty(commonQueryDivId)) {
            commonQueryDivId = "_t_"+System.nanoTime();
        }
        html.append("<div id='"+commonQueryDivId+"' class='commonQueryDiv'>");
        html.append("<fieldset class='fieldset'>");
/*        html.append("<legend class='legend'>信息查询</legend>");
        html.append("部门编号:&nbsp;<input id='departmentNo' class='easyui-textbox' />&nbsp;");
        html.append("部门名称:&nbsp;<input id='departmentName' class='easyui-textbox' />");*/

        searchArea = searchArea.replace("&lt;","<").replace("&gt;",">");
        html.append(searchArea);

        //查询按钮id
        String serachBtnId = "_t_"+System.nanoTime();
        html.append("<a id='"+serachBtnId+"' class='easyui-linkbutton' data-options=\"iconCls:'icon-search'\">查询</a>");
        html.append("</fieldset>");




        html.append("</div>");

        //网格div的id
        String dataInfoAreaId = "_t_"+System.nanoTime();

        html.append(" <div id='"+dataInfoAreaId+"' style='width:100%;min-width:1024px;height: 100%;'>");

        if (StringUtils.isEmpty(id)) {
            id = "_t_"+System.nanoTime();
        }
        html.append("<table id='"+id+"' ");
        html.append(" >");
        html.append("</table>");

        html.append("</div>");

        html.append("</div>");

        html.append("<script th:inline='none' type='text/javascript'>");
        html.append("$(function () {");
        //计算高度
        html.append("var height = $('#"+commonShowDivId+"').height() - $('#"+commonQueryDivId+"').height();");
        html.append("$('#"+dataInfoAreaId+"').height(height);");

        //搜索按钮事件
        html.append("$('#"+serachBtnId+"').on('click',function () {");
        html.append("$('#"+id+"').treegrid('load',");
        html.append(getSearchParams());
        html.append(");");
        html.append("});");

        columns = columns.replace("width:ticc.getFixWidth(","width:ticc.getFixWidth('#"+dataInfoAreaId+"',");
        html.append("ticcEasyUiFrame.intiTreegrid('");
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
        html.append(",'"+getTreeField()+"',"+getIntiSearchParams());
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

    @Override
    public void setAttributeValue(IProcessableElementTag tag) {
        super.setAttributeValue(tag);
        String  treeField = tag.getAttributeValue("treeField");
        if(!StringUtils.isEmpty(treeField)) {
            setTreeField(treeField);
        }
    }

    @Override
    protected String gridBackCall(String elementId) {
        return "$('#"+elementId+"').treegrid('reload');";
    }

    public String getTreeField() {
        return treeField;
    }

    public void setTreeField(String treeField) {
        this.treeField = treeField;
    }
}