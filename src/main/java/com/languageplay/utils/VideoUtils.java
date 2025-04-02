package com.languageplay.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class VideoUtils {

    private StringBuilder checkResolutionCommand = new StringBuilder("ffprobe -v error -select_streams v:0 -show_entries stream=width,height -of csv=s=x:p=0 "); 
    private StringBuilder checkMetaDataCommand = new StringBuilder("ffprobe -v quiet -show_entries format_tags=major_brand,encoder -of default=noprint_wrappers=1 ");
    ProcessBuilder pb;
    private File videoFile;
    private String fileName;
    private String resolution;
    private int width;
    private int height;
    

    public VideoUtils(File file) {
        if (file != null) {
            this.videoFile = file;
            this.fileName = file.getName();            
            this.checkResolutionCommand.append(file.getAbsolutePath());
            this.checkMetaDataCommand.append(file.getAbsolutePath());
        }
    }


    // returns array of width and height, and returns 1 as 3rd element in array if succesfully retrieved if not 0
    public int[] getVideoResolution() {

        String os = System.getProperty("os.name").toLowerCase();
        String command = checkResolutionCommand.toString();
        String[] commandArray = command.split(" ");  // Split by spaces
        int res[] = new int[3];

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
            this.width = Integer.parseInt(resolutionArr[0]);
            this.height = Integer.parseInt(resolutionArr[1]);
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

    // public boolean checkValidVideoFormat(File file) {



    // }

    // public void 
    
}
