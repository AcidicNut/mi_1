package com.company;

import com.company.Input.InputHandler;

import java.util.ArrayList;

public class Main {
    public static Maze maze;
    public static int NORTH = 1;
    public static int EAST = 2;
    public static int SOUTH = 4;
    public static int WEST = 8;

    public static void main(String[] args) {
        maze = new Maze();
        InputHandler.inputReader(maze);
        maze.setFields();
        boolean found = false;
        int itemindex = 0;
        Coord start = new Coord(0, 0);
        ArrayList<String> output;

        for(int i = 0; i < maze.N; i++){
            for (int j = 0; j < maze.M; j++){
                maze.fields[i][j].hdistance = Coord.heuristicDistance(i,j, maze.itemLocations[itemindex].coord.i, maze.itemLocations[itemindex].coord.j);
            }
        }

        while( itemindex < maze.itemsNumber ){
            start = setActCoord(start);
            maze.fields[start.i][start.j].voltmar = true;
            if (!found&&(start.i != 0)&&((maze.fields[start.i][start.j].description & NORTH) != NORTH)){
                found = stepTo(start.i-1, start.j, start.i, start.j, maze.itemLocations[itemindex].coord.i, maze.itemLocations[itemindex].coord.j);
            }
            if (!found&&(start.j != maze.M)&&(maze.fields[start.i][start.j].description & EAST) != EAST){
                found = stepTo(start.i, start.j+1, start.i, start.j, maze.itemLocations[itemindex].coord.i, maze.itemLocations[itemindex].coord.j);
            }
            if (!found&&(start.j != 0)&&(maze.fields[start.i][start.j].description & WEST) != WEST){
                found = stepTo(start.i, start.j-1, start.i, start.j, maze.itemLocations[itemindex].coord.i, maze.itemLocations[itemindex].coord.j);
            }
            if (!found&&(start.i != maze.N)&&((maze.fields[start.i][start.j].description & SOUTH) != SOUTH)){
                found = stepTo(start.i+1, start.j, start.i, start.j, maze.itemLocations[itemindex].coord.i, maze.itemLocations[itemindex].coord.j);
            }
            if (found) {
                int tmp = itemindex;
                output = extractOutput(maze.itemLocations[itemindex].coord.i, maze.itemLocations[itemindex].coord.j);
                for (int i = output.size()-1; i >=0; i--){
                    System.out.println(output.get(i));
                }
                System.out.println("felvesz");
                itemindex++;
                output.clear();
                maze.resetFields(itemindex);
                maze.fields[maze.itemLocations[tmp].coord.i][maze.itemLocations[tmp].coord.j].distance = 0;
                found = false;
            }
        }

        maze.resetFields();
        maze.fields[maze.itemLocations[maze.itemsNumber-1].coord.i][maze.itemLocations[maze.itemsNumber-1].coord.j].distance = 0;
        while (!found){
            start = setActCoord(start);
            maze.fields[start.i][start.j].voltmar = true;
            if (!found&&(start.i != 0)&&((maze.fields[start.i][start.j].description & NORTH) != NORTH)){
                found = stepTo(start.i-1, start.j, start.i, start.j, maze.N-1, maze.M-1);
            }
            if (!found&&(start.j != maze.M)&&(maze.fields[start.i][start.j].description & EAST) != EAST){
                found = stepTo(start.i, start.j+1, start.i, start.j, maze.N-1, maze.M-1);
            }
            if (!found&&(start.j != 0)&&(maze.fields[start.i][start.j].description & WEST) != WEST){
                found = stepTo(start.i, start.j-1, start.i, start.j, maze.N-1, maze.M-1);
            }
            if (!found&&(start.i != maze.N)&&((maze.fields[start.i][start.j].description & SOUTH) != SOUTH)){
                found = stepTo(start.i+1, start.j, start.i, start.j, maze.N-1, maze.M-1);
            }
            if (found) {
                output = extractOutput(maze.N-1, maze.M-1);
                for (int i = output.size()-1; i >= 0; i--){
                    System.out.println(output.get(i));
                }
            }
        }
        System.out.print("\n");
    }

    public static Coord setActCoord(Coord coord){
        ArrayList<Field> list = new ArrayList<>();
        for (int i = 0; i < maze.N; i++){
            for (int j = 0; j < maze.M; j++){
                if ((maze.fields[i][j].distance > -1)&&
                        (!maze.fields[i][j].voltmar)){
                        list.add(maze.fields[i][j]);
                }
            }
        }

        if (list.size() != 0) {
            Field first = list.get(0);

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).hdistance + list.get(i).distance < first.distance + first.hdistance)
                    first = list.get(i);
            }
            return first.myCoord;
        }
        return new Coord(coord.i,coord.j);
    }

    public static boolean stepTo(int nexti, int nextj, int acti, int actj, int itemi, int itemj){
        if ((nexti >= maze.N) || (nextj >= maze.M) || (nexti < 0) || (nextj < 0)){
            return false;
        }
        if (maze.fields[nexti][nextj].voltmar){
            return false;
        }
        maze.fields[nexti][nextj].stepOn(acti, actj);
        return (nexti == itemi) && (nextj == itemj);
    }

    public static ArrayList<String> extractOutput(int i, int j){
        ArrayList<String> list = new ArrayList<>();
        while (maze.fields[i][j].prev != null){
            list.add(i + " " + j);
            int tmp = i;
            i = maze.fields[tmp][j].prev.i;
            j = maze.fields[tmp][j].prev.j;
        }
        return list;
    }
}