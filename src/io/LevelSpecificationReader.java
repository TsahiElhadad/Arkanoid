
package io;


import geometry.Point;
import levels.LevelGeneretor;
import levels.LevelInformation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LevelSpecificationReader {
    private double x = -1;
    private double y = -1;


    public List<LevelInformation> fromReader(java.io.Reader reader) {
        try {
            String nameLevel;
            List<LevelInformation> lstLevelInfo = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(reader);
            List<String> levelNameList = new ArrayList<>();
            List<String> velocityList = new ArrayList<>();
            List<String> backgroundList = new ArrayList<>();
            List<Integer> paddleSpeedList = new ArrayList<>();
            List<Integer> paddleWidthList = new ArrayList<>();
            List<String> blocksList = new ArrayList<>();
            List<Point> locationBlocks = new ArrayList<>();
            List<Integer> rowHeightList = new ArrayList<>();
            List<Integer> numblocksList = new ArrayList<>();
            // get the first line from file.
//            // check start of level.
//            if (line.equals("START_LEVEL")) {
//                line = bufferedReader.readLine();
//                // get the game level
//
//            }
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("level_name")) {
                    int temp = line.indexOf(":");
                    nameLevel = line.substring(temp + 1);
                    levelNameList.add(nameLevel);
                }
                if (line.startsWith("ball_velocities")) {
                    int temp = line.indexOf(":");
                    velocityList.add(line.substring(temp + 1));
                }
                if (line.startsWith("background")) {
                    int temp = line.indexOf(":");
                    backgroundList.add(line.substring(temp + 1));
                }
                if (line.startsWith("paddle_speed")) {
                    int temp = line.indexOf(":");
                    paddleSpeedList.add(Integer.parseInt(line.substring(temp + 1)));
                    //System.out.println(paddleSpeedList.get(0));
                }
                if (line.startsWith("paddle_width")) {
                    int temp = line.indexOf(":");
                    paddleWidthList.add(Integer.parseInt(line.substring(temp + 1)));
                    //System.out.println(paddleWidthList.get(0));
                }
                if (line.startsWith("block_definitions")) {
                    int temp = line.indexOf(":");
                    blocksList.add(line.substring(temp + 1));
                }
                if (line.startsWith("blocks_start_x") || line.startsWith("blocks_start_y:")) {
                    if (line.startsWith("blocks_start_x")) {
                        int temp = line.indexOf(":");
                        this.x = Double.parseDouble(line.substring(temp + 1));
                    }
                    if (line.startsWith("blocks_start_y:")) {
                        int temp = line.indexOf(":");
                        this.y = Double.parseDouble(line.substring(temp + 1));
                    }
                }
                if (line.startsWith("row_height")) {
                    int temp = line.indexOf(":");
                    rowHeightList.add(Integer.parseInt(line.substring(temp + 1)));
                }
                if (line.startsWith("num_blocks")) {
                    int temp = line.indexOf(":");
                    numblocksList.add(Integer.parseInt(line.substring(temp + 1)));
                }
                // USE METHOD
//                if (line.startsWith("START_BLOCKS")) {
//                    while ()
//                }
                if (this.x >= 0 && this.y >= 0) {
                    locationBlocks.add(new Point(this.x, this.y));
                    // for next location blocks
                    this.x = -1;
                    this.y = -1;
                }
            }
            // Add the locations blocks.
//            for(int i = 0; i < levelNameList.size(); i++) {
//                // add new levelInformation to list.
//                lstLevelInfo.add(new LevelGeneretor(numblocksList.get(i), velocityList.get(i),
//                        paddleSpeedList.get(i), paddleWidthList.get(i), levelNameList.get(i),
//                        backgroundList.get(i), numblocksList.get(i));
//            }
            return lstLevelInfo;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
