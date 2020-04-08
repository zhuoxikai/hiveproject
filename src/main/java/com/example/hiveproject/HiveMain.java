package com.example.hiveproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
@RestController
public class HiveMain {
    @Autowired
    HiveApplication hiveApplication;
    @Autowired
   HiveApplicationTest hiveApplicationTest;
    @PostConstruct
    public void main() throws Exception {
        hiveApplicationTest.beforOD("2018-10-01 00:00:01", 15);
        for (int i = 1;i <= 9; i++) {//一天的5分钟分段od
            String day = "";
            for (int j = 0; j <= 23; j++) {
                int k = 0;
                if (j < 10) {
                    while (k < 60) {
                        if (k < 10)
                            day = "2018-10-0" + i + " " + "0" + j + ":0" + k + ":00";
                        else
                            day = "2018-10-0" + i + " " + "0" + j + ":" + k + ":00";
                        Map<String, Integer> map = hiveApplicationTest.afterOD(day, 15);
                        for (String key : map.keySet()) {
                            String keys[] = key.split("\\s+");
                            hiveApplication.insert_od_month(day, j + "", keys[0], keys[1], map.get(key) + "", k + "");
                        }
                        k = k + 5;
                    }
                }
                else {
                    while (k < 60) {
                        if (k < 10)
                            day = "2018-10-0" + i + " " + j + ":0" + k + ":00";
                        else
                            day = "2018-10-0" + i + " " + j + ":" + k + ":00";
                        Map<String, Integer> map = hiveApplicationTest.afterOD(day, 5);
                        for (String key : map.keySet()) {
                            String keys[] = key.split("\\s+");
                           hiveApplication.insert_od_month(day,j+"", keys[0], keys[1], map.get(key)+"",k+"" );
                        }
                        k = k + 5;
                    }
                }
            }
        }
    }
}

