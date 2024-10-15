package com.spotifyalarm.alarm_manager.service.alarm;

import com.spotifyalarm.alarm_manager.dto.AlarmDto;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface IAlarmService {

    Optional<AlarmDto> getAlarmByTime(Time time);   // Fetch an alarm by its time.

    List<AlarmDto> getAllAlarms();                  // Fetch all alarms.

    AlarmDto createAlarm(AlarmDto alarmDto);        // Create a new alarm.

    Optional<AlarmDto> updateAlarm(Long id, AlarmDto alarmDto); // Update an existing alarm by ID.

    void deleteAlarm(Long id);                      // Delete an alarm by ID.

    void deleteAlarmByTime(Time time);              // Delete an alarm by time.
}
