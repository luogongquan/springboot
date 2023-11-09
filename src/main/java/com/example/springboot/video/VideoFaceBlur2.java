package com.example.springboot.video;


import org.opencv.core.*;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;
/**
 * code @ClassName: VideoFaceBlur2
 * author: luogongquan
 * since: 2023/8/31 10:09
 */
public class VideoFaceBlur2 {
    public static void main(String[] args) {
        // 加载 OpenCV 库
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // 加载人脸检测器
        CascadeClassifier faceDetector = new CascadeClassifier("d:\\video\\haarcascade_frontalface_alt.xml");

        // 加载人脸关键点检测器
        Net faceLandmarkDetector = Dnn.readNetFromTensorflow("d:\\video\\face_landmark.pd");

        // 打开视频文件
        VideoCapture videoCapture = new VideoCapture("d:\\video\\video.mp4");

        // 获取视频帧的宽度和高度
        int frameWidth = (int) videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH);
        int frameHeight = (int) videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT);

        // 创建视频编写器
        VideoWriter videoWriter = new VideoWriter("d:\\video\\output.mp4", VideoWriter.fourcc('X', '2', '6', '4'), 30, new Size(frameWidth, frameHeight), true);

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
                // 提取人脸区域
                Mat faceRegion = frame.submat(rect);

                // 检测人脸关键点
                Mat blob = Dnn.blobFromImage(faceRegion, 1.0, new Size(96, 96), new Scalar(0, 0, 0), true, false);
                faceLandmarkDetector.setInput(blob);
                Mat landmarks = faceLandmarkDetector.forward();

                // 对人脸区域进行打码处理
                // 可以根据关键点位置进行精确的打码处理，例如使用高斯模糊或像素化等方法
                Imgproc.GaussianBlur(landmarks, blurredFrame, new Size(99, 99), 30);

                // 将打码后的人脸区域复制回原图像
                faceRegion.copyTo(frame.submat(rect));
            }

            // 写入视频帧
            videoWriter.write(frame);
        }

        // 释放资源
        videoCapture.release();
        videoWriter.release();
    }
}
