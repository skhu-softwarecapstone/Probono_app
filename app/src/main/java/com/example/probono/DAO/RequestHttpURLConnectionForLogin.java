package com.example.probono.DAO;

import android.webkit.CookieManager;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class RequestHttpURLConnectionForLogin {
    public String request(String _url, String param) {


        HttpURLConnection urlConn = null;


        try {
            URL url = new URL(_url);
            urlConn = (HttpURLConnection) url.openConnection();

            //[2-1] urlConn 설정
            urlConn.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 : POST
            urlConn.setRequestProperty("Accept-Charset", "UTF-8");
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");


            // 바디에 요청 데이터를 JSON형태로 지정
            OutputStream os = urlConn.getOutputStream();
            os.write(param.getBytes("UTF-8")); //출력 스트림에 출력.
            os.flush(); // 출력 스트림을 플러시(비운다)하고 버퍼링 된 모든 출력 바이트를 강제 실행 -> 실제 요청이 전송됨
            os.close(); // 출력 스트림을 닫고 모든 시스템 자원을 해제

            //[2-3] 연결 요청 확인
            // 실패시 null을 리턴하고 메서드를 종료
            if(urlConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println(""+ urlConn.getResponseCode());
                return null;
            }

            //로그인 성공시 실행될 작업
            StringBuilder sb = new StringBuilder("");
            Map<String, List<String>> header = urlConn.getHeaderFields();
            if(header.containsKey("Set-Cookie")){
                List<String> cookies = header.get("Set-Cookie");
                for(String cookie : cookies){
                    sb.append(cookie);
                }
            }
            // 쿠키 분할 및 세션 아이디 값 가져오기
            /*
            String sessionId="";
            for(String cook : sb.toString().split(";")){
                if(cook.split("=")[0].equals("JSESSIONID")){
                    sessionId = cook;
                }
            }*/
            CookieManager cm = CookieManager.getInstance();
            cm.setCookie("sessionId", sb.toString());

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
