package dev.tinajero.models;

public class Course {
    private int id;
    private String courseName;
    private float worth;

    public Course(){}

    public Course(int id, String courseName, float worth) {
        this.id = id;
        this.courseName = courseName;
        this.worth = worth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public float getWorth() {
        return worth;
    }

    public void setWorth(float worth) {
        this.worth = worth;
    }
}
