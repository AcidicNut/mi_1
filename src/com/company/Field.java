package com.company;

import static com.company.Main.maze;

public class Field {
    public int distance = -1;
    public int hdistance = 0;
    public Coord prev;
    public int description;
    public boolean voltmar = false;
    public Coord myCoord;

    public Field(int desc, Coord c){
        description = desc;
        myCoord = c;
    }

    public void stepOn( int previ, int prevj){
        if ((distance == -1) || ((maze.fields[previ][prevj].distance + maze.fields[previ][prevj].hdistance) < (distance + hdistance))){
            distance = maze.fields[previ][prevj].distance + 1;
            prev = new Coord(previ, prevj);
        }
    }
}
