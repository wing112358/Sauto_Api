package com.autoapitest.FunctionService.system.utils;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

/**
 * xml处理
 * @author wing
 */

@Slf4j
public class XmlHandler {


    /**
     * 根据类名定位xml文件
     */

    public Document getXMLFile(String className) throws DocumentException, IOException {

        String xmlfile = MessageFormat.format("./src/test/java/com/autoapitest/FunctionService/xmlCase/{0}.xml", className);


        // 创建saxReader对象
        SAXReader reader = new SAXReader();
        // 通过read方法读取一个文件 转换成Document对象
        Document document = reader.read(xmlfile);

        return document;

    }


    /**
     * 根据方法名定位数据组
     */
    public Element getMethod(String className, String methodName, String key) throws DocumentException, IOException {

        Document document = getXMLFile(className);

        //获取根节点
        Element rootElement = document.getRootElement();
        log.info("类节点的名称：：" + rootElement.getName());

        //获取方法对应节点

        Element methodElement = rootElement.element(methodName);
        log.info("方法节点的名称：：" + methodElement.getName());

        //获取特定Iddata节点
        Element dataElement = methodElement.elementByID(key);


        return dataElement;

    }

    /**
     * 提取结果
     */

    public String getresult(String className, String methodName, String key) throws DocumentException, IOException {
        Element datanormal = getMethod(className, methodName, key);

        Element result = datanormal.element("result");
        log.info("这里是结果：" + result.getText());

        String resulttotal= result.getText();


        return resulttotal;


    }

    /**
     * 获取参数并组装
     * @throws DocumentException
     * @throws IOException
     */

    public JSONObject getParam(String className, String methodName, String key) throws DocumentException, IOException {
        Element datanormal = getMethod(className, methodName, key);

        Element paramElement = datanormal.elementByID("param");

        JSONObject json = new JSONObject();
        for (Iterator i = paramElement.elementIterator(); i.hasNext(); ) {
            Element element = (Element) i.next();
            log.info("属性值：" + element.getName()  + "具体值::" + element.getText());
            if (element.getText().contains(",")){
                String s= element.getText();
                List<Integer> list = new StringToList().String2IntList(s);
                json.put(element.getName(),list);
            }
            else{
                json.put(element.getName(),element.getText());
            }



        }
        log.info(json.toString());

        return json;
    }
}
