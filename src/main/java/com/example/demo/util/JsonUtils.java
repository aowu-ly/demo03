package com.example.demo.util;

import java.io.IOException;

import net.sf.json.JSONArray;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

/****************************************************
 * 创建人：  @author     
 * 创建时间: /
 * 项目名称: demo
 * 文件名称: 
 * 文件描述: @Description
 * 公司名称: 深圳市赢和信息技术有限公司
 *
 * All rights Reserved, Designed By 深圳市赢和信息技术有限公司
 * @Copyright:2016-
 *
 ********************************************************/
public class JsonUtils {
    /** * Jackson技术将JSON字符串转换为JavaBean * * @param jsonStr * @param clazz * @return */
    public static <T> T jsonString2Bean(String jsonStr, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = mapper.readTree(jsonStr);
            T view = mapper.readValue(rootNode, clazz);
            return view;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
