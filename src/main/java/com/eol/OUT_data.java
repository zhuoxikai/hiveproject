package com.eol;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class OUT_data {

    public static void main(String[] args) throws IOException {

        File file =new File("D://shuju//afterodata//src.txt");
        Writer out =new FileWriter(file);
        String data="";
       for(int i=1;i<=5;i++){
           data="tunnel download test_od09_3 D://shuju//afterodata//09//od_09_3.txt;";
           data+="\r\n";
       }
        out.write(data);
        out.close();
    }
}
