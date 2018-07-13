/**
 * Created by Administrator on 2017/9/29.
 */
/**
 * Created by user on 2017/9/1.
 */


/**
 * 百度地图公共js
 */
window.baiduMapUtil = function(){
    /*正式百度密钥*/
    var ak = "fdv5gclnF2HSnNyKm4Dj3aVuLUGMLUZ1";
    /*地图对象*/
    var map;
    /*百度地图坐标*/
    var baiduPoints = [];
    var pub = {
        setMap:function (vmap) {
            map = vmap;
        },
        /**
         * 获取地图
         * @param elementId 文档元素id
         * @param callback 回调函数
         * @returns {BMap.Map} 返回地图对象
         */
        getMap:function (elementId,callback) {
            map = null;
            map = new BMap.Map(elementId,{enableMapClick:false});    // 创建Map实例
            map.centerAndZoom("广州",14);      // 初始化地图,用城市名设置地图中心点
            //	map.centerAndZoom(new BMap.Point(113.3382000000, 23.1389800000), 11);  // 初始化地图,设置中心点坐标和地图级别

            // map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
            //map.setCurrentCity("广州");          // 设置地图显示的城市 此项是必须
            // 设置的
            map.enableScrollWheelZoom();

            //map.setMapStyle({style:'midnight'});

            //添加控件和比例尺
            // var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});
            // var top_left_navigation = new BMap.NavigationControl();
            // map.addControl(top_left_control);
            // map.addControl(top_left_navigation);

            // var stCtrl = new BMap.PanoramaControl(); //构造全景控件
            // stCtrl.setOffset(new BMap.Size(5, 5));
            // stCtrl.setAnchor(BMAP_ANCHOR_BOTTOM_RIGHT);
            // map.addControl(stCtrl);//添加全景控件

            $.getJSON("/onlineTerminal/ticc/common/static/file/mapStyle.json",null,function (data) {
            // $.getJSON("ticc/common/static/file/mapStyle.json",null,function (data) {
                map.setMapStyle({styleJson:data});
                if (callback instanceof Function){
                    callback(map);
                }else {
                    return map;
                }
                // return map;
            });

            // return map;
        },
        /**
         * 获取地图
         * @param elementId 文档元素id
         * @param callback 回调函数
         * @returns {BMap.Map} 返回地图对象
         */
        getDisplayMap:function (elementId) {
            map = null;
            map = new BMap.Map(elementId,{enableMapClick:false});    // 创建Map实例
            map.centerAndZoom("广州",14);      // 初始化地图,用城市名设置地图中心点
            //	map.centerAndZoom(new BMap.Point(113.3382000000, 23.1389800000), 11);  // 初始化地图,设置中心点坐标和地图级别

            // map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
            //map.setCurrentCity("广州");          // 设置地图显示的城市 此项是必须
            // 设置的
            map.enableScrollWheelZoom();

            //map.setMapStyle({style:'midnight'});

            //添加控件和比例尺
            // var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});
            // var top_left_navigation = new BMap.NavigationControl();
            // map.addControl(top_left_control);
            // map.addControl(top_left_navigation);

            // var stCtrl = new BMap.PanoramaControl(); //构造全景控件
            // stCtrl.setOffset(new BMap.Size(5, 5));
            // stCtrl.setAnchor(BMAP_ANCHOR_BOTTOM_RIGHT);
            // map.addControl(stCtrl);//添加全景控件

            return map;
        },
        /**
         * 添加图像标注
         * @param points    坐标数组
         * @param markerOptions 标注可选参数
         * @param event 事件名
         * @param handler 事件处理函数
         * @param data 标注物的额外参数
         * @returns {Array}
         */
        addMarker : function(points,markerOptions,event,handler,data) {
            var markers = [];
            if (!points.length){
                var marker;
                if (markerOptions instanceof Object){
                    marker = new BMap.Marker(points, markerOptions);
                }else if (markerOptions instanceof Object){
                    marker = new BMap.Marker(points[i], markerOptions);
                }else{
                    marker = new BMap.Marker(points);
                }

                //额外的扩展属性
                if (data instanceof Object){
                    marker.data = data;
                }

                if (typeof event == 'string' && handler instanceof Function){
                    // marker.addEventListener(event, handler(marker));
                    marker.addEventListener(event, function () {
                        handler.call(this,this);
                    });
                }
                map.addOverlay(marker);
                markers.push(marker);
                return markers;
            }
            for (var i = 0, len = points.length; i < len; i++) {
                var marker;
                if (markerOptions instanceof Array){
                    marker = new BMap.Marker(points[i], markerOptions[i]);
                }else if (markerOptions instanceof Object){
                    marker = new BMap.Marker(points[i], markerOptions);
                }else{
                    marker = new BMap.Marker(points[i]);
                }

                //额外的扩展属性
                if (data instanceof Array){
                    marker.data = data[i];
                }else if (data instanceof Object){
                    marker.data = data;
                }

                if (event instanceof Array && handler instanceof Array){
                    if (typeof event[i] == 'string' && handler[i] instanceof Function){
                        marker.addEventListener(event[i], function () {
                            handler[i].call(this,this);
                        });
                    }
                }else if (typeof event == 'string' && handler instanceof Function){
                    // marker.addEventListener(event, handler(marker));
                    marker.addEventListener(event, function () {
                        handler.call(this,this);
                    });
                }
                map.addOverlay(marker);
                markers.push(marker);
            }
            // alert(points.length);
            // alert(markers);
            return markers;
        },
        /**
         * 添加点聚合
         * @param points    坐标数组
         * @param markerOptions 标注可选参数
         * @param event 事件名
         * @param handler 事件处理函数
         * @param labels label显示的内容数组
         * @param labelOptions label选项
         * @param labelStyle label样式
         * @param data 标注物的额外参数
         * @returns {BMapLib.MarkerClusterer}
         */
        addMarkerClusterer: function(points,markerOptions,event,handler,labels,labelOptions,labelStyle,data,minClusterSize) {
            var markers = [];
            var markerClusterer;
            if (null == minClusterSize || undefined == minClusterSize){
                minClusterSize = 4;
            }
            for (var i = 0, len = points.length; i < len; i++) {
                var marker;
                if (markerOptions instanceof Array){
                    marker = new BMap.Marker(points[i], markerOptions[i]);
                }else if (markerOptions instanceof Object){
                    marker = new BMap.Marker(points[i], markerOptions);
                }else{
                    marker = new BMap.Marker(points[i]);
                }

                //额外的扩展属性
                if (data instanceof Array){
                    marker.data = data[i];
                }else if (data instanceof Object){
                    marker.data = data;
                }
                if (event instanceof Array && handler instanceof Array){
                    if (typeof event[i] == 'string' && handler[i] instanceof Function){
                        marker.addEventListener(event[i], function () {
                            handler[i].call(this,this);
                        });
                    }
                }else if (typeof event == 'string' && handler instanceof Function){
                    // marker.addEventListener(event, handler(marker));
                    marker.addEventListener(event, function () {
                        handler.call(this,this);
                    });
                }
                if (undefined != labels || null != labels){
                    var textLabel;
                    if (labelOptions instanceof Array){
                        textLabel = new BMap.Label(labels[i], labelOptions[i]);
                    }else if (labelOptions instanceof Object){
                        textLabel = new BMap.Label(labels[i], labelOptions);
                    }else{
                        textLabel = new BMap.Label(labels[i]);
                    }
                    if (undefined == labelStyle || null == labelStyle){
                        textLabel.setStyle({
                            color : "black",
                            fontSize : "12px",
                            fontFamily:"微软雅黑",
                            border:"0px",
                            backgroundColor:"#FFFFFF"
                        });
                    }else{
                        textLabel.setStyle(labelStyle);
                    }
                    marker.setLabel(textLabel);
                }


                markers.push(marker);
            }
            // markers = pub.addMarker(points,markerOptions,event,handler,data);
            markerClusterer = new BMapLib.MarkerClusterer(map,
                {
                    markers: markers,
                    minClusterSize: minClusterSize,
                    // styles:[{
                    //     url: '/onlineTerminal/ticc/common/static/displayAdmin/image/bus_01.png',
                    //     size: new BMap.Size(30, 26),
                    //     opt_anchor: [16, 0],
                    //     textColor: '#ff00ff',
                    //     opt_textSize: 10
                    // }, {
                    //     url: '/onlineTerminal/ticc/common/static/displayAdmin/image/bus_02.png',
                    //     size: new BMap.Size(40, 35),
                    //     opt_anchor: [40, 35],
                    //     textColor: '#ff0000',
                    //     opt_textSize: 12
                    // }, {
                    //     url: '/onlineTerminal/ticc/common/static/displayAdmin/image/bus_03.png',
                    //     size: new BMap.Size(50, 44),
                    //     opt_anchor: [32, 0],
                    //     textColor: '#ff0000',
                    //     opt_textSize: 14
                    // }]
                    styles:[{
                        url:'/busAnalysisSys/static/busAnalysisDisplay/images/polymeric_icon_1.png',  //图标路径
                        size: new BMap.Size(60, 60),  //图标大小
                        textSize: 10,
                        opt_anchor: [16, 0],
                        textColor: '#FFF'
                    },{
                        url:'/busAnalysisSys/static/busAnalysisDisplay/images/polymeric_icon_2.png',  //图标路径
                        size: new BMap.Size(60, 60),  //图标大小
                        textSize: 10,
                        textColor: '#FFF'
                    },{
                        url:'/busAnalysisSys/static/busAnalysisDisplay/images/polymeric_icon_3.png',  //图标路径
                        size: new BMap.Size(60, 60),  //图标大小
                        textSize: 10,
                        textColor: '#FFF'
                    }]
                }
                );
            return markerClusterer;
        },
        /**
         * 添加文本标注
         * @param labels    内容数组
         * @param labelOptions  文本标注可选参数数组
         * @param labelStyle    文本标注样式对象
         */
        addLabel: function(labels,labelOptions,labelStyle){
            for (var i=0,len = labels.length; i<len; i++){
                var textLabel = new BMap.Label(labels[i], labelOptions[i]);
                if (undefined == labelStyle || null == labelStyle){
                    textLabel.setStyle({
                        color : "black",
                        fontSize : "12px",
                        fontFamily:"微软雅黑",
                        border:"0px",
                        backgroundColor:"#00ffffff"
                    });
                }else{
                    textLabel.setStyle(labelStyle);
                }

                map.addOverlay(textLabel);

            }
        },
        /**
         *  添加折线覆盖
         * @param points   坐标数组
         * @param polylineOptions   折线覆盖可选参数对象
         */
        addPolyline : function(points,opts,event, handler){

            if (null == opts || undefined == opts){
                opts = {
                    strokeColor: '#75CEE0',//	圆形边线颜色
                    fillColor: '#75CEE0',
                    fillOpacity: 0.2,
                    strokeWeight: 2,
                    strokeOpacity: 0.5
                    // enableEditing: true
                };
            }

            var polyline = new BMap.Polyline(points, opts);
            if (typeof event == 'string'){
                polyline.addEventListener(event,function (type, target) {
                    if(null != handler && undefined != handler && handler instanceof Function) {
                        handler.call(this, this);
                    }
                });
            }
            map.addOverlay(polyline);//覆盖折线到地图上

            return polyline;
        },
        /**
         * 添加圆覆盖物
         * @param center    圆中心点坐标
         * @param radius    圆半径
         * @param opts      圆的选项
         * @param event     事件
         * @param handler   事件对应的方法
         * @returns {BMap.Circle} 返回圆
         */
        addCircle : function(centers, radius, opts, event, handler) {
            var circles = [];
            if (centers instanceof Array){
                for (var i = 0, len = centers.length; i < len; i++){
                    var center = centers[i];
                    //圆形的半径，单位为米
                    // var myRadius = radius[i];
                    var myRadius;
                    if (radius instanceof Array){
                        myRadius = radius[i];
                    }else{
                        myRadius = radius;
                    }
                    var circle;
                    if (null != opts && undefined != opts){
                        circle = new BMap.Circle(center, myRadius, opts);
                    }else {
                        opts = {
                            strokeColor: '#75CEE0',//	圆形边线颜色
                            fillColor: '#75CEE0',
                            fillOpacity: 0.2,
                            strokeWeight: 2,
                            strokeOpacity: 0.5
                            // enableEditing: true
                        };
                        circle = new BMap.Circle(center, myRadius, opts);
                    }
                    if (event instanceof Array){
                        for (var j = 0, length = event.length; j < length; j++){
                            circle.addEventListener(event[j],function (type, target) {
                                for (var k = 0, len1 = handler.length; k < len1; k++){
                                    if(null != handler[j] && undefined != handler[j] && handler[j] instanceof Function) {
                                        handler[j].call(this, this);
                                    }
                                }
                            });
                        }
                    }else if (typeof event == 'string'){
                        circle.addEventListener(event,function (type, target) {
                            if(null != handler && undefined != handler && handler instanceof Function) {
                                handler.call(this, this);
                            }
                        });
                    }


                    //判断是否有回调函数
                    // if(null != callback && undefined != callback && callback instanceof Function){
                    //     circle.addEventListener("lineupdate",function (type, target) {
                    //         callback.call(this,this);
                    //     });
                    // }

                    map.addOverlay(circle);
                    circles.push(circle);
                }

            }else{
                var center = centers;
                //圆形的半径，单位为米
                var myRadius = radius;
                var circle;
                if (null != opts && undefined != opts){
                    circle = new BMap.Circle(center, myRadius, opts);
                }else {
                    opts = {
                        strokeColor: '#75CEE0',//	圆形边线颜色
                        fillColor: '#75CEE0',
                        fillOpacity: 0.2,
                        strokeWeight: 2,
                        strokeOpacity: 0.5
                        // enableEditing: true
                    };
                    circle = new BMap.Circle(center, myRadius, opts);
                }

                if (event instanceof Array){
                    for (var j = 0, length = event.length; j < length; j++){
                        circle.addEventListener(event[j],function (type, target) {
                            for (var k = 0, len1 = handler.length; k < len1; k++){
                                if(null != handler[j] && undefined != handler[j] && handler[j] instanceof Function) {
                                    handler[j].call(this, this);
                                }
                            }
                        });
                    }
                }else if (typeof event == 'string'){
                    circle.addEventListener(event,function (type, target) {
                        if(null != handler && undefined != handler && handler instanceof Function) {
                            handler.call(this, this);
                        }
                    });
                }


                // //判断是否有回调函数
                // if(null != callback && undefined != callback && callback instanceof Function){
                //     circle.addEventListener("lineupdate",function (type, target) {
                //         callback.call(this,this);
                //     });
                // }
                map.addOverlay(circle);
                circles.push(circle);
            }
            return circles;
        },
        /**
         *
         * @param startPoint
         * @param endPoint
         * @param opts
         * @param event
         * @param handler
         */
        addRectangle : function(startPoint, endPoint, opts, event, handler) {
            try {
                var rectangles = [];
                if (startPoint instanceof Array){
                    var points;
                    for (var i = 0, len = startPoint.length; i < len; i++){
                        points = [];
                        points.push(new BMap.Point(startPoint[0].lng,startPoint[0].lat));
                        points.push(new BMap.Point(endPoint[0].lng,startPoint[0].lat));
                        points.push(new BMap.Point(endPoint[0].lng,endPoint[0].lat));
                        points.push(new BMap.Point(startPoint[0].lng,endPoint[0].lat));
                        var polyline = pub.addPolyline(points,opts,event, handler);
                        rectangles.push(polyline);
                    }
                }else{
                    var points = [];
                    points.push(new BMap.Point(startPoint.lng,startPoint.lat));
                    points.push(new BMap.Point(endPoint.lng,startPoint.lat));
                    points.push(new BMap.Point(endPoint.lng,endPoint.lat));
                    points.push(new BMap.Point(startPoint.lng,endPoint.lat));
                    var polyline = pub.addPolyline(points,opts,event, handler);
                    rectangles.push(polyline);
                }
                return rectangles;
            }catch (e){
                console.error(e);
                console.info("添加矩形失败");
            }
        },
        /**
         * 创建信息窗体
         * @param html 内容
         * @returns {BMapLib.InfoBox}
         */
        createInfoBox:function (html,size) {
            try {
                if (undefined != size && null != size){
                    return new BMapLib.InfoBox(map,html,{align: INFOBOX_AT_TOP,offset:size,enableAutoPan: true});
                }else{
                    return new BMapLib.InfoBox(map,html,{align: INFOBOX_AT_TOP,offset:new BMap.Size(0, 40),enableAutoPan: true});
                }
            }catch (e){
                console.log(e);
                return new BMapLib.InfoBox(map,html,{align: INFOBOX_AT_TOP,offset:new BMap.Size(0, 40),enableAutoPan: true});
            }

            // return new BMapLib.InfoBox(map,html,{boxStyle:{
            //     width: "200px",height: "300px"},align: INFOBOX_AT_TOP,offset:new BMap.Size(0, 100),enableAutoPan: true});
        },
        /**
         *
         * @param htmlContent 内容
         * @param posi 位置
         * @param title 标题
         * @param width 宽度
         * @param height 高度
         * @param offset 偏移
         */
        openInfoWindow:function (htmlContent,posi,title,width,height,offset,isCenterAndZoom) {
            var opts = {};
            if (null != width && undefined != width){
                opts.width = width;
            }
            if (null != height && undefined != height){
                opts.height = height;
            }
            if (null == offset && undefined == offset){
                opts.offset = new BMap.Size(9, -10);
            }
            if (null != title && undefined != title){
                opts.title = title;
            }
            if (null == isCenterAndZoom || undefined == isCenterAndZoom){
                isCenterAndZoom = true;
            }
            if (isCenterAndZoom){
                map.centerAndZoom(posi, 20);
            }
            var infoWindow = new BMap.InfoWindow(htmlContent, opts);  // 创建信息窗口对象
            map.openInfoWindow(infoWindow, posi); //开启信息窗口
        },
        /**
         * gps坐标转百度坐标
         * @param gpsPonit  gps坐标数组
         * @param i         坐标索引
         * @param callBack  转换成功后的回调函数 baiduPoints参数是百度坐标数组
         * @param from      源坐标类型
         * @param to        目标坐标类型
         */
        transPoint : function (gpsPonit,i,transPointCallBack,from,to) {
            if (undefined == i || null == i){
                i = 0;
            }
            if (undefined == from || null == from){
                from = 1;
            }
            if (undefined == to || null == to){
                to = 5;
            }
            var coords = "";
            for(var length = gpsPonit.length; i<length; i++){
                coords += gpsPonit[i].lng+","+gpsPonit[i].lat+";";
                if(i%99 == 0 || i == length-1){
                    coords = coords.substring(0,coords.length-1);
                    //正式的
                    var param = "coords="+coords+"&from="+from+"&to="+to+"&ak="+ak;
                    $.ajax({
                        url: "http://api.map.baidu.com/geoconv/v1/?"+param,
                        type: 'GET',
                        dataType: 'JSONP',//here
                        success: function (data) {
                            // alert(JSON.stringify(data));
                            if(data.status === 0){
                                for (var j = 0; j < data.result.length; j++) {
                                    baiduPoints.push(new BMap.Point(data.result[j].x,data.result[j].y));
                                }
                                if(length <= baiduPoints.length){//转换完成
                                    // if (callBack instanceof Function){
                                    //     callBack(baiduPoints);
                                    //
                                    // }
                                    transPointCallBack.call(this,baiduPoints);
                                    baiduPoints = [];
                                }else{
                                    //递归
                                    pub.transPoint(gpsPonit,++i,transPointCallBack,from,to);
                                }
                            }else{
                                console.log("坐标转换出错了");
                            }

                        }
                    });
                    break;
                }
            }

        },
        /**
         *  百度地图坐标转gps坐标（有误差）
         * @param baiduPoints 百度坐标数组
         */
        baiduToGps: function (baiduPoints) {
            try {
                var gpsPoints = [];
                if (baiduPoints instanceof Array){
                    for (var i = 0, len = baiduPoints.length; i < len; i++){
                        var bd_lng=baiduPoints[0].lng - 0.0065;

                        var bd_lat=baiduPoints[0].lat - 0.006;

                        var z = Math.sqrt(bd_lng * bd_lng + bd_lat * bd_lat) - 0.00002 * Math.sin(bd_lat * Math.PI);

                        var theta = Math.atan2(bd_lat, bd_lng) - 0.000003 * Math.cos(bd_lng * Math.PI);

                        var lng = z * Math.cos(theta);

                        var lat = z * Math.sin(theta);
                        lng = lng.toFixed(6);
                        lat = lat.toFixed(7);
                        gpsPoints.push(new BMap.Point(lng, lat));
                    }
                    return gpsPoints;
                }
            }catch (e){
                console.error('baidu转gps出错',e);
            }




        }


    };
    return pub;
}();

