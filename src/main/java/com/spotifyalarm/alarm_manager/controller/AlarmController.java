package com.spotifyalarm.alarm_manager.controller;

import com.spotifyalarm.alarm_manager.dto.AlarmDto;
import com.spotifyalarm.alarm_manager.service.alarm.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Optional;

@RestController
@RequestMapping("/api/alarms")
public class AlarmController {
    private final AlarmService alarmService;

    @Autowired
    public AlarmController(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @GetMapping("/time/{time}")
    public ResponseEntity<AlarmDto> getAlarmByTime(@PathVariable("time") Time time) {
        Optional<AlarmDto> alarm = alarmService.getAlarmByTime(time);
        return alarm.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAlarm(@RequestBody AlarmDto alarmDto) {
        try {
            AlarmDto createdAlarm = alarmService.createAlarm(alarmDto);
            return new ResponseEntity<>(createdAlarm, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AlarmDto> updateAlarm(@PathVariable("id") Long id, @RequestBody AlarmDto alarmDto) {
        Optional<AlarmDto> updatedAlarm = alarmService.updateAlarm(id, alarmDto);
        return updatedAlarm.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
