package com.autoapitest.FunctionService;


import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class TestInsert extends BaseTest {
    @Test
    public void TestInsert(){

        Integer insertresult=  adfmapper.InsertDetails(777,999,"测试多数据源","/dhhdhdhdhiie/dgtww",1,"插入成功安监局阿加急急急");

        log.info(insertresult.toString());
    }

}
