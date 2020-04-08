package com.eol;

import org.datanucleus.store.types.wrappers.ArrayList;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxComputeLocal {
    private static final String DRIVER_NAME ="com.aliyun.odps.jdbc.OdpsDriver";
    private static  ResultSet rs=null;
    public static void main(String[] args) throws SQLException, ParseException {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Connection conn = DriverManager.getConnection(
                "jdbc:odps:http://service.cn-beijing.maxcompute.aliyun.com/api?project=CHENGZI",
                "LTAI4Fx2ojCF41FVAr8JtsoA", "LT0uMQSeP0gGIAACme07XesSksLd4d");


        Statement stmt = conn.createStatement();
        stmt.execute("drop table if exists railway_od");
        stmt.execute("create table if not exists railway_in " +
                "as SELECT id,riqi,stations,recivetime,beforbanlance " +
                "FROM railway_10 WHERE trade='进站' ");

        stmt.execute("create table if not exists railway_out " +
                "as SELECT id,stations,recivetime,beforbanlance " +
                "FROM railway_10 WHERE trade='出站' ");

        stmt.execute("create table if not exists railway_od " +
                "as SELECT railway_in.id as id1,railway_in.stations as station1," +
                "railway_in.recivetime as recivetime1," +
                "railway_out.stations as station2,railway_out.recivetime as recivetime2 " +
                "From railway_in join railway_out on railway_in.id=railway_out.id " +
                "AND railway_in.beforbanlance=railway_out.beforbanlance and railway_in.recivetime< railway_out.recivetime ");

        //stmt.execute("DROP TABLE IF EXISTS " + "railway_in");

        //stmt.execute("DROP TABLE IF EXISTS " + "railway_out");

        rs=stmt.executeQuery("select * from railway_od limit 5");
        ResultSetMetaData rsm=rs.getMetaData();
        long in_time = rsm.getColumnType(4);
        long out_ime=in_time+00000000040000;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date date=new Date(in_time);
        Date date1=new Date(out_ime);
        String intime=sdf.format(date1);
        String outime=sdf.format(date);
        String testOD="create table if not exists od_10" +
                " as select id1,kind1,station1,recivetime1,station2,recivetime2 from railway_od " +
                "where recivetime2 between unix_timestamp('"+intime+"','yyyy-MM-dd HH:mm:ss') and" +
                " unix_timestamp('"+outime+"','yyyy-MM-dd HH:mm:ss')";
        stmt.execute(testOD);
    }
}
