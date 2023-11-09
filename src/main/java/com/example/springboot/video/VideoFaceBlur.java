package com.example.springboot.video;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

public class VideoFaceBlur {
    public static void main(String[] args) {
        // 加载 OpenCV 库
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // 加载人脸检测器
        CascadeClassifier faceDetector = new CascadeClassifier("d:\\video\\haarcascade_frontalface_alt.xml");

        // 打开视频文件
        VideoCapture videoCapture = new VideoCapture("d:\\video\\video.mp4");

        // 获取视频帧的宽度和高度
        int frameWidth = (int) videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH);
        int frameHeight = (int) videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT);

        // 创建视频编写器
        VideoWriter videoWriter = new VideoWriter("d:\\video\\output_video.mp4", VideoWriter.fourcc('X', '2', '6', '4'), 30, new Size(frameWidth, frameHeight), true);

        // 创建缓冲帧
        Mat frame = new Mat();
        Mat grayFrame = new Mat();
        Mat blurredFrame = new Mat();

        // 逐帧处理视频
        while (videoCapture.read(frame)) {
            // 将帧转换为灰度图像
            Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);

            // 检测人脸
            MatOfRect faceDetections = new MatOfRect();
            faceDetector.detectMultiScale(grayFrame, faceDetections);

            // 对检测到的人脸进行打码处理
            for (Rect rect : faceDetections.toArray()) {
                // 对人脸区域进行高斯模糊处理
                Mat faceRegion = frame.submat(rect);
                Imgproc.GaussianBlur(faceRegion, blurredFrame, new Size(99, 99), 30);

                // 将打码后的人脸区域复制回原图像
                blurredFrame.copyTo(frame.submat(rect));
            }

            // 写入视频帧
            videoWriter.write(frame);
        }

        // 释放资源
        videoCapture.release();
        videoWriter.release();
    }
}
