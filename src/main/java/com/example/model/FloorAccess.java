package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.Date;

@JsonPropertyOrder(value = {"person_id", "datetime", "floor_level","building"})
public class FloorAccess implements Serializable {
    @JsonProperty
    private String person_id;
    //@JsonProperty
    //private String datetime;
    @JsonProperty
    private int floor_level;
    @JsonProperty
    private String building;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm")
    private Date datetime;

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        //System.out.println(datetime.toString());
        this.datetime = datetime;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    /*public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }*/

    public int getFloor_level() {
        return floor_level;
    }

    public void setFloor_level(int floor_level) {
        this.floor_level = floor_level;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}
