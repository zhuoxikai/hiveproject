package com.eol;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class YuCe {
    private static final String DRIVER_NAME ="com.aliyun.odps.jdbc.OdpsDriver";

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
        stmt.execute("drop table if exists 10_in");
        stmt.execute("drop table if exists 10_out");
        stmt.execute("create table if not exists 10_in " +
                "as select riqi,stations,recivetime from railway_10 " +
                "where trade='进站'");
        stmt.execute("create table if not exists 10_out " +
                "as select riqi,stations,recivetime from railway_10 " +
                "where trade='出站'");
        for (int i = 1; i <= 31; i++) {
            String intime = "";
            String outime = "";
            for (int j = 0; j <= 23; j++) {
                int k = 0;
                if (i < 10) {
                    if (j < 10) {
                        while ((k + 15) < 60) {
                            intime = "2018-10-0" + i + " 0" + j + ":0" + k + ":00";
                            outime = "2018-10-0" + i + " 0" + j + ":0" + (k + 15) + ":00";

                        }

                    }
                }
            }
        }
    }
    
    public static String CreateSQL(String intime,String outime,int i) throws ParseException {
        String sql="create table if not exists 10_"+i+"_"+" as select in_number,out_number,count(*) as passengers" +
                " where in_number between unix_timestamp('"+intime+"','yyyy-MM-dd HH:mm:ss')" +
                " and unix_timestamp('"+outime+"','yyyy-MM-dd HH:mm:ss')" +
                " group by in_number,out_number";
        return sql;
    }
}

