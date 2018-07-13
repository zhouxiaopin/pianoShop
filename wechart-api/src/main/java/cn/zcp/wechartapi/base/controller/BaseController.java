package cn.zcp.wechartapi.base.controller;


import cn.zcp.wechartapi.base.constant.BaseConstant;
import cn.zcp.wechartapi.frame.enums.BaseEnum;
import cn.zcp.wechartapi.frame.exception.BaseException;
import cn.zcp.wechartapi.base.pojo.BaseResult;
import cn.zcp.wechartapi.base.pojo.ComboBox;
import cn.zcp.wechartapi.base.pojo.DatagridResult;
import cn.zcp.wechartapi.base.service.IBaseMyBatisService;
import cn.zcp.wechartapi.base.utils.JSONUtils;
import cn.zcp.wechartapi.base.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public abstract class BaseController<T,V> {
    @Autowired
    private IBaseMyBatisService<T,V> baseMyBatisService;

//    protected abstract String getJsp();
//    protected abstract String getId(T t);
//    protected abstract void addBefore(T t);
//    protected abstract void updateBefore(T t,T param);

    protected static final String QUERY_OPRT = "query";
    protected static final String ADD_OPRT = "add";
    protected static final String UPDATE_OPRT = "update";
    protected static final String QUERYDETAIL_OPRT = "queryDetail";

    protected String getJsp(){return null;}
    protected String getId(T t){return null;}
    protected BaseResult addBefore(T t){return null;}
    protected BaseResult updateBefore(T t,T param){return null;}


    @RequestMapping(value = "/initQuery", method = RequestMethod.POST)
    public String initQuery(Model model) throws Exception{
        model.addAttribute("oprt",QUERY_OPRT);
        //重定向到list
//        return "redirect:list.action";
        return jsp();
    }
    @RequestMapping(value = "/initAdd", method = RequestMethod.POST)
    public String initAdd(Model model, T t) throws Exception{
        try {
            model.addAttribute("obj",t);
            model.addAttribute("oprt",ADD_OPRT);
            model.addAttribute("retMsg", BaseConstant.SUCCESS_MSG);
        }catch (Exception e){
            model.addAttribute("retMsg",BaseConstant.FAIL_MSG);
        }
        return jsp();
    }
    @RequestMapping(value = "/initUpdate", method = RequestMethod.POST)
    public String initUpdate(Model model, String id) throws Exception{
        model.addAttribute("oprt",UPDATE_OPRT);
        try {
            init(model,id);
        }catch (Exception e){
            model.addAttribute("retMsg",BaseConstant.FAIL_MSG);
        }
        return jsp();
    }
    @RequestMapping(value = "/initQueryDetail", method = RequestMethod.POST)
    public String queryDetail(Model model, String id) throws Exception{
        model.addAttribute("oprt",QUERYDETAIL_OPRT);
        try {
            init(model,id);
        }catch (Exception e){
            model.addAttribute("retMsg",BaseConstant.FAIL_MSG);
        }
        return jsp();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult add(Model model, T t) throws Exception{
        BaseResult baseResult = addBefore(t);
        if(null == baseResult||baseResult.isStatus()) {
            return baseMyBatisService.insert(t);
        }else {
            return baseResult;
        }

//        BaseResult baseResult = null;
//        try {
//            addBefore(t);
//            baseResult = baseMyBatisService.insert(t);
//        }catch (Exception e){
//            if(null == baseResult) {
//                baseResult = new BaseResult();
//            }
//            baseResult.setCode(BaseConstant.EXCEPTION_CODE);
//            baseResult.setMsg(BaseConstant.EXCEPTION_MSG);
//        }
//        return baseResult;
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult update(Model model, T t) throws Exception{
        BaseResult baseResult = null;
        String pk = getId(t);
        if(StringUtils.isEmpty(pk)) {
            throw new BaseException(BaseEnum.NO_SET_PK);
        }
        T obj = getObj(pk);

        baseResult = updateBefore(obj,t);
        if(baseResult.isStatus()||null == baseResult) {
            return  baseMyBatisService.update(obj);
        }else {
            return baseResult;
        }
//        BaseResult baseResult = null;
//        try {
//            T obj = getObj(getId(t));
//            updateBefore(obj,t);
//            baseResult = baseMyBatisService.update(obj);
//
//        }catch (Exception e){
//            if(null == baseResult) {
//                baseResult = new BaseResult();
//            }
//            baseResult.setCode(BaseConstant.EXCEPTION_CODE);
//            baseResult.setMsg(BaseConstant.EXCEPTION_MSG);
//        }
//        return baseResult;
    }




    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult delete(@RequestParam("ids[]") String[] ids) throws Exception{
        int len = ids.length;
        if(1 == len ) {
            T obj = getObj(ids[0]);
            return baseMyBatisService.delete(obj);
        }else{
            return baseMyBatisService.deleteList(ids);
        }

//        BaseResult baseResult = null;
//        try {
//            int len = ids.length;
//            if(1 == len ) {
//                T obj = getObj(ids[0]);
//                baseResult = baseMyBatisService.delete(obj);
//            }else{
//                baseResult = baseMyBatisService.deleteList(ids);
//            }
//        }catch (Exception e){
//            if(null == baseResult) {
//                baseResult = new BaseResult();
//            }
//            baseResult.setCode(BaseConstant.EXCEPTION_CODE);
//            baseResult.setMsg(BaseConstant.EXCEPTION_MSG);
//        }
//        return baseResult;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public DatagridResult<T> list(V v) {
        DatagridResult<T> datagridResult;
        try {
            datagridResult = baseMyBatisService.queryObjs(v);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new DatagridResult<>();
        }
        return datagridResult;
    }

    @RequestMapping(value = "/getComboBox", method = RequestMethod.POST)
    @ResponseBody
    public List<ComboBox> getComboBox(V v){
        return baseMyBatisService.queryComboBox(v);
    }

    protected T getObj(String pk){
        T t = null;
        try {
            t = baseMyBatisService.queryObj(pk);
        }catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }

    protected void init(Model model, String id){
        try {
            T t = getObj(id);
            if(null != t) {
                 model.addAttribute("obj",t);
//                model.addAttribute("jsonObj", GsonUtils.objToJsonStr(t));
                model.addAttribute("jsonObj", JSONUtils.obj2json(t));
                model.addAttribute("retMsg", BaseConstant.SUCCESS_MSG);
            }else{
                model.addAttribute("retMsg",BaseConstant.RECORD_EXISTS_NO_MSG);
            }
        }catch (Exception e){
            model.addAttribute("retMsg",BaseConstant.FAIL_MSG);
        }
    }

    /**
     * 获取jsp页面
     * @return 返回jsp页面
     */
    protected String jsp(){
        String jsp = getJsp();
        if(null == jsp) {
            throw new BaseException(BaseEnum.NO_SET_JSP);
        }
        return jsp;
    }
}
