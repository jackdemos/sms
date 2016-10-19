package com.weiwangtong;

import java.util.Map;

/**
 * Created by Administrator on 2016-03-14.
 */
public interface ISms {
    final String USERNAME = "DL-wanglu";
    final String PASSWORD = "82TcXOBS";
    final String URL = "http://cf.51welink.com/submitdata/service.asmx/g_Submit?";

     Map<String,Object> send(String context, String mobile, String postURL, String userName, String password);
}
