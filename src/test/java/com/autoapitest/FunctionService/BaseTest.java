package com.autoapitest.FunctionService;

import com.autoapitest.FunctionService.mapper.item.*;
import com.autoapitest.FunctionService.mapper.own.AddFunctionTestMapper;
import com.autoapitest.FunctionService.service.Function.AddFunctionService;
import com.autoapitest.FunctionService.service.Function.DeleteFunctionService;
import com.autoapitest.FunctionService.service.Function.GetFunctionTreeService;
import com.autoapitest.FunctionService.service.Login.LoginService;
import com.autoapitest.FunctionService.service.MealAndComsumer.AddComsumerService;
import com.autoapitest.FunctionService.service.MealAndComsumer.AddMealService;
import com.autoapitest.FunctionService.service.MealAndComsumer.DeleteComsumerService;
import com.autoapitest.FunctionService.service.MealAndComsumer.DeleteMealService;
import com.autoapitest.FunctionService.service.User.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import javax.annotation.Resource;

/**
 *
 * 测试基类
 *
 * @author wing
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public LoginService loginservice;

    @Autowired
    public AddFunctionService addservice;

    @Autowired
    public DeleteFunctionService deletefunctionservice;

    @Autowired
    public AddMealService addmealservice;

    @Autowired
    public DeleteMealService deletemealservice;

    @Autowired
    public AddComsumerService addcomsumerservice;

    @Autowired
    public DeleteComsumerService deletecomsumerservice;

    @Autowired
    public AddRoleService addroleservice;

    @Autowired
    public DeleteRoleService deleteroleservice;

    @Autowired
    public AddUserService adduserservice;

    @Autowired
    public DeleteUserService deleteuserservice;

    @Autowired
    public GetUserInfoService getuserinfoservice;

    @Autowired
    public UpdateUserService updateuserinfoservice;

    @Autowired
    public GetFunctionTreeService getfunctionservice;


    @Resource
    public FunctionMapper fmapper;

    @Resource
    public MealMapper mmpaper;

    @Resource
    public ComsumerMapper cmapper;

    @Resource
    public RoleMapper rmapper;

    @Resource
    public UserMapper umapper;

    @Resource
    public AddFunctionTestMapper adfmapper;


//    @Parameters({"ENV"})
//    @BeforeSuite
//    public String GetEnv(String ENV){
//        log.info("执行环境为："+ ENV);
//        return ENV;
//    }





//    /**
//     * 获取测试数据
//     *
//     * @param elements
//     * @param packageName
//     * @param names
//     * @return
//     */
//    public Object[][] getTestData(List<Element> elements, String packageName, String... names) {
//        Object[][] objects = new Object[elements.size()][];
//        for (int i = 0; i < elements.size(); i++) {
//            Element element = elements.get(i);
//            objects[i] = new Object[names.length];
//            for (int j = 0; j < names.length; j++) {
//                Element temp = element.element(names[j]);
//                if (temp != null && temp.nodeCount() == 1) {
//                    objects[i][j] = temp.getText();
//                } else if (temp != null) {
//                    try {
//                        objects[i][j] = Case.objectFromElement(Class.forName(packageName + names[j]), element);
//                    } catch (ClassNotFoundException e) {
//                        throw new RuntimeException("ClassNotFoundException");
//                    }
//                } else if ("title".equals(names[j])) {
//                    objects[i][j] = Case.getIDTitle(element);
//                }
//            }
//        }
//        return objects;
//    }

    /**
     * 暂停
     *
     * @param millis
     */
    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
