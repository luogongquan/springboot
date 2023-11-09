package com.example.springboot.video;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;

import static org.bytedeco.opencv.global.opencv_videoio.CAP_PROP_FRAME_WIDTH;
import static org.opencv.videoio.Videoio.CAP_PROP_FPS;
import static org.opencv.videoio.Videoio.CAP_PROP_FRAME_HEIGHT;

public class VideoFaceMask {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // 输入视频文件路径
        String inputVideoPath = "d:\\video\\video.mp4";
        // 输出视频文件路径
        String outputVideoPath = "d:\\video\\output.mp4";
        // 打码图片文件路径
        String maskImagePath = "d:\\video\\mask\\mask.png";

        // 加载视频文件和打码图片文件
        VideoCapture capture = new VideoCapture(inputVideoPath);
        if (!capture.isOpened()) {
            System.out.println("Error: 无法打开输入视频文件");
            return;
        }

        Mat mask = new Mat((int) capture.get(CAP_PROP_FRAME_HEIGHT), (int) capture.get(CAP_PROP_FRAME_WIDTH), CvType.CV_8UC1);
        Mat image = new Mat();
        while (capture.read(image)) {
            // 在视频帧上应用打码图片，并进行颜色反转以创建掩码效果
            MatOfByte byteMat = new MatOfByte();
            Imgcodecs.imencode(".jpg", image, byteMat);
            byte[] bytes = byteMat.toArray();
            Mat imageForMask = Imgcodecs.imdecode(byteMat, Imgcodecs.IMREAD_UNCHANGED);
            MatOfByte byteMatMask = new MatOfByte();
            Imgcodecs.imencode(".png", mask, byteMatMask);
            byte[] bytesMask = byteMatMask.toArray();
            Mat maskForImage = Imgcodecs.imdecode(byteMatMask, Imgcodecs.IMREAD_UNCHANGED);
            Core.bitwise_not(maskForImage, maskForImage); // 反转颜色以创建掩码效果
            Core.bitwise_and(imageForMask, maskForImage, imageForMask); // 应用掩码效果
            Imgcodecs.imwrite("d:\\video\\mask\\temp_image.jpg", imageForMask); // 临时保存处理后的帧，以便进行下一步操作（例如应用人脸识别算法）
            // 继续处理下一帧...
        }
        capture.release();
        // 输出处理后的视频文件，你可以选择在上面的代码中保存每一帧后立即输出视频文件，或者在循环结束后输出所有帧。这个示例只演示了如何在循环结束后输出所有帧。
        Size size = new Size(capture.get(CAP_PROP_FRAME_HEIGHT), capture.get(CAP_PROP_FRAME_WIDTH));
        VideoWriter writer = new VideoWriter(outputVideoPath, -1, capture.get(CAP_PROP_FPS), size);
        while (capture.read(image)) {
            writer.write(image); // 将处理后的帧写入输出视频文件
        }
        writer.release(); // 释放VideoWriter资源并关闭输出视频文件。
    }
}