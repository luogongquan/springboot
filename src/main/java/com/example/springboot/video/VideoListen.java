package com.example.springboot.video;

import com.sun.deploy.net.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName: VideoListen
 * @author: luogongquan
 * @since: 2023/9/1 10:00
 */
public class VideoListen {
    private static final String FLV_URL = "http://192.168.133.130/live?port=1935&app=hls&stream=lgq";

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpHead = new HttpGet(FLV_URL);

        try {
            CloseableHttpResponse response = httpClient.execute(httpHead);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                System.out.println("Transcoded live stream is being played.");
            } else {
                System.out.println("Transcoded live stream is not being played.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String LIVE_STREAM_URL = "http://192.168.133.130/live?port=1935&app=hls&stream=lgq";
    private static final int TIMEOUT = 5000; // 请求超时时间，单位为毫秒

    public static void main2(String[] args) {
        while (true) {
            try {
                URL url = new URL(LIVE_STREAM_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // 设置请求方法为HEAD，只获取响应头信息
                connection.setRequestMethod("HEAD");
                connection.setRequestMethod("GET");
                // 发送HTTP请求
                connection.connect();

                // 获取响应状态码
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // 视频流正在被播放
                    System.out.println("Video stream is being played");
                } else {
                    // 视频流未被播放
                    System.out.println("Video stream is not being played");
                }

                // 关闭连接
                connection.disconnect();
                Thread.sleep(2000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
