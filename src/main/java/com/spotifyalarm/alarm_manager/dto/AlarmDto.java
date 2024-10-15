package com.spotifyalarm.alarm_manager.dto;




import java.sql.Time;



public class AlarmDto {

    private long id;
    private String name;
    private Time time;
    private String playlistId;

    public AlarmDto() {
    }

    public AlarmDto(long id, String name, Time time, String playlistId) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.playlistId = playlistId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Time getTime() {
        return time;
    }

    public String getPlaylistId() {
        return playlistId;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }








}



