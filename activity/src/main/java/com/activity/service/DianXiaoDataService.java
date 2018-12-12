package com.activity.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/15 0015下午 06:23
 */
@Service
public class DianXiaoDataService {

    private static Logger loger = Logger.getLogger(DianXiaoDataService.class);

    /**
     * 同步还款截止日当天应还款的订单
     */
    public void syncDianXiaoNoPay(){
            //如果redis中存在未还款的value,则把订单同步到电销订单表
            List<String> valueList = new ArrayList<>();
            for (int i=1;i<=100;i++){
                valueList.add(String.valueOf(i));
            }

            ExecutorService executorService = Executors.newFixedThreadPool(4);
            if (CollectionUtils.isNotEmpty(valueList)){
                for (String loanId : valueList){
                    System.out.println("nopay_pay_loanId="+loanId);
                    executorService.execute(new DianXiaoNoPayThread(loanId));
                }
            }else {
                loger.error("don_not_get_noPay_value");
            }





    }




}
