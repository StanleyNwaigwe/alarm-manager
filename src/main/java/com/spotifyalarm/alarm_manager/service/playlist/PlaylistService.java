package com.spotifyalarm.alarm_manager.service.playlist;

import com.spotifyalarm.alarm_manager.dto.AccessTokenResponse;
import com.spotifyalarm.alarm_manager.dto.PlaylistDto;
import com.spotifyalarm.alarm_manager.model.Playlist;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService implements IPlaylistService {

    @Value("${spotify.api.url}")
    private String spotifyApiUrl;

    @Value("${spotify.client.id}")
    private String clientId;

    @Value("${spotify.client.secret}")
    private String clientSecret;

    private final RestTemplate restTemplate;


    public PlaylistService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }





    @Override
    public Optional<PlaylistDto> getPlaylistById(String playlistId) {
        String accessToken = authenticate();
        if (accessToken == null) {
            return Optional.empty();
        }
        String url = spotifyApiUrl + "/playlists/" + playlistId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Playlist> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, Playlist.class
        );
        Playlist playlist = response.getBody();
        return Optional.ofNullable(playlist != null ? convertToDto(playlist) : null);
    }

    @Override
    public List<PlaylistDto> getAllPlaylists() {

        List<PlaylistDto> playlists = new ArrayList<>();

        return playlists;
    }


    @Override
    public PlaylistDto createPlaylist(PlaylistDto playlistDto) {

        return null;
    }


    @Override
    public void deletePlaylist(String playlistId) {

    }



    public String authenticate() {
        String tokenUrl = "https://accounts.spotify.com/api/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        String body = "grant_type=client_credentials";

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<AccessTokenResponse> response = restTemplate.exchange(
                tokenUrl, HttpMethod.POST, requestEntity, AccessTokenResponse.class);

        if (response.getBody() != null) {
            return response.getBody().getAccessToken();
        }
        return null;
    }


    private PlaylistDto convertToDto(Playlist playlist) {
        return new PlaylistDto(
                playlist.getSpotifyId(),
                playlist.getName(),
                playlist.getDescription(),
                playlist.getImageUrl(),
                playlist.getSpotifyUrl());
    }
}

