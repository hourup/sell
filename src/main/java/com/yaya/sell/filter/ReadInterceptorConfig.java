package com.yaya.sell.filter;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhushuaixing
 * @Date: 2020/3/19
 */
public class ReadInterceptorConfig {

    private final static String INI_FILE = "interceptor.ini";

    /**
     * 去除ini文件中的注释，以";"或"#"开头
     *
     * @param source
     * @return
     */
    private static String removeIniComments(String source) {
        String result = source;
        if (result.contains(";")) {
            result = result.substring(0, result.indexOf(";"));
        }
        if (result.contains("#")) {
            result = result.substring(0, result.indexOf("#"));
        }
        return result.trim();
    }

    /**
     * 读取 INI 文件，存放到Map中
     *
     * @return Map<sectionName, object> object是一个Map（存放name=value对）或List（存放只有value的properties）
     */
    public Map<String, List<String>> readIniFile() {
        String fileName = getFilePath();
        Map<String, List<String>> listResult = new HashMap<>();
        Map<String, List<String>> result = new HashMap<>();

        if (StringUtils.isEmpty(fileName)) {
            return result;
        }
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String str = null;
            String currentSection = null; //处理缺省的section
            List<String> currentProperties = new ArrayList<>();
            boolean lineContinued = false;
            String tempStr = null;

            //一次读入一行（非空），直到读入null为文件结束
            //先全部放到listResult<String, List>中
            while ((str = reader.readLine()) != null) {
                str = removeIniComments(str).trim(); //去掉尾部的注释、去掉首尾空格

                if (StringUtils.isEmpty(str)) {
                    continue;
                }

                if (lineContinued) {
                    str = tempStr + str;
                }

                //处理行连接符'\'
                if (str.endsWith("\\")) {
                    lineContinued = true;
                    tempStr = str.substring(0, str.length() - 1);
                    continue;
                } else {
                    lineContinued = false;
                }

                //是否一个新section开始了
                if (str.startsWith("[") && str.endsWith("]")) {
                    String newSection = str.substring(1, str.length() - 1).trim();

                    //如果新section不是现在的section，则把当前section存进listResult中
                    if (!newSection.equals(currentSection)) {
                        listResult.put(currentSection, currentProperties);
                        currentSection = newSection;

                        //新section是否重复的section
                        //如果是，则使用原来的list来存放properties
                        //如果不是，则new一个List来存放properties
                        currentProperties = listResult.get(currentSection);
                        if (currentProperties == null) {
                            currentProperties = new ArrayList<>();
                        }
                    }
                } else {
                    currentProperties.add(str);
                }
            }

            //把最后一个section存进listResult中
            listResult.put(currentSection, currentProperties);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {

                }
            }
        }

        //整理拆开name=value对，并存放到MAP中：
        //从listResult<String, List>中，看各个list中的元素是否包含等号“=”，如果包含，则拆开并放到Map中
        //整理后，把结果放进result<String, Object>中
        for (String key : listResult.keySet()) {
            List<String> tempList = listResult.get(key);

            //空section不放到结果里面
            if (tempList == null || tempList.size() == 0) {
                continue;
            }

            result.put(key, listResult.get(key));
        }
        return result;
    }

    private String getFilePath() {
        URL url = getClass().getClassLoader().getResource(INI_FILE);
        if (url != null) {
            return url.getPath();
        }
        return null;
    }
}
