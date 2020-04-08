package com.example.hiveproject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 使用 JdbcTemplate 操作 Hive
 */
@RestController
@RequestMapping("/hive2")
public class HiveApplication {

    private static final Logger logger = LoggerFactory.getLogger(HiveApplication.class);

   /* @Autowired
    @Qualifier("hiveDruidTemplate")
    private JdbcTemplate hiveDruidTemplate;*/

    @Autowired
    @Qualifier("hiveJdbcTemplate")
    private JdbcTemplate hiveJdbcTemplate;

    public void select_table(){
        StringBuffer sql=new StringBuffer("select * from sep_9 limit 10 ");
        hiveJdbcTemplate.execute(sql.toString());
    }

    /**
     * 创建进站表
     */
    @RequestMapping("/table/create_in")
    public String create_in(){
        StringBuffer sql=new StringBuffer("create table if not exists "
                +"railway_in"
                +"(in_id string,in_station string,in_beforebanlance string,in_time string) "
                +"row format delimited fields terminated by \'\t\' lines terminated by \'\n\' "
                +"stored as textfile");
        logger.info("Running:"+sql);
        String result="Create table successfully...";
        try{
            hiveJdbcTemplate.execute(sql.toString());
        }catch (DataAccessException dae){
            result="Create table encounter an error:"+dae.getMessage();
            logger.error(result);
        }
        return result;
    }
    /**
     * 创建出站表
     */
    @RequestMapping("/table/create_out")
    public String create_out(){
        StringBuffer sql=new StringBuffer("create table if not exists "
                +"railway_out"
                +"(out_id string,out_station string,out_time string,out_beforebanlance string) "
                +"row format delimited fields terminated by \'\t\' lines terminated by \'\n\' "
                +"stored as textfile");
        logger.info("Running:"+sql);
        String result="Create table successfully...";
        try{
            hiveJdbcTemplate.execute(sql.toString());
        }catch (DataAccessException dae){
            result="Create table encounter an error:"+dae.getMessage();
            logger.error(result);
        }
        return result;
    }
    /**
     * 创建od配对表
     */
    @RequestMapping("/table/create_od")
    public String create_od(){
        StringBuffer sql=new StringBuffer("create table if not exists "
                +"railway_od"
                +"(id string,in_station string,out_station string,in_time string,out_time string) "
                +"row format delimited fields terminated by \'\t\' lines terminated by \'\n\'"
                +"stored as textfile");
        logger.info("Running:"+sql);
        String result="Create table successfully...";
        try{
            hiveJdbcTemplate.execute(sql.toString());
        }catch (DataAccessException dae){
            result="Create table encounter an error:"+dae.getMessage();
            logger.error(result);
        }
        return result;
    }
    /**
     * 创建od分段统计表
     */
    @RequestMapping("/tables/create_od_total")
    public String create_od_total(){
        StringBuffer sql=new StringBuffer("create table if not exists "
                +"od_total"
                +"(in_station string,out_station string,passengers int) "
                +"row format delimited fields terminated by \'\t\' lines terminated by \'\n\'"
                +"stored as textfile");
        logger.info("Running:"+sql);
        String result="Create table successfully...";
        try{
            hiveJdbcTemplate.execute(sql.toString());
        }catch (DataAccessException dae){
            result="Create table encounter an error:"+dae.getMessage();
            logger.error(result);
        }
        return result;
    }
    /**
     * 添加数据至进站表
     */
    @RequestMapping("/table/insert_in")
    public String insert_in(){
        String sql="insert into table railway_in "
                +"select id,stations,beforebanlance,recivetime from sep_9 "
                +"where action='进站'";
        logger.info("Running:"+sql);
        String result="insert data successfully...";
        try{
            hiveJdbcTemplate.execute(sql.toString());
        }catch (DataAccessException dae){
            result="insert data encounter an error:"+dae.getMessage();
            logger.error(result);
        }
        return result;
    }
    /**
     * 添加数据至出站表
     */
    @RequestMapping("/table/insert_out")
    public String insert_out(){
        String sql="insert into table railway_out "
                +"select id,stations,recivetime,beforebanlance from sep_9"
                +"where action='出站'";
        logger.info("Running:"+sql);
        String result="insert data successfully...";
        try{
            hiveJdbcTemplate.execute(sql.toString());
        }catch (DataAccessException dae){
            result="insert data encounter an error:"+dae.getMessage();
            logger.error(result);
        }
        return result;
    }
    /**
     * 添加数据至od配对表
     */
    @RequestMapping("/table/insert_od")
    public String insert_od(){
        String sql="insert into table railway_od "
                +"select railway_in.in_id,railway_in.in_station,railway_in.in_time,railway_in.in_beforebanlance "
                +"railway_out.out_id,railway_out.out_station,railway_out.out_time,railway_out.out_beforebanlance "
                +"from railway_in,railway_out "
                +"where railway_in.in_id=railway_out.out_id and railway_in.in_beforebanlance=railway_out.out_beforebanlance "
                +"order by railway_in.in_time ASC";
        logger.info("Running:"+sql);
        String result="insert data successfully...";
        try{
            hiveJdbcTemplate.execute(sql.toString());
        }catch (DataAccessException dae){
            result="insert data encounter an error:"+dae.getMessage();
            logger.error(result);
        }
        return result;
    }
    /**
     * 删除进站表
     */
    @RequestMapping("/table/drop_in")
    public String drop_in(){
        String sql="drop table if exists railway_in";
        logger.info("Running:"+sql);
        String result="drop table successfully...";
        try{
            hiveJdbcTemplate.execute(sql.toString());
        }catch (DataAccessException dae){
            result="drop table encounter an error:"+dae.getMessage();
            logger.error(result);
        }
        return result;
    }
    /**
     * 删除出站表
     */
    @RequestMapping("/table/drop_out")
    public String drop_out(){
        String sql="drop table if exists railway_out";
        logger.info("Running:"+sql);
        String result="drop table successfully...";
        try{
            hiveJdbcTemplate.execute(sql.toString());
        }catch (DataAccessException dae){
            result="drop table encounter an error:"+dae.getMessage();
            logger.error(result);
        }
        return result;
    }
    /**
     * 删除od配对表
     */
    @RequestMapping("/table/drop_od")
    public String drop_od(){
        String sql="drop if exists railway_od";
        logger.info("Running:"+sql);
        String result="drop table successfully...";
        try{
            hiveJdbcTemplate.execute(sql.toString());
        }catch (DataAccessException dae){
            result="drop table encounter an error:"+dae.getMessage();
            logger.error(result);
        }
        return result;
    }
    /**
     * 进行od统计操作
     */
    public Map<String,Integer> total_od(String sql){
        Map<String,Integer> map1=new HashMap<>();
        List rows=hiveJdbcTemplate.queryForList(sql);
        Iterator it=rows.iterator();
        while(it.hasNext()){
          Map map=(Map)it.next();
          String odIn=(String) map.get("in_station");
          String odInValue=odIn.replace(" ","");
          String odOut=(String) map.get("out_station");
          String odOutValue=odOut.replace(" ","");
          int persons=Integer.parseInt(map.get("passengers").toString());
          map1.put(odInValue+" "+odOutValue,persons);
        }
        return map1;
    }
    /**
     * 添加数据至od分段表
     */
    public void insert_od_month(String day,String time,String in_station,String out_station,String passengers,String min){
        hiveJdbcTemplate.update("insert into table od_10_01 values (?,?,?,?,?,?)",day,time,in_station,out_station,passengers,min);
    }
}


