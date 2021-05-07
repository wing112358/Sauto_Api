package com.autoapitest.FunctionService.Function.addFunction;

import com.alibaba.fastjson.JSONObject;
import com.autoapitest.FunctionService.LoginTest;
import com.autoapitest.FunctionService.entity.FunctionEntity;
import com.autoapitest.FunctionService.params.Common.Environment;
import com.autoapitest.FunctionService.params.Function.AddFunctionReq;
import com.autoapitest.FunctionService.params.Function.DeleteFunctionReq;
import com.autoapitest.FunctionService.system.utils.JSON;
import com.autoapitest.FunctionService.system.utils.Tools;
import com.autoapitest.FunctionService.system.utils.XmlHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.dom4j.DocumentException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 * 新增权限接口
 *
 * @author wing
 */
@Slf4j
public class AddFuncitonTest extends LoginTest {

    public static final String SERVER_GROUPS = "SERVER_GROUPS";
    public List<Integer> list=new ArrayList<>();
    Header[] loginheader ;

    /**
     * 登录获取header
     */

    @Parameters({"ENV"})
    @BeforeClass
    public Header[] getHeaders(String ENV){

        this.loginheader = new LoginTest().loginNormal("admindev","8888","123456",0,ENV);

        return loginheader;

    }

    //塞ID进list
    @AfterClass(alwaysRun = true)
    public void setFunctionId(ITestContext ctx) {
        ctx.setAttribute(SERVER_GROUPS, this.list);
    }

    /**
     * 增加同级权限
     * @param param
     * @param result
     */

