package com.example.hiveproject;


import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

/**
 * 使用JDBC 连接hive数据库
 */
public class SpringHiveJDBC {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static ResultSet rs=null;
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Connection con = DriverManager.getConnection(
                "jdbc:hive2://10.2.55.99:10000/default","root","");
        Statement stmt = con.createStatement();
        String sql="insert into table railway_od select railway_in.in_id as id,railway_in.in_station as in_station,railway_out.out_station as out_station,railway_in.in_time as in_time,railway_out.out_time as out_time from railway_in,railway_out where railway_in.in_id=railway_out.out_id and railway_in.in_beforebanlance=railway_out.out_beforebanlance order by railway_in.in_time ASC";
        rs=stmt.executeQuery(sql);
        if (rs.next()) {
            System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+
                    rs.getString(4)+rs.getString(5));
        }
    }
}

