package com.spotifyalarm.alarm_manager.service.playlist;


import com.spotifyalarm.alarm_manager.dto.PlaylistDto;

import java.util.List;
import java.util.Optional;

public interface IPlaylistService {
    Optional<PlaylistDto> getPlaylistById(String id);
    List<PlaylistDto> getAllPlaylists();
    PlaylistDto createPlaylist(PlaylistDto playlistDto);
    void deletePlaylist(String id);


}
