package com.languageplay.products;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.languageplay.SubtitleLine;

// concrete product

// srt format follows: 
    /*
        Time code line
        Start --> End
        Dialogue         
    */
public class SrtSubtitle extends Subtitle {

    public SrtSubtitle(File subFile) {
        super(subFile);
    }

    @Override
    public void readSubtitleFile(File subFile) {
        // after reading start and end time, set this to true so that we know next line we read will be dialogue
        boolean expectDialogue = false;
        double startTime = 0;
        double endTime = 0;
        String dialogue = "";



        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.subFile));

            String line;
            while ((line = reader.readLine()) != null) {

                // check if its only numbers in the line and dont expect it to be dialogue, so can guarantee that the line is time code line 
                if (line.matches("\\d+") && !expectDialogue) {

                    expectDialogue = false;

                } else if (line.contains("-->")) {
                    // get the double startTime and Endtime
                    String startTimeString = line.substring(0, 8) + ":" +  line.substring(9, 12);
                    double hoursStart = Double.parseDouble(startTimeString.substring(0, 2));
                    double minutesStart = Double.parseDouble(startTimeString.substring(3, 5));
                    double secondsStart = Double.parseDouble(startTimeString.substring(6, 8));
                    double miliSecondsStart = Double.parseDouble(startTimeString.substring(9, 12));
    
                    double startTimeSeconds = (hoursStart * (double) 3600) + (minutesStart * (double) 60) + (secondsStart) + (miliSecondsStart / (double) 1000);
                    startTime = startTimeSeconds;
                    
                    String endTimeString = line.substring(17, 25) + ":" + line.substring(26, 29);
                    double hoursEnd = Double.parseDouble(endTimeString.substring(0, 2));
                    double minutesEnd = Double.parseDouble(endTimeString.substring(3, 5));
                    double secondsEnd = Double.parseDouble(endTimeString.substring(6, 8));
                    double miliSecondsEnd = Double.parseDouble(endTimeString.substring(9, 12));
    
                    double endTimeSeconds = (hoursEnd * (double) 3600) + (minutesEnd * (double) 60) + (secondsEnd) + (miliSecondsEnd / (double) 1000);
                    endTime = endTimeSeconds;
                    expectDialogue = true;
    
                    // System.out.println("Start time in seconds: " + startTimeSeconds + "    End time in seconds: " + endTimeSeconds); 
                } else {
                    dialogue = line;
                    // System.out.println(line);
                    SubtitleLine sub = new SubtitleLine(startTime, endTime, dialogue);
                    // System.out.println("start time: " + sub.getStartTime() + "  end time: " + sub.getEndTime());
                    // System.out.println(sub.getDialogue());
                    this.subtitles.add(sub);

                    expectDialogue = false;
                }

               
            }

            reader.close();

            // for (SubtitleLine sub : subtitles) {
            //     System.out.println("start time: " + sub.getStartTime() + "  end time: " + sub.getEndTime());
            //     System.out.println(sub.getDialogue());
            // }
            

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    
}
