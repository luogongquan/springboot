package com.example.springboot.video;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class StreamMonitor {

    private static final String STREAM_URL = "http://192.168.133.130/live?port=1935&app=hls&stream=lgq"; // 直播流的URL
    private static final String CLOSE_STREAM_URL = "http://example.com/close"; // 关闭直播流的URL

    private static final int TIMEOUT = 5; // 超时时间

    public static void main(String[] args) throws IOException, InterruptedException {
        String streamUrl = "rtmp://example.com/live/stream"; // 直播流的URL
        Socket socket = new Socket(STREAM_URL, 80); // 连接到nginx-http-flv-module的端口

        InputStream inputStream = socket.getInputStream();

        byte[] buffer = new byte[1024];
        int bytesRead;
        int totalBytes = 0;

        // 读取直播流数据，如果超过超时时间仍未读取到数据，则关闭直播流
        while ((bytesRead = inputStream.read(buffer)) != -1 && totalBytes < buffer.length) {
            totalBytes += bytesRead;
            if (totalBytes == buffer.length) {
                // 如果读取到的数据已经达到buffer的大小，说明直播流已经被播放了
                break;
            } else if (totalBytes < buffer.length ) {
                // 如果读取到的数据小于buffer的大小，并且已经超过了超时时间，说明直播流未被播放，关闭直播流
                socket.close();
                break;
            }
        }
    }
}
