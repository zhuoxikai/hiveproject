package com.eol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HIveTest {
    private static final String DRIVER_NAME ="org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws SQLException, ParseException {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Connection conn = DriverManager.getConnection(
                "jdbc:hive2://123.57.18.29:10000/default",
                "root", "112920");

        Statement stmt = conn.createStatement();

        stmt.execute("drop table if exists railway_od");
        stmt.execute("create table if not exists railway_in " +
                "as SELECT id,kinds,stations,recivetime,beforbanlance " +
                "FROM railway WHERE trade='进站'");

        stmt.execute("create table if not exists railway_out " +
                "as SELECT id,kinds,stations,recivetime,beforbanlance " +
                "FROM railway WHERE trade='出站'");

        stmt.execute("create table if not exists railway_od " +
                "as SELECT railway_in.id as id1,railway_in.kinds as kind1,railway_in.stations as station1,railway_in.recivetime as recivetime1," +
                "railway_out.stations as station2,railway_out.recivetime as recivetime2 " +
                "From railway_in join railway_out on railway_in.id=railway_out.id " +
                "AND railway_in.beforbanlance=railway_out.beforbanlance ");

        stmt.execute("DROP TABLE IF EXISTS " + "railway_in");

        stmt.execute("DROP TABLE IF EXISTS " + "railway_out");
        String sql="";

        for(int i=1;i<=1;i++){
            String day_in="2018-10-0"+i+" 00:00:01";
            String day_out="2018-10-0"+(i+1)+" 00:00:01";
            sql=changSql(day_in,day_out,i+"");
            stmt.execute(sql);
        }
    }

    public static String changSql(String inTime,String outTime, String day) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String testOD="create table if not exists od_1-_"+day+
                "as SELECT id1,kind1,station1,recivetime1,station2,recivetime2 " +
                "from railway_od where unix_timestamp(TO_DATE(recivetime1,'yyyy-mm-dd hh:mi:ss')) " +
                "BETWEEN unix_timestamp(TO_DATE('"+inTime+"','yyyy-mm-dd hh:mi:ss')) " +
                "AND unix_timestamp(TO_DATE('"+outTime+"','yyyy-mm-dd hh:mi:ss')) ;";
        return testOD;
    }

}
