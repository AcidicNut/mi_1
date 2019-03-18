package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze {
    public int N = 0, M = 0, itemsNumber = 0;
    public List<ArrayList<Integer>> InputArray = new ArrayList<>();
    public Field[][] fields;
    public Item[] itemLocations;

    public void setFields() {
        fields = new Field[N][M];
        itemLocations = new Item[itemsNumber];
        int x = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                fields[i][j] = new Field(InputArray.get(i).get(j), new Coord(i,j));
                if ((fields[i][j].description & 16) == 16) {
                    itemLocations[x] = new Item(new Coord(i, j), Coord.heuristicDistance(0,0,i,j));
                    x++;
                }
            }
        fields[0][0].distance = 0;
    }

    public void resetFields(int itemindex){
        if (itemindex >= itemsNumber)
            return;
        for(int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                fields[i][j].voltmar = false;
                fields[i][j].distance = -1;
                fields[i][j].hdistance = Coord.heuristicDistance(i,j, itemLocations[itemindex].coord.i, itemLocations[itemindex].coord.j);
                fields[i][j].prev = null;
            }
        }
    }

    public void resetFields(){
        for(int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                fields[i][j].voltmar = false;
                fields[i][j].distance = -1;
                fields[i][j].hdistance = Coord.heuristicDistance(i,j, N-1, M-1);
                fields[i][j].prev = null;
            }
        }
    }
}
