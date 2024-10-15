package com.spotifyalarm.alarm_manager.service.alarm;

import com.spotifyalarm.alarm_manager.dto.AlarmDto;
import com.spotifyalarm.alarm_manager.model.Alarm;
import com.spotifyalarm.alarm_manager.repository.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlarmService implements IAlarmService {
    private final AlarmRepository alarmRepository;

    @Autowired
    public AlarmService(AlarmRepository alarmRepository) {
        this.alarmRepository = alarmRepository;
    }

    @Override
    public Optional<AlarmDto> getAlarmByTime(Time time) {
        return alarmRepository.findByTime(time)
                .map(this::convertToDto);
    }

    @Override
    public List<AlarmDto> getAllAlarms() {
        return alarmRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AlarmDto createAlarm(AlarmDto alarmDto) {
        Optional<Alarm> existingAlarm = alarmRepository.findByTime(alarmDto.getTime());

        if (existingAlarm.isPresent()) {
            throw new IllegalArgumentException("An alarm already exists for this time.");
        }

        Alarm alarm = convertToEntity(alarmDto);
        Alarm savedAlarm = alarmRepository.save(alarm);
        return convertToDto(savedAlarm);
    }

    @Override
    public Optional<AlarmDto> updateAlarm(Long id, AlarmDto alarmDto) {
        return alarmRepository.findById(id).map(alarm -> {
            alarm.setName(alarmDto.getName());
            alarm.setTime(alarmDto.getTime());
            alarm.setPlaylistId(alarmDto.getPlaylistId());
            Alarm updatedAlarm = alarmRepository.save(alarm);
            return convertToDto(updatedAlarm);
        });
    }

    @Override
    public void deleteAlarm(Long id) {
        alarmRepository.deleteById(id);
    }

    @Override
    public void deleteAlarmByTime(Time time) {
        alarmRepository.findByTime(time).ifPresent(alarmRepository::delete);
    }

    // Convert Alarm entity to AlarmDto
    private AlarmDto convertToDto(Alarm alarm) {
        return new AlarmDto(
                alarm.getId(),
                alarm.getName(),
                alarm.getTime(),
                alarm.getPlaylistId()
        );
    }

    // Convert AlarmDto to Alarm entity
    private Alarm convertToEntity(AlarmDto alarmDto) {
        Alarm alarm = new Alarm();
        alarm.setName(alarmDto.getName());
        alarm.setTime(alarmDto.getTime());
        alarm.setPlaylistId(alarmDto.getPlaylistId());
        return alarm;
    }
}
