package com.spotifyalarm.alarm_manager.repository;

import com.spotifyalarm.alarm_manager.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findBySpotifyId(String spotifyId);
    Optional<Playlist> findByName(String name);
}
