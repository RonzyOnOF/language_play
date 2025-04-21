package com.languageplay.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;


public class VideoUtils {

    private StringBuilder checkResolutionCommand = new StringBuilder("ffprobe -v error -select_streams v:0 -show_entries stream=width,height -of csv=s=x:p=0 "); 
    private StringBuilder checkMetaDataCommand = new StringBuilder("ffprobe -v quiet -show_entries format_tags=major_brand,encoder -of default=noprint_wrappers=1 ");
    ProcessBuilder pb;
    private File videoFile;
    private String fileName;
    private String resolution;
    private double width;
    private double height;
    

    public VideoUtils(File file) {
        if (file != null) {
            this.videoFile = file;
            this.fileName = file.getName(); 

            String absolutePath = file.getAbsolutePath();
            String quotedFilePath = "\"" + absolutePath + "\"";

            this.checkResolutionCommand.append(quotedFilePath);
            this.checkMetaDataCommand.append(quotedFilePath);
        }
    }


    // returns array of width and height, and returns 1 as 3rd element in array if succesfully retrieved if not 0
    public double[] getVideoResolution() {

        String os = System.getProperty("os.name").toLowerCase();
        String command = checkResolutionCommand.toString();
        String[] commandArray = command.split(" ");  // Split by spaces
        double res[] = new double[3];

        // check if on windows or unix based systems and performs appropriate commands
        if (os.contains("win")) {
            pb = new ProcessBuilder("cmd", "/c", String.join(" ", commandArray));
        } else {
            pb = new ProcessBuilder("bash", "-c", String.join(" ", commandArray));
        }

        // convert the bottom code into some method
        try {

            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                resolution = line;
            }

            process.waitFor();

            String resolutionArr[] = resolution.split("x");

            this.width = Double.parseDouble(resolutionArr[0]);
            this.height = Double.parseDouble(resolutionArr[1]);

            res[0] = this.width;
            res[1] = this.height;
            res[2] = 1;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            res[0] = 0;
            res[1] = 0;
            res[2] = 0;
        }
        return res;

    }

    // checks to see if video resolution is bigger than user's screen size to scale appropriately
    public double[] checkScreenResolution() {
        Rectangle2D bounds = Screen.getPrimary().getBounds();
        double screenWidth = bounds.getWidth();
        double screenHeight = bounds.getHeight();

        double res[] = new double[3];
        res[0] = this.width;
        res[1] = this.height;
        res[2] = 1;

        if (this.width >= screenWidth || this.height >= screenHeight) {
            res = getNewScreenSize(screenWidth, screenHeight);
        }

        return res;
    }


    public double[] getNewScreenSize(double screenWidth, double screenHeight) {
    
        double res[] = new double[3];
        double widthScale = screenWidth / this.width;
        double heightScale = screenHeight / this.height;

        double scaleFactor = Math.min(widthScale, heightScale);
        
        double scaledWidth = this.width * scaleFactor;
        double scaledHeight = this.height * scaleFactor;



        // TO DO: After sizing down, make it scale to get rid of black bars
        // after scaling down the video, if its almost size of user's screen, make it a more smaller
        if ((screenWidth - scaledWidth) < 15 || (screenHeight- scaledHeight) < 15) {
            scaledWidth -= 140;
            scaledHeight -= 140;
        }

        res[0] = scaledWidth;
        res[1] = scaledHeight;
        res[2] = 1;

        return res;
    }
    
}
