package com.examples;


import java.io.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;




class change_id extends get_id {
    public static void main(String[] args) throws IOException {
        String[] number_id = get_id(0);
        String[] name_id = get_id(2);
        for(int i=1;i<=31;i++){
            String file="D://shuju//Guess//09_in.txt";
            String outfile="D://shuju//Guess//09//两路口.txt";
            String[] station_in=station(file,1);
            String[] riqi=station(file,0);
            String[] in_time=station(file,2);
            List<String> station_in1=new ArrayList<String>();
            Map<String,String> map=get_map(number_id,name_id);
            for(Map.Entry<String,String> mtr:map.entrySet()){
                if(mtr.getValue().equals("两路口")){
                    String k=mtr.getKey();
                    for(int m=0;m<station_in.length;m++){
                        if(station_in[m].equals(k)){
                            station_in1.add(station_in[m]);
                        }
                    }
                }
            }
        }
        /*for (int i = 15; i <= 28; i++) {
            if (i < 10) {
                String file1 = "D://shuju//粒度od//sc_10_" + i + ".txt";
                String outfile1 = "D://shuju//twoweek//sc_10_" + i + ".txt";
                String[] station_ins = station(file1, 1);
                String[] station_outs = station(file1, 2);
                Map<String, String> map = get_map(number_id, name_id);

                for (int m = 0; m < station_ins.length; m++) {
                    for (String s : map.keySet()) {
                        if (station_ins[m].equals(s)) {
                            station_ins[m] = map.get(s);
                            break;
                        }
                    }
                }
                for (int m = 0; m < station_outs.length; m++) {
                    for (String s : map.keySet()) {
                        if (station_outs[m].equals(s)) {
                            station_outs[m] = map.get(s);
                            break;
                        }
                    }
                }
                String[] in_id = station(file1, 1);
                String[] out_id = station(file1, 2);
                String[] passengers = station(file1, 3);
                String[] hours = station(file1, 0);
                String[] riqi = station(file1, 4);
                String[] str = new String[in_id.length];

                write_file(outfile1);
                FileOutputStream fos = new FileOutputStream(new File(outfile1));
                OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
                BufferedWriter bw = new BufferedWriter(osw);
                for (int s = 0; s < str.length; s++) {
                    str[s] = riqi[s] + "," + hours[s] + "," + in_id[s] + "," + station_ins[s] + "," + out_id[s] + ","
                            + station_outs[s] + "," + passengers[s];
                    bw.write(str[s].concat("\r\n"));
                }
                bw.close();
                osw.close();
                fos.close();
            } else {
                String file1 = "D://shuju//粒度od//sc_10_" + i + ".txt";
                String outfile1 = "D://shuju//twoweek//sc_10_" + i + ".txt";
                String[] station_ins = station(file1, 1);
                String[] station_outs = station(file1, 2);
                Map<String, String> map = get_map(number_id, name_id);

                for (int m = 0; m < station_ins.length; m++) {
                    for (String s : map.keySet()) {
                        if (station_ins[m].equals(s)) {
                            station_ins[m] = map.get(s);
                            break;
                        }
                    }
                }
                for (int m = 0; m < station_outs.length; m++) {
                    for (String s : map.keySet()) {
                        if (station_outs[m].equals(s)) {
                            station_outs[m] = map.get(s);
                            break;
                        }
                    }
                }
                String[] in_id = station(file1, 1);
                String[] out_id = station(file1, 2);
                String[] passengers = station(file1, 3);
                String[] hours = station(file1, 0);
                String[] riqi = station(file1, 4);
                String[] str = new String[in_id.length];

                write_file(outfile1);
                FileOutputStream fos = new FileOutputStream(new File(outfile1));
                OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
                BufferedWriter bw = new BufferedWriter(osw);
                for (int s = 0; s < str.length; s++) {
                    str[s] = riqi[s] + "," + hours[s] + "," + in_id[s] + "," + station_ins[s] + "," + out_id[s] + ","
                            + station_outs[s] + "," + passengers[s];
                    bw.write(str[s].concat("\r\n"));
                }
                bw.close();
                osw.close();
                fos.close();
            }
        }*/
       /*for(int i=10;i<=14;i++){
            String file1="D://shuju//afterodata//afterodata//10//od_10_"+i+".txt";
            String outfile1="D://shuju//afterodata//afterodata//10//sc_10_"+i+".txt";
            String[] station_in=station(file1,2);
            String[] station_out=station(file1,4);
            List<String> id1 = shuju(file1,0);
            List<String> kind1 =  shuju(file1,1);
            List<String> number_in1=  shuju(file1,2);
            List<String> in_time1 = shuju(file1,3);
            List<String> number_out1 =  shuju(file1,4);
            List<String> out_time1 =  shuju(file1,5);
            List<String> station_ins=ChangType(station_in);
            List<String> station_outs=ChangType(station_out);

             String[] kind=Compare_types(id1,in_time1,kind1);
            String[] number_in=Compare_types(id1,in_time1,number_in1);
            String[] number_out=Compare_types(id1,in_time1,number_out1);
            String[] out_time=Compare_types(id1,in_time1,out_time1);
            String[] station_in1=Compare_types(id1,in_time1,station_ins);
            String[] station_out1=Compare_types(id1,in_time1,station_outs);
           /*Map<String, String> map = get_map(number_id, name_id);

           for (int m = 0; m < station_in1.length; m++) {
               for (String s : map.keySet()) {
                   if (station_in1[m].equals(s)) {
                       station_in1[m] = map.get(s);
                       break;
                   }
               }
           }
           for (int m = 0; m < station_out1.length; m++) {
               for (String s : map.keySet()) {
                   if (station_out1[m].equals(s)) {
                       station_out1[m] = map.get(s);
                       break;
                   }
               }
           }
            String[] id=Compare_id(id1,in_time1);
            List<String> id2= shuju(file1,0);
            String[] in_time=Compare_time(id2,in_time1);
            String[] str=new String[id.length];

            write_file(outfile1);
            FileOutputStream fos = new FileOutputStream(new File(outfile1));
            OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
            BufferedWriter bw = new BufferedWriter(osw);
            for (int s = 0; s < str.length; s++) {
                str[s] = id[s] + kind[s]+ "," + number_in[s]  + "," + in_time[s] + ","
                        + number_out[s] +"," + out_time[s];
                bw.write(str[s].concat("\r\n"));
            }
            bw.close();
            osw.close();
            fos.close();
       }*/
    }
}

