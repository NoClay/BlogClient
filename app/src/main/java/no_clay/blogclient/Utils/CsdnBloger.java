package no_clay.blogclient.Utils;


import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by no_clay on 2017/3/28.
 */

public class CsdnBloger {
    private static final String TAG = "CsdnBloger";
    private RequestQueue mQueue;
    private Context mContext;

    public static final String LOGIN_URL = "https://passport.csdn.net/account/login";

    public CsdnBloger(Context context) {
        mContext = context;
        mQueue = Volley.newRequestQueue(mContext);
    }

//    public Map<String, String> login(final String userName, final String password) throws Exception {
//
//        StringRequest loginRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d(TAG, "onResponse: " + response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d(TAG, "onErrorResponse: " + error.toString());
//                    }
//                }
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<>();
//                map.put("username", userName);
//                map.put("password", password);
//                map.put("lt", "LT-234628-XkqVJ7q1emyLfzqa9vzUOes73gDQjQ");
//                map.put("execution", "e2s1");
//                map.put("_eventId", "submit");
//                return map;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> header = new HashMap<>();
//                header.put("Accept", "text/html,application/xhtml+xml," +
//                        "application/xml;q=0.9,image/webp,*/*;q=0.8");
//                header.put("Accept-Encoding", "gzip, deflate, br");
//                header.put("Accept-Language", "zh-CN,zh;q=0.8");
//                header.put("Host", "passport.csdn.net");
//                header.put("Origin", "https://passport.csdn.net");
//                header.put("Referer", "https://passport.csdn.net/account" +
//                        "/login?from=http%3A%2F%2Fmy.csdn.net%2Fmy%2Fmycsdn");
//                header.put("User-Agent", "Mozilla/5.0 " +
//                        "(Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
//                        "(KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36");
//                return header;
//            }
//        };
//        mQueue.add(loginRequest);
////        Connection conn = Jsoup.connect("https://passport.csdn.net/ajax/accounthandler.ashx");
////        conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
////        conn.header("Accept-Encoding", "gzip, deflate, br");
////        conn.header("Accept-Language", "zh-CN,zh;q=0.8");
////        conn.header("Host", "passport.csdn.net");
////        conn.header("Origin", "https://passport.csdn.net");
////        conn.header("Referer", "https://passport.csdn.net/account/login?from=http%3A%2F%2Fmy.csdn.net%2Fmy%2Fmycsdn");
////        conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36");
////        Connection.Response response = (Connection.Response) conn.ignoreContentType(true)
////                .method(Connection.Method.POST).data(map).execute();
////        Log.d(TAG, "login: 用户登录返回信息：" + response.body().toString());
////        Log.d(TAG, "login: code = " + response.statusCode());
////        Map<String, String> cookies = response.cookies();
////        for (Map.Entry<String, String> temp : cookies.entrySet()) {
////            Log.d(TAG, "login: " + temp.getKey() + " --->  " + temp.getValue());
////        }
//        return null;
//    }

    public Map<String, String> login(final String userName, final String password)
            throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("username", userName);
        map.put("password", password);
        map.put("lt", "LT-234628-XkqVJ7q1emyLfzqa9vzUOes73gDQjQ");
        map.put("execution", "e2s1");
        map.put("_eventId", "submit");

        Connection conn = Jsoup.connect("https://passport.csdn.net/account/login");
        conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.header("Accept-Encoding", "gzip, deflate, br");
        conn.header("Accept-Language", "zh-CN,zh;q=0.8");
        conn.header("Host", "passport.csdn.net");
        conn.header("Origin", "https://passport.csdn.net");
        conn.header("Referer", "https://passport.csdn.net/account/login?from=http%3A%2F%2Fmy.csdn.net%2Fmy%2Fmycsdn");
        conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36");
        Connection.Response response = (Connection.Response) conn.ignoreContentType(true)
                .method(Connection.Method.POST).data(map).execute();
        Log.d(TAG, "login: 用户登录返回信息：" + response.body().toString());
        Log.d(TAG, "login: code = " + response.statusCode());
        Map<String, String> cookies = response.cookies();
        for (Map.Entry<String, String> temp : cookies.entrySet()) {
            Log.d(TAG, "login: " + temp.getKey() + " --->  " + temp.getValue());
        }
        return null;
    }

    public static void publishBlog(Map<String, String> cookies, String title, String content, String tags) throws Exception {
        String url = "http://write.blog.csdn.net/postedit?edit=1&isPub=1";
        Connection conn = Jsoup.connect(url)
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3")
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Host", "write.blog.csdn.net")
                .header("Referer", "http://write.blog.csdn.net/postedit")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0")
                .data("tags", tags)
                .data("titl", title)
                .data("typ", "1")
                .data("cont", content)
                .data("desc", "")
                .data("flnm", "")
                .data("chnl", "0")
                .data("comm", "2")
                .data("level", "0")
                .data("tag2", "")
                .data("artid", "0")
                .data("stat", "publish")
                .ignoreContentType(true);
        for (String cookie : cookies.keySet()) {
            conn.cookie(cookie, cookies.get(cookie));
        }
        String text = conn.post().text();
        System.out.println(text);
    }
}
