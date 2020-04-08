package com.examples;



import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class get_id extends Get_list {

    /**
     *    获得对照表的站台编号和站台名
     */
    public static String[] get_id(int m){
        String file="D://shuju//chongqing_stations_nm.txt";
        String[] stations=Station(file);
        String[] get_id=new String[stations.length-1];
        for(int i=0;i<stations.length-1;i++){
            String[] test=stations[i+1].split("\t");
            get_id[i]=test[m].replace("\"","");
        }
        return get_id;
    }

    /**
     *  将对照表里的站台编号和站台名添加为map集合对
     */
    public static  Map<String,String> get_map(String[] number,String[] name) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < number.length; i++) {
            map.put(number[i], name[i]);
        }
        return map;
    }


    /**
     *    获得od表的数据将其表为集合
     */
    public static List<String> shuju(String file,int i){
        String[] stations=Station(file);
        String[] ids=new String[stations.length-1];
        for(int k=0;k<stations.length-1;k++) {
            String[] test = stations[k + 1].split(",");
            ids[k] = test[i];
        }
        List<String> shuju=ChangType(ids);
        return shuju;
    }

    /**
     * 获得od表的数据并将其变成数组
     */

    public static String[] station(String file,int i){
        String[] stations=Station(file);
        String[] station=new String[stations.length-1];
        for(int k=0;k<stations.length-1;k++){
            String[] test=stations[k+1].split(",");
            station[k]=test[i];
        }
        return station;
    }

    /**
     *  创建文件
     */
    public static void write_file(String file)  {
        File files=new File(file);
        if(!files.exists()){
            try{
                files.createNewFile();
                System.out.println("文件" + files.getName() + "不存在已为您创建!");
            }catch (IOException e) {
                System.out.println("创建文件异常!");
                e.printStackTrace();
            }
        }else {
            System.out.println("文件" + files.getName() + "已存在!");
        }

    }


    /**
     *   一行行的读入文件
     */
    public static String[] Station(String file){
        File files=new File(file);
        StringBuilder select_id=new StringBuilder();
        try{
            BufferedReader brd=new BufferedReader(new FileReader(files));
            String str=null;
            while((str=brd.readLine())!=null){
                select_id.append("\r\n".concat(str));
            }
            brd.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String select_ids=select_id.toString();
        String[] stations=select_ids.split("\r\n");
        return stations;
    }
}
