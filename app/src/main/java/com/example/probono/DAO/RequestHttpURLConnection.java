package com.example.probono.DAO;

import android.content.ContentValues;
import android.webkit.CookieManager;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class RequestHttpURLConnection {
    public String request(String _url, JSONObject json) {


        HttpURLConnection urlConn = null;


        try {
            URL url = new URL(_url);
            urlConn = (HttpURLConnection) url.openConnection();

            //[2-1] urlConn 설정
            urlConn.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 : POST
            urlConn.setRequestProperty("Accept-Charset", "UTF-8");
            urlConn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            // 현제 살아있는 세션이 존재한다면 세션 아이디값을 쿠키로 전달
            CookieManager cm = CookieManager.getInstance();
            String sessionId = cm.getCookie("sessionId");
            if(sessionId != null){
                urlConn.setRequestProperty("Cookie", sessionId);
            }

            // 바디에 요청 데이터를 JSON형태로 지정
            OutputStream os = urlConn.getOutputStream();
            if(json!=null)
                os.write(json.toString().getBytes("UTF-8")); //출력 스트림에 출력.
            os.flush(); // 출력 스트림을 플러시(비운다)하고 버퍼링 된 모든 출력 바이트를 강제 실행 -> 실제 요청이 전송됨
            os.close(); // 출력 스트림을 닫고 모든 시스템 자원을 해제

            //[2-3] 연결 요청 확인
            // 실패시 null을 리턴하고 메서드를 종료
            if(urlConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println(""+ urlConn.getResponseCode());
                return null;
            }

            

            // [2-4] 읽어온 결과물 리턴
            // 요청한 URL의 출력물을 BufferReader로 받는다.
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));

            // 출력물의 라인과 그 합에 대한 변수
            String line;
            String page = "";

            // 라인을 받아와 합친다
            while((line = reader.readLine()) != null ) {
                page += line;
            }
            System.out.println("----------------------------");
            System.out.print(page);
            System.out.println("----------------------------");
            return page;



            // catch
        } catch(MalformedURLException e) { //for URL.
            e.printStackTrace();

        } catch(IOException e) { // for openConnection
            e.printStackTrace();
        } finally {
            if(urlConn != null)
                urlConn.disconnect();
        }

        return null;
    }
}
