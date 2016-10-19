package com.weiwangtong;

/**
 * Created by Administrator on 2016-03-15.
 */
public abstract class AbstractSmsFactory {
    public abstract   <T extends ISms> T createSms(Class<T> c);
}
