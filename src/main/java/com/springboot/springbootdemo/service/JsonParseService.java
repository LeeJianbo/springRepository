package com.springboot.springbootdemo.service;

import com.alibaba.fastjson.JSON;
import com.springboot.springbootdemo.dto.DataSourceDTO;
import com.springboot.springbootdemo.dto.ResDTO;
import com.springboot.springbootdemo.entity.User;
import com.springboot.springbootdemo.util.MapperParam;
import com.springboot.springbootdemo.util.MapperUtils;
import org.apache.catalina.mapper.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * description: JsonParseService
 * date: 2019-09-25 11:37
 * author: Lee
 */
public class JsonParseService {

    public static void main(String[] args) {
      /*  String s = "{\n" +
                "\t\"success\": true,\n" +
                "\t\"msg\": \"\",\n" +
                "\t\"status\": \"0\",\n" +
                "\t\"data\": {\n" +
                "\t\t\"curPageNum\": 1,\n" +
                "\t\t\"total\": 2,\n" +
                "\t\t\"datasourceList\": [{\n" +
                "\t\t\t\t\"logicNodeName\": \"79_机构_前置\",\n" +
                "\t\t\t\t\"dbType\": \"mysql5\",\n" +
                "\t\t\t\t\"updateTime\": \"2018-08-15 17:45:31\",\n" +
                "\t\t\t\t\"name\": \"13_108\",\n" +
                "\t\t\t\t\"id\": \"4bad995823964e5394d932b7d4cb0045\",\n" +
                "\t\t\t\t\"logicNodeId\": \"L_168_1_121_79_d1f29fba09994188a72ad4b5ea5d514b\",\n" +
                "\t\t\t\t\"deployState\": \"部署\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"logicNodeName\": \"80_机构_前置\",\n" +
                "\t\t\t\t\"dbType\": \"mysql5\",\n" +
                "\t\t\t\t\"updateTime\": \"2018-08-08 16:13:12\",\n" +
                "\t\t\t\t\"name\": \"13_121\",\n" +
                "\t\t\t\t\"id\": \"2f29f7b4a527434e9978454b8db35b7e\",\n" +
                "\t\t\t\t\"logicNodeId\": \"L_168_1_121_80_68dadc5911e84ad3a53ef4ff187e25ce\",\n" +
                "\t\t\t\t\"deployState\": \"部署\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"countPerPage\": 10,\n" +
                "\t\t\"totalPageNum\": 1\n" +
                "\t}\n" +
                "}";
        ResDTO resDTO = JSON.parseObject(s, ResDTO.class);
        Object datasourceList = JSON.parseObject(resDTO.getData().toString()).get("datasourceList");
        System.out.println(datasourceList);
        List<DataSourceDTO> dataSourceDTOS = JSON.parseArray(String.valueOf(datasourceList.toString()), DataSourceDTO.class);
        dataSourceDTOS.forEach(d -> System.out.println(d.toString()));*/

        MapperUtils<User> mapper = new MapperUtils<>();
        User user = new User();
        user.setId(56789098765L);
        user.setStatus("1");
        user.setName("ADMIN");
        MapperParam<User> param = new MapperParam<>();
        param.setObj(user);
        ArrayList<String> s = new ArrayList<>();
        //s.add("status");
        param.setIgnoreColumnList(s);
        try {
            Map<String, Object> convert = mapper.convert(param);
            System.out.println(convert);
            System.out.println(convert.get("name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
