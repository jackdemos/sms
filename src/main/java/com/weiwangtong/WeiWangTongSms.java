package com.weiwangtong;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016-03-14.
 */
public class WeiWangTongSms implements ISms {

    public Map<String, Object> send(String context, String mobile, String postUrl, String userName, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            URL url = new URL(postUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Connection", "Keep-Alive");
            urlConnection.setUseCaches(false);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Length", "" + context.length());
            String postData = "sname=DL-wanglu&spwd=82TcXOBS&scorpid=&sprdid=1012888&sdst=" + mobile + "&smsg=" + URLEncoder.encode(context, "UTF-8");
            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();
            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed");
                map.put("state", 500);
                map.put("msg", "网络异常");
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
            String line, result = "";
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            map = analysisXML(result);

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return map;
    }

    private static Map<String, Object> analysisXML(String result) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Document document = DocumentHelper.parseText(result);
            Element rootElement = document.getRootElement();
            String state = rootElement.elementTextTrim("State");
            String msg = rootElement.elementTextTrim("MsgState");
            map.put("state", state);
            map.put("msg", msg);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }
}
