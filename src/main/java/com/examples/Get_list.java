package com.examples;

import java.util.ArrayList;
import java.util.List;

public class Get_list {

    /**
     *  将数组转化为集合
     */
    public static List<String> ChangType(String[] str){
        List<String> list=new ArrayList<>(str.length);
        for(String s:str){
            list.add(s);
        }
        return list;
    }

    /**
     *  删除卡号和进站时间一样的数据
     */
    public static String[] Compare_id(List<String> id,List<String> time_in) {
        for(int i=id.size()-1;i>=1;i--){
            if((id.get(i).equals(id.get(i-1)))&&(time_in.get(i).equals(time_in.get(i-1)))){
                id.remove(i);
            }
        }
        String[] id1=id.toArray(new String[id.size()]);
        return id1;
    }

    public static String[] Compare_time(List<String> id,List<String> time_in) {
        for(int i=id.size()-1;i>=1;i--){
            if((id.get(i).equals(id.get(i-1)))&&(time_in.get(i).equals(time_in.get(i-1)))){
                time_in.remove(i);
            }
        }
        String[] time_in1=time_in.toArray(new String[id.size()]);
        return time_in1;
    }

    public static String[] Compare_types(List<String> id,List<String> time_in,List<String> type ) {
        for(int i=id.size()-1;i>=1;i--){
            if((id.get(i).equals(id.get(i-1)))&&(time_in.get(i).equals(time_in.get(i-1)))){
                type.remove(i);
            }
        }
        String[] type1=type.toArray(new String[id.size()]);
        return type1;
    }

}
