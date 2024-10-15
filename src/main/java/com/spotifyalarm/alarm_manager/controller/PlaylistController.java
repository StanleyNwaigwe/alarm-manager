package com.spotifyalarm.alarm_manager.controller;

import com.spotifyalarm.alarm_manager.dto.PlaylistDto;
import com.spotifyalarm.alarm_manager.service.playlist.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final IPlaylistService playlistService;

    @Autowired
    public PlaylistController(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDto> getPlaylistById(@PathVariable String id) {
        Optional<PlaylistDto> playlist = playlistService.getPlaylistById(id);
        return playlist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<PlaylistDto> getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    @PostMapping
    public ResponseEntity<PlaylistDto> createPlaylist(@RequestBody PlaylistDto playlistDto) {
        PlaylistDto createdPlaylist = playlistService.createPlaylist(playlistDto);
        return ResponseEntity.ok(createdPlaylist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable String id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }
}
