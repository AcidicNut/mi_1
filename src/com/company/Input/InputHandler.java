package com.company.Input;

import com.company.Maze;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class InputHandler {
    public static void inputReader(Maze maze){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String thisLine;
        try {
            while (!((thisLine = br.readLine()).equals(""))) {
                StringTokenizer st = new StringTokenizer(thisLine, " ");

                if (st.countTokens() == 1) {
                    maze.itemsNumber = Integer.valueOf(st.nextToken());
                    break;
                }
                ArrayList<Integer> row = new ArrayList<>();
                while (st.hasMoreElements()) {
                    row.add(Integer.valueOf(st.nextToken()));
                }
                maze.InputArray.add(row);
            }
        } catch (Exception e) {
        }
        maze.N = maze.InputArray.size();
        maze.M = maze.InputArray.get(0).size();
    }
}