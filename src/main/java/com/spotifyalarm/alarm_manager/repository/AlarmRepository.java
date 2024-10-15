package com.spotifyalarm.alarm_manager.repository;
import com.spotifyalarm.alarm_manager.model.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Optional;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    Optional<Alarm> findByTime(Time time);
}


