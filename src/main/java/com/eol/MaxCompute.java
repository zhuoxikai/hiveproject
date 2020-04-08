package com.eol;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MaxCompute {

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

            String sql="create table if not exists 09_od  as select * from(" +
                    "select * from od_09_01 union all select * from od_09_02" +
                    "union all select * from ) ";
            stmt.execute(sql);

           /* for(int i=20;i<=21;i++){
                if(i<10){
                    for(int j=0;j<=23;j++) {
                        if (j <= 8) {
                            String intime = "2018-10-0" + i + " 0" + j + ":00:00";
                            String outime = "2018-10-0" + i + " 0" + (j + 1) + ":00:00";
                            String day = "10_" + i + "_" + j;
                            stmt.execute(changSql(intime,outime,day));
                            stmt.execute(insertSQL(day,j));
                            stmt.execute(dropSQL(day));
                        } else if (j == 9) {
                            String intime = "2018-10-0" + i + " 09:00:00";
                            String outime = "2018-10-0" + i + " 10:00:00";
                            String day = "10_" + i + "_" + j;
                            stmt.execute(changSql(intime,outime,day));
                            stmt.execute(insertSQL(day,j));
                            stmt.execute(dropSQL(day));
                        } else if (j <= 22) {
                            String intime = "2018-10-0" + i + " " + j + ":00:00";
                            String outime = "2018-10-0" + i + " " + (j + 1) + ":00:00";
                            String day = "10_" + i + "_" + j;
                            stmt.execute(changSql(intime,outime,day));
                            stmt.execute(insertSQL(day,j));
                            stmt.execute(dropSQL(day));
                        } else {
                            String intime = "2018-10-0" + i + " 23:00:00";
                            String outime = "2018-10-0" + (i + 1) + " 00:00:00";
                            String day = "10_" + i + "_" + j;
                            stmt.execute(changSql(intime,outime,day));
                            stmt.execute(insertSQL(day,j));
                            stmt.execute(dropSQL(day));
                        }
                    }
                }else{
                    for(int j=0;j<=23;j++) {
                        if (j <= 8) {
                            String intime = "2018-10-" + i + " 0" + j + ":00:00";
                            String outime = "2018-10-" + i + " 0" + (j + 1) + ":00:00";
                            String day = "10_" + i + "_" + j;
                            stmt.execute(changSql(intime, outime, day));
                            stmt.execute(insertSQL(day, j));
                            stmt.execute(dropSQL(day));
                        } else if (j == 9) {
                            String intime = "2018-10-" + i + " 09:00:00";
                            String outime = "2018-10-" + i + " 10:00:00";
                            String day = "10_" + i + "_" + j;
                            stmt.execute(changSql(intime, outime, day));
                            stmt.execute(insertSQL(day, j));
                            stmt.execute(dropSQL(day));
                        } else if (j <= 22) {
                            String intime = "2018-10-" + i + " " + j + ":00:00";
                            String outime = "2018-10-" + i + " " + (j + 1) + ":00:00";
                            String day = "10_" + i + "_" + j;
                            stmt.execute(changSql(intime, outime, day));
                            stmt.execute(insertSQL(day, j));
                            stmt.execute(dropSQL(day));
                        } else {
                            String intime = "2018-10-" + i + " 23:00:00";
                            String outime = "2018-10-" + (i + 1) + " 00:00:00";
                            String day = "10_" + i + "_" + j;
                            stmt.execute(changSql(intime, outime, day));
                            stmt.execute(insertSQL(day, j));
                            stmt.execute(dropSQL(day));
                        }
                    }
                }
                stmt.execute(lianheSQL(i));
                for(int m=0;m<=23;m++){
                    String name="od_10_" + i + "_" + m;
                    stmt.execute(dropSQL(name));
                }
                stmt.execute(alterSQL(i));
            }*/

            /*for(int k=15;k<=28;k++){
                String name="od_10_"+k;
                String name1="sc_10_"+k;
               String j="201810"+k;
                //stmt.execute(insertSQL(name,j,k));
                //stmt.execute(alterSQL(k));
                //stmt.execute(dropSQL(name));
                stmt.execute(dropSQL(name1));
            }*/

        }



        public static String changSql(String inTime, String outTime,String day) throws ParseException {
            String testOD="create table if not exists "+day+
                " as SELECT station1,station2,count(*) as passengers " +
                "from railway_od where unix_timestamp(TO_DATE(recivetime1,'yyyy-mm-dd hh:mi:ss')) " +
                "BETWEEN unix_timestamp(TO_DATE('"+inTime+"','yyyy-mm-dd hh:mi:ss')) " +
                "AND unix_timestamp(TO_DATE('"+outTime+"','yyyy-mm-dd hh:mi:ss')) " +
                    "group by station1,station2;";
            return testOD;
        }

        public static String insertSQL(String name,String j,int k){
            String sql="create table if not exists sc_10_"+k+" as select hour,station1,station2,passengers," +
                    "case when station1=='NULL' then "+j+" else "+j+" end from "+name;
            return sql;
        }

        public static String dropSQL(String name){
            String sql="drop table if exists "+name;
            return sql;
        }

       public static String UnionSQL(int i){
            String sql="create table if not exists od_10_"+i+" as select * from(" +
                    "select * from od_10_"+i+"_0 union all select * from od_10_"+i+"_1 union all " +
                    "select * from od_10_"+i+"_2 union all select * from od_10_"+i+"_3 union all  " +
                    "select * from od_10_"+i+"_4 union all select * from od_10_"+i+"_5 union all "+
                    "select * from od_10_"+i+"_6 union all select * from od_10_"+i+"_7 union all " +
                    "select * from od_10_"+i+"_8 union all select * from od_10_"+i+"_9 union all " +
                    "select * from od_10_"+i+"_10 union all select * from od_10_"+i+"_11 union all " +
                    "select * from od_10_"+i+"_12 union all select * from od_10_"+i+"_13 union all " +
                    "select * from od_10_"+i+"_14 union all select * from od_10_"+i+"_15 union all " +
                    "select * from od_10_"+i+"_16 union all select * from od_10_"+i+"_17 union all " +
                    "select * from od_10_"+i+"_18 union all select * from od_10_"+i+"_19 union all " +
                    "select * from od_10_"+i+"_20 union all select * from od_10_"+i+"_21 union all " +
                    "select * from od_10_"+i+"_22 union all select * from od_10_"+i+"_23)";
            return sql;
        }

       public static String alterSQL(int i){
            String sql="alter table sc_10_"+i+" change column _c4 rename to riqi";
            return sql;
        }
}


