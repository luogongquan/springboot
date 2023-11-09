package com.example.springboot.study.word;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class PieChartGenerator {
    public static byte[] generatePieChart() {
        // Create a dataset with sample data
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Category 1", 30);
        dataset.setValue("Category 2", 20);
        dataset.setValue("Category 3", 50);

        // Create a JFreeChart object
        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart Example", // Chart title
                dataset, // Dataset
                true, // Include legend
                true, // Include tooltips
                false // URLs not supported
        );

        byte[] imageBytes = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ChartUtils.writeChartAsPNG(baos, chart, 500, 300);
            imageBytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the image byte array to base64 string
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        return imageBytes;
    }
}