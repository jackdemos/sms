package com.weiwangtong;

/**
 * Created by Administrator on 2016-03-15.
 */
public class SmsFactory{
    public static  <T extends ISms> T createSms(Class<T> c) {
        ISms sms = null;
        try {
            sms = (ISms)Class.forName(c.getName()).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (T) sms;
    }
}
