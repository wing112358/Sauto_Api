package com.autoapitest.FunctionService.params.MealAndComsumer;

import com.autoapitest.FunctionService.params.Common.Request;
import lombok.Data;

/**
 * 删除套餐接口参数维护
 * @author wing
 */

@Data
public class DeleteMealReq extends Request {

    /**
     * 套餐id
     */
    private  Integer setMealId ;


    public DeleteMealReq(Integer setMealId){
        this.setMealId=setMealId;
    }
}
