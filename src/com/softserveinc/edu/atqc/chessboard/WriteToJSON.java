/**
 * Class WriteTiJSON helps write output data to JSON formatted files
 *
 * @version  1.0 03 Sep 2014
 * @author   Yermek Kadyrbayev
 */


package com.softserveinc.edu.atqc.chessboard;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class WriteToJSON {
    public static void writeToJSON(String fileName, ArrayList<Figure> path) {
        int step = 0;
        JSONObject jsonObject = new JSONObject();

        for (Figure figure: path) {
            JSONArray point = new JSONArray();
            point.add(figure.getX());
            point.add(figure.getY());

            jsonObject.put(step++, point);
        }

        try {

            FileWriter file = new FileWriter(fileName);
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(jsonObject);
    }

    public static void writeToJSON(String fileName, Chessboard board) {
        JSONObject jsonObject = new JSONObject();
        JSONArray pointsAndStates = new JSONArray();

        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                    pointsAndStates.add("[" + i + "," + j + "," + board.getCellStates(i, j) + "]");
            }
        }

        jsonObject.put("JSON", pointsAndStates);

        try {

            FileWriter file = new FileWriter(fileName);
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(jsonObject);
    }
}