    @Parameters({"ENV"})
    @Test( dataProvider = "addfunction")
    public List<Integer> addFunctionNormal(AddFunctionReq param, String result,String ENV) throws IOException {

        log.info("-----------------------------------------开始发起addFunctionNormal请求--------------------------------------------------------");

        //清理历史数据
        Integer deleteflag=deletedata(param,loginheader,ENV);

        //用于存放结果
        JSONObject response = null;

        //发送请求
        try {

            response = addservice.addfunction(param, result,loginheader, ENV);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //判断返回
        if ("success".equals(JSON.getString(response, "massage"))) {

            log.info("新增权限成功");
        }

        //检查数据库插入情况
        Integer checkadd= checkAddSuccess(param);
        Assert.assertEquals(checkadd.intValue(),1,"插入成功，且name一致");
        //获取id
        Long id = getId(param);


        log.info("-----------------------------------------结束addFunctionNormal请求--------------------------------------------------------");

        Integer intid=new Tools().longToInteger(id);

        this.list.add(intid);

        return this.list;



    }

    @DataProvider(name = "addfunction")
    public Object[][] addFunctionData() throws IOException, DocumentException {

        //获取xml的返回
        String result = new XmlHandler().getresult(this.getClass().getSimpleName(),"addFunctionNormal","正常测试");
        //获取xml的param
        JSONObject jsonparam= new XmlHandler().getParam(this.getClass().getSimpleName(),"addFunctionNormal","正常测试");
        //处理拿到的参数
        AddFunctionReq addfunctionparam = new AddFunctionReq(jsonparam.getIntValue("currentId") ,jsonparam.getString("name"),jsonparam.getIntValue("pid"),jsonparam.getIntValue("type"),jsonparam.getString("url"));
        //塞进dataprovider
        return new Object[][]{
            {
                addfunctionparam,
                result,
            },
        };
    }


    /**
     * 增加子级页面
     * @param param
     * @param result
     */

    @Parameters({"ENV"})
    @Test(dataProvider = "addpage",dependsOnMethods = "addFunctionNormal")
    public List<Integer> addPage(AddFunctionReq param,String result,String ENV) throws IOException {

        log.info("-----------------------------------------开始发起addPage请求--------------------------------------------------------");
        //清理历史数据
        Integer deleteflag=deletedata(param,loginheader,ENV);

        //用于存放结果
        JSONObject response = null;
//        Header[]  loginheader = new LoginTest().loginNormal("admindev","8888","123456",0);

        //发送请求
        try {

            response = addservice.addfunction(param, result,loginheader, ENV );
        } catch (IOException e) {
            e.printStackTrace();
        }
        //判断返回
        if ("success".equals(JSON.getString(response, "massage"))) {

            log.info("新增页面成功");
        }

        //检查数据库插入情况
        Integer checkadd= checkAddSuccess(param);
        Assert.assertEquals(checkadd.intValue(),1,"插入成功，且name一致");
        //获取id
        Long id = getId(param);

        log.info("-----------------------------------------结束addPage请求--------------------------------------------------------");

        Integer intid=id.intValue();
        this.list.add(intid);

        return this.list;

    }
    @DataProvider(name = "addpage")
    public Object[][] addPageData() throws IOException, DocumentException {

        //获取之前增加的父节点id
        //获取父节点参数
        JSONObject functionparam= new XmlHandler().getParam(this.getClass().getSimpleName(),"addFunctionNormal","正常测试");
        //获取父节点的url
        String furl = functionparam.getString("url");
        //获取对应id
        Integer pid = getPidByUrl(furl);


        //获取xml的返回
        String result = new XmlHandler().getresult(this.getClass().getSimpleName(),"addPage","正常测试");
        //获取xml的param
        JSONObject jsonparam= new XmlHandler().getParam(this.getClass().getSimpleName(),"addPage","正常测试");
        //处理拿到的参数
        AddFunctionReq addfunctionparam = new AddFunctionReq(null ,jsonparam.getString("name"),pid,jsonparam.getIntValue("type"),jsonparam.getString("url"));
        //塞进dataprovider
        return new Object[][]{
                {
                        addfunctionparam,
                        result,
                },
        };
    }

    /**
     * 增加子级按钮
     * @param param
     * @param result
     */
    @Parameters({"ENV"})
    @Test(dataProvider = "addbutton",dependsOnMethods = "addPage")
    public List<Integer> addButton(AddFunctionReq param,String result,String ENV) throws IOException {


        log.info("-----------------------------------------开始发起addButton请求--------------------------------------------------------");
        //清理历史数据
        Integer deleteflag=deletedata(param,loginheader,ENV);

        //用于存放结果
        JSONObject response = null;
//        Header[]  loginheader = new LoginTest().loginNormal("admindev","8888","123456",0);

        //发送请求
        try {

            response = addservice.addfunction(param, result,loginheader, ENV);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //判断返回
        if ("success".equals(JSON.getString(response, "massage"))) {

            log.info("新增页面成功");
        }

        //检查数据库插入情况
        Integer checkadd= checkAddSuccess(param);
        Assert.assertEquals(checkadd.intValue(),1,"插入成功，且name一致");

        //获取id
        Long id = getId(param);
        log.info("-----------------------------------------结束addButton请求--------------------------------------------------------");

        Integer intid=id.intValue();
        this.list.add(intid);

        return this.list;


    }
    @DataProvider(name = "addbutton")
    public Object[][] addButtonData() throws IOException, DocumentException {

        //获取之前增加的父节点id
        //获取父节点参数
        JSONObject functionparam= new XmlHandler().getParam(this.getClass().getSimpleName(),"addPage","正常测试");
        //获取父节点的url
        String furl = functionparam.getString("url");
        //获取对应id
        Integer pid = getPidByUrl(furl);


        //获取xml的返回
        String result = new XmlHandler().getresult(this.getClass().getSimpleName(),"addButton","正常测试");
        //获取xml的param
        JSONObject jsonparam= new XmlHandler().getParam(this.getClass().getSimpleName(),"addButton","正常测试");
        //处理拿到的参数
        AddFunctionReq addfunctionparam = new AddFunctionReq(null ,jsonparam.getString("name"),pid,jsonparam.getIntValue("type"),jsonparam.getString("url"));
        //塞进dataprovider
        return new Object[][]{
                {
                        addfunctionparam,
                        result,
                },
        };

    }


    /**
     * 根据url获取pid
     *
     */
    public Integer getPidByUrl(String url){
        Integer pid = fmapper.getPidByUrl(url);

        return pid;
    }

    /**
     * 校验是否添加成功
     * @param request
     * @return
     */

    public Integer checkAddSuccess(AddFunctionReq request){
        Integer isdelete = 0;
        String responseurl = request.getUrl();
        List<FunctionEntity> function =fmapper.getListByUrl(responseurl,isdelete);

        String dataurl=function.get(0).getUrl();
        Assert.assertEquals(dataurl,responseurl);
        return 1;

    }

    /**
     * 根据url获取Id
     * @param request
     * @return
     */
    public  Long getId(AddFunctionReq request){
        Long id;
        String requesturl = request.getUrl();
        List<FunctionEntity> function = fmapper.getListByUrl(requesturl,0);

        id = function.get(0).getId();


        return id;
    }

    /**
     * 根据url清理已存数据
     * @param request
     * @return
     */

    @Parameters({"ENV"})
    @Test
    public Integer deletedata(AddFunctionReq request,Header[] headers,String ENV) throws IOException {

        //根据url获取id
        Integer functionId = fmapper.getPidByUrl(request.getUrl());
        //整合成list
        List<Integer> list = new ArrayList<>();

        list.add(functionId);

        //处理删除参数
        DeleteFunctionReq deleteparameter =  new DeleteFunctionReq(list);

        JSONObject deleteresponse = deletefunctionservice.deletefunction(deleteparameter,"操作成功",headers,ENV);

        Integer flag=0;

       Integer status = JSON.getInt(deleteresponse,"status");

       if (status==200){
           flag=1;
           log.info("删除标志：：："+flag);
       }



        return flag;


    }
}
