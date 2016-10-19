package com.weiwangtong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-03-14.
 */
public class TestSms {
    public static void main(String[] args) {
        ISms sms = SmsFactory.createSms(WeiWangTongSms.class);
        //String context = "您的验证码是：954163。请不要把验证码泄露给其他人。【微网通联】";
        List<String> mobileList = new ArrayList<String>();
        String context = "您的订单货物已于2015-3-30发出，快递单号为：924CB45623，请注意查收【微网通联】";
        String mobiles = "18808057922";//,15038271743";
        mobileList = Arrays.asList(mobiles.split(","));
       /* mobileList.add("18808057922");*/
        for(String mobile:mobileList) {
            Map<String, Object> result = sms.send(context, mobile, ISms.URL, ISms.USERNAME, ISms.PASSWORD);
            System.out.println("状态:" + result.get("state") + "\t 消息:" + result.get("msg"));
        }
    }
}
