package com.example.springboot.video;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

public class videoFaceCensorship {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Load the classifier
        CascadeClassifier faceCascade = new CascadeClassifier("d:\\video\\haarcascade_frontalface_default.xml");

        // Open the video file
        VideoCapture videoCapture = new VideoCapture("d:\\video\\video.mp4");

        // Check if the video file is opened successfully
        if (!videoCapture.isOpened()) {
            System.out.println("Error opening video file");
            return;
        }

        // Get the frames per second (fps) of the video
        double fps = videoCapture.get(Videoio.CAP_PROP_FPS);

        // Create a VideoWriter object to save the output video
        VideoWriter videoWriter = new VideoWriter("d:\\video\\output_video.mp4", VideoWriter.fourcc('X', '2', '6', '4'), fps, new Size(30, 30));

        // Create a Mat object to store the video frame
        Mat frame = new Mat();

        // Process each frame of the video
        while (videoCapture.read(frame)) {
            // Convert frame to grayscale for face detection
            Mat grayFrame = new Mat();
            Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
            Imgproc.equalizeHist(grayFrame, grayFrame);

            // Detect faces in the frame
            MatOfRect faces = new MatOfRect();
            faceCascade.detectMultiScale(grayFrame, faces);

            // Apply censorship to the detected faces
            for (Rect rect : faces.toArray()) {
                Mat faceRegion = frame.submat(rect);
                Imgproc.rectangle(frame, rect.tl(), rect.br(), new Scalar(0, 0, 0), -1);
            }

            // Write the frame with censored faces to the output video
            videoWriter.write(frame);
        }

        // Release the video capture and writer objects
        videoCapture.release();
        videoWriter.release();
    }
}