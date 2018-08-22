package com.code.repository.study.json;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {
    "result": [ { "brand": [ { "bid": 2001, "bname": "品牌1" }, { "bid": 2002, "bname": "品牌2" } ],
                  "cid": 1,
                  "cname": "品类1"
                },
                { "brand": [ { "bid": 2001, "bname": "品牌1" }, { "bid": 2002, "bname": "品牌2" } ],
                  "cid": 2,
                  "cname": "品类2"
                }
                ]
    }
 * 
 * 类 ObjectToJsonDemo 的实现描述：TODO 类实现描述
 * @author huamo
 * May 22, 20132:36:37 PM
 */
public class ObjectToJsonDemo {

    public static void main(String[] args) {

        List<Map<String, Object>> brandList = new ArrayList<Map<String, Object>>();

        Map<String, Object> brand = new HashMap<String, Object>();
        brand.put("bname", "品牌1");
        brand.put("bid", 2001);
        Map<String, Object> brand2 = new HashMap<String, Object>();
        brand2.put("bname", "品牌2");
        brand2.put("bid", 2002);

        brandList.add(brand);
        brandList.add(brand2);

        Map<String, Object> catMap = new HashMap<String, Object>();
        catMap.put("cname", "品类1");
        catMap.put("cid", 001);
        catMap.put("brand", brandList);

        Map<String, Object> catMap2 = new HashMap<String, Object>();
        catMap2.put("cname", "品类2");
        catMap2.put("cid", 002);
        catMap2.put("brand", brandList);

        List<Map<String, Object>> catList = new ArrayList<Map<String, Object>>();

        catList.add(catMap);
        catList.add(catMap2);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", catList);

        System.out.println(JSONObject.toJSON(map));

    }
}
