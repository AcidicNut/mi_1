package com.company;

import static java.lang.Math.abs;

public class Coord {
    public int i;
    public int j;

    public Coord(int i, int j){
        this.i = i;
        this.j = j;
    }

    public static int heuristicDistance(int i1, int j1, int i2, int j2){
        return abs(i1 - i2) + abs(j1 - j2);
    }
}
