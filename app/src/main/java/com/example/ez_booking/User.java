package com.example.ez_booking;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;

enum TransportMode {
    BUS("bus"), TRAIN("train");

    private String value;

    TransportMode(String value) {
        this.value = value;
    }

    String value() {
        return this.value;
    }
}

class User {
    private String name;

    public User() {

    }

    public String getName() {
        return this.name;
    }
}

class Schedule {
    private int capacity;
    private String startTime;
    private ArrayList<DocumentReference> stops;

    public Schedule() {

    }

    public int getCapacity() {
        return capacity;
    }

    public String getStartTime() {
        return startTime;
    }

    public ArrayList<DocumentReference> getStops() {
        return stops;
    }

    public void decrementCapacity(int passengers) {
        this.capacity -= passengers;
    }
}

class Stop {
    private GeoPoint coords;

    public Stop() {

    }

    public GeoPoint getCoords() {
        return coords;
    }
}
