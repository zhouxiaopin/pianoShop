package cn.zcp.bgmange.base.utils;

import java.util.*;

public class MapUtils {
    public static boolean ASC = true;
    public static boolean DESC = false;
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map,boolean asc) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        if(asc) {
            Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
                public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                    return (o1.getValue()).compareTo(o2.getValue());
                }
            });
        }else{
            Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
                public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            });
        }

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueAndLimit(Map<K, V> map,boolean asc,Integer limit) {
        if((null == map && map.size() <= 0) || limit == null || limit == 0) {
            return null;
        }
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        if(asc) {
            Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
                public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                    return (o1.getValue()).compareTo(o2.getValue());
                }
            });
        }else{
            Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
                public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            });
        }

        Map<K, V> result = new LinkedHashMap<K, V>();
        int count = 0;
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
            count++;
            if(count == limit) {
                break;
            }
        }
        return result;
    }


    public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map,boolean asc) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        if(asc) {
            Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
                public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                    return (o1.getKey()).compareTo(o2.getKey());
                }
            });
        }else{
            Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
                public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                    return (o2.getKey()).compareTo(o1.getKey());
                }
            });
        }

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
//    public static <K, V> Map<K, V> sortMapByKey(Map<K, V> map,boolean asc) {
//        if (map == null || map.isEmpty()) {
//            return null;
//        }
//
//        Map<K, V> sortMap;
//        if(asc) {
//            sortMap = new TreeMap<>(
//                    new CompareObjectAsc());
//        }else {
//            sortMap = new TreeMap<>(
//                    new CompareObjectDesc());
//        }
//        sortMap.putAll(map);
//        return sortMap;
//    }
//
//
//    static class CompareObjectAsc implements Comparator<Object> {
//        @Override
//        public int compare(Object o1, Object o2){
//            if (o1 instanceof String) {
//                return compareImp( (String) o1, (String) o2);
//            }else if (o1 instanceof Integer) {
//                return compareImp( (Integer) o1, (Integer) o2);
//            }else if (o1 instanceof Long) {
//                return compareImp( (Long) o1, (Long) o2);
//            }else {  //根据需要扩展compare函数
//                System.err.println("未找到合适的比较器");
//                return 1;
//            }
//        }
//        //重载
//        public int compareImp(String o1, String o2) {
//            return o1.compareTo(o2);
//        }
//        //重载
//        public int compareImp(Integer o1, Integer o2) {
//            int val1 = o1.intValue();
//            int val2 = o2.intValue();
//            return (val1 < val2 ? -1 : (val1 == val2 ? 0 : 1));
//
//        }
//        //重载
//        public int compareImp(Long o1, Long o2) {
//            long val1 = o1.intValue();
//            long val2 = o2.intValue();
//            return (val1 < val2 ? -1 : (val1 == val2 ? 0 : 1));
//        }
//    }
//    static class CompareObjectDesc implements Comparator<Object> {
//        @Override
//        public int compare(Object o1, Object o2){
//            if (o1 instanceof String) {
//                return compareImp( (String) o1, (String) o2);
//            }else if (o1 instanceof Integer) {
//                return compareImp( (Integer) o1, (Integer) o2);
//            }else if (o1 instanceof Long) {
//                return compareImp( (Long) o1, (Long) o2);
//            }else {  //根据需要扩展compare函数
//                System.err.println("未找到合适的比较器");
//                return 1;
//            }
//        }
//        //重载
//        public int compareImp(String o1, String o2) {
//            return o2.compareTo(o1);
//        }
//        //重载
//        public int compareImp(Integer o1, Integer o2) {
//            int val1 = o1.intValue();
//            int val2 = o2.intValue();
//            return (val1 >= val2 ? -1 : (val1 == val2 ? 0 : 1));
//
//        }
//        //重载
//        public int compareImp(Long o1, Long o2) {
//            long val1 = o1.intValue();
//            long val2 = o2.intValue();
//            return (val1 >= val2 ? -1 : (val1 == val2 ? 0 : 1));
//        }
//    }

    public static void main(String[] args) {
        Map<Integer, String> map = new TreeMap<Integer, String>();

        map.put(5, "kfc");
        map.put(12, "wnba");
        map.put(9, "nba");
        map.put(20, "cba");
        Map<Integer, String> resultMap = sortByKey(map,ASC);    //按Key进行排序

        for (Map.Entry<Integer, String> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }


    /**
     * 比较器类
     */
//    static class MapKeyComparatorAsc implements Comparator<Number>{
//        @Override
//        public int compare(Number o1, Number o2) {
//            return o1 > 02;
//        }
//    }
//    static class MapKeyComparatorDesc implements Comparator<Comparable>{
//        @Override
//        public int compare(Comparable o1, Comparable o2) {
//            return (o2).compareTo(o1);
//        }
//    }

}
