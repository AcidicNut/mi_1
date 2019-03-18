package com.company;

public class Item implements Comparable<Item>{
    public Coord coord;
    public int distance;

    public Item(Coord coord, int d){
        this.coord = coord;
        distance = d;
    }

    @Override
    public int compareTo(Item o) {
        return distance - o.distance;
    }
}
