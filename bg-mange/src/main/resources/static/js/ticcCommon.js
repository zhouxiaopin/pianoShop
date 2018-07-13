/**
 * Created by Administrator on 2017/9/15.
 */
window.ticc = {
    /**
     * ajax请求
     * @param path  请求路径
     * @param data   参数
     * @param success   请求成功回调函数
     * @param ptype     请求方式
     * @param error     请求失败回调函数
     * @param timeout   请求超时时间
     * @param async      是否是异步请求
     */
    ajaxRequest: function (path, data, success, ptype, error, timeout, async) {

        if (!ptype || ptype.toLowerCase() != "get"){
            ptype = "post";
        }
        if (async == undefined) {
            async = true;
        }
        $.ajax({
            url: path,
            type: ptype,
            data: data,
            async: async,
            dataType: "json",
            timeout: (timeout == undefined || timeout == null) ? 15000 : timeout,
            success: success,
            error: function (xhr, textStatus, errorThrown) {
                if (errorThrown == 'timeout') {
                    ticc.alertError('服务器繁忙,请稍后再试');
                }
            }

        });
    },
    getBrowser: function(){
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
        var isOpera = userAgent.indexOf("Opera") > -1;
        //判断是否Opera浏览器
        if (isOpera) {
            return "Opera"
        }
        //判断是否Firefox浏览器
        if (userAgent.indexOf("Firefox") > -1) {
            return "Firefox";
        }
        //判断是否Chrome浏览器
        if (userAgent.indexOf("Chrome") > -1){
            return "Chrome";
        }
        //判断是否Safari浏览器
        if (userAgent.indexOf("Safari") > -1) {
            return "Safari";
        }
        //判断是否IE浏览器
        if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
            return "IE";
        }
    },
    /**
     *
     * @param element 元素
     * @param percent   百分比
     * @returns {number}
     */
    getFixWidth: function(element,percent){
        return ($(element).width()) * percent ;
    },
    /**
     *
     * @param msg   显示的消息文本
     * @param fn    在窗口关闭的时候触发该回调函数
     */
    alertInfo: function (msg,fn) {
        $.messager.alert('提示',msg,'info',fn);
    },
    alertQuestion: function (msg,fn) {
        $.messager.alert('提示',msg,'question',fn);
    },
    alertWarning: function (msg,fn) {
        $.messager.alert('警告',msg,'warning',fn);
    },
    alertError: function (msg,fn) {
        $.messager.alert('错误',msg,'error',fn);
    },
    confirm: function (msg,fn) {
        $.messager.confirm('提示',msg,fn);
    },
    /**
     * 添加收藏
     * @param url
     * @param title
     */
    addFavorite: function(url,title){
        try {
            window.external.AddFavorite(url, title);
        }catch(e) {
            ticc.alertInfo("加入收藏失败，请使用Ctrl+D进行添加");
        }
    },
    isNullOrEmpty: function (obj) {
        if (null == obj || undefined == obj || "" == obj){
            return true;
        }
        return false;
    },
    isNull: function (obj) {
        if (null == obj || undefined == obj){
            return true;
        }
        return false;
    },
    arrayIsNullOrEmpty: function (obj) {
        if (null == obj || undefined == obj || obj.length <= 0){
            return true;
        }
        return false;
    },
    /*
         * 设置数字滚动
         * target：目标元素的ID, start：开始值, end：结束值, decimals ：小数位数,默认值是0,duration： 动画延迟秒数，默认值是2
         */
    setCountUp: function(target, start, end, decimals, duration) {
        var countUp_item = new CountUp(target, start, end, decimals || 0, duration || 2, {
            useEasing: true,
            useGrouping: true,
            separator: ',',
            decimal: '.'
        });
        if (!countUp_item.error) {
            countUp_item.start();
        } else {
            console.error("数字滚动" + countUp_item.error);
        }
    },
    /**
     * 校验只要是数字（包含正负整数，0以及正负浮点数）就返回true
     * @param val
     * @returns {boolean}
     */
    isNumber: function(val){

        var regPos = /^\d+(\.\d+)?$/; //非负浮点数
        var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
        if(regPos.test(val) || regNeg.test(val)){
            return true;
        }else{
            return false;
        }

    }
};
