package com.example.hiveproject;

import org.apache.hadoop.hdfs.inotify.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class HiveApplicationTest {
    @Autowired
    private HiveApplication hiveApplication;

    /**
     * 转化时间，对od分段语句进行查询
     */
    public String selectSQL(String intime,int time) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=simpleDateFormat.parse(intime);
        if(date.getMinutes()+time>=60){
            date.setTime(date.getMinutes()-60);
            date.setHours(date.getHours()+1);
        }else{
            date.setMinutes(date.getMinutes()+time);
        }
        String outime=simpleDateFormat.format(date);
        String sql="select in_station,out_station,count(*) as passengers from railway_od"
                +"where in_time between unix_timestamp('"+intime+"','yyyy-MM-dd HH:mm:ss')"
                + " and unix_timestamp('"+outime+"','yyyy-MM-dd HH:mm:ss') "
                +"group by in_station,out_station";
        return sql;
    }
    /**
     * 创建分段前的od表
     */
    public Map<String,Integer> beforOD(String intime ,int time) throws Exception {
        hiveApplication.create_in();
        hiveApplication.create_out();
        hiveApplication.create_od();
        hiveApplication.insert_in();
        hiveApplication.insert_out();
        hiveApplication.insert_od();
        String sql=selectSQL(intime,time);
        Map<String,Integer> map=hiveApplication.total_od(sql);
//        hiveApplication.drop_in();
//        hiveApplication.drop_out();
//        hiveApplication.drop_od();
        return map;
    }
    /**
     * 创建分段后的od统计表
     */
    public Map<String, Integer> afterOD(String intime,int time) throws Exception {
        String sql=selectSQL(intime,time);
        Map<String,Integer> map=hiveApplication.total_od(sql);
        return map;
    }
}
