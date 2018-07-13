package cn.zcp.wechartapi.base.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * Created by Administrator on 2017/11/13.
 */
public class DateUtils {
    private DateUtils(){}
    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMMSS1 = "yyyyMMddHHmmss";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMM = "yyyyMM";
    public static final String YYYY = "yyyy";
    public static final String MMDDHHMM = "MM-dd HH:mm";

    /**
     * 获取系统当前时间
     * @param format 时间格式
     * @return
     */
    public static String getSystemTime(String format){
       /* Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        if(month<10){
            return year+"-0"+month+"-"+day+" "+hour+":"+minute+":"+second;
        }else{
            return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
        }*/
        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);

    }
    /**
     * 格式化时间
     * @param format 时间格式
     * @return
     */
    public static String formatTime(Date date,String format){
       /* Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        if(month<10){
            return year+"-0"+month+"-"+day+" "+hour+":"+minute+":"+second;
        }else{
            return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
        }*/
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);

    }
}
