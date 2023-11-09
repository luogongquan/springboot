package com.example.springboot.video;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

public class FaceBlur {
    public static void main(String[] args) {
        //VideoToImage("d:\\video\\video.mp4","d:\\video",2);
        video();
    }

    private static void video() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // 加载人脸检测器
        CascadeClassifier faceDetector = new CascadeClassifier("d:\\video\\haarcascade_frontalface_default.xml");

        // 加载视频
        VideoCapture videoCapture = new VideoCapture("d:\\video\\video.mp4");

        if (!videoCapture.isOpened()) {
            System.out.println("无法打开视频文件");
            return;
        }
        double fps = videoCapture.get(Videoio.CAP_PROP_FPS);

        VideoWriter videoWriter = new VideoWriter("d:\\video\\output_video.mp4", VideoWriter.fourcc('X', '2', '6', '4'), fps, new Size(640, 480));
        Mat frame = new Mat();
        Mat grayFrame = new Mat();
        long sum = 0,currentFrame=0; // 定义保存图片数
        while (videoCapture.read(frame)) {
            // 将帧转换为灰度图像
            Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);

            // 检测人脸
            MatOfRect faceDetections = new MatOfRect();
            faceDetector.detectMultiScale(grayFrame, faceDetections);

            // 对每个检测到的人脸进行打码

            for (Rect rect : faceDetections.toArray()) {
               // if (currentFrame % 2 == 0) {
                    Imgproc.blur(frame.submat(rect), frame.submat(rect), new Size(30, 30));
                    String fname = sum + ".jpg";
                    Imgcodecs.imwrite( "d:\\video\\pic1\\" + fname, frame); // 将帧转成图片输出
                    sum++;
                //}
                currentFrame++;
            }
            videoWriter.write(frame);
            // 显示视频帧
            // 这里使用你喜欢的方法来显示视频帧，比如JavaFX、Swing等

        }

        videoCapture.release();
        videoWriter.release();
    }


    public static void VideoToImage(String videoPath, String imagePath, int num) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // get video
        VideoCapture capture = new VideoCapture(videoPath);
        double totalFrameNumber = capture.get(7);// 获取帧数
        System.out.println("总帧数：" + totalFrameNumber);
        Mat frame = new Mat(); // 定义一个Mat变量，用来存放存储每一帧图像
        boolean flags = true; // 循环标志位
        long currentFrame = 0; // 定义当前帧
        long sum = 0; // 定义保存图片数

        try {
            while (flags) {
                capture.read(frame); // 读取视频每一帧
                if (currentFrame % num == 0) {
                    String fname = sum + ".jpg";
                    Imgcodecs.imwrite(imagePath + "/" + fname, frame); // 将帧转成图片输出
                    sum++;
                }
                if (currentFrame >= totalFrameNumber) {
                    flags = false;
                }
                currentFrame++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            capture.release();
            System.out.println("视频解析结束！");
        }


    }

}