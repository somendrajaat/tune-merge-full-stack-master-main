package com.tunemerge.tunemerge.Controller;

import com.tunemerge.tunemerge.Model.Album.AlbumResponse;
import com.tunemerge.tunemerge.Model.SpotifyModel.PlaylistResponse;
import com.tunemerge.tunemerge.Model.SpotifyModel.SinglePlaylist;
import com.tunemerge.tunemerge.Model.accessToken;
import com.tunemerge.tunemerge.Model.userProfile.UserProfile;
import com.tunemerge.tunemerge.Service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpotifyController {
    @Autowired
    SpotifyService spotifyService;

    @GetMapping("/getuser")
    public UserProfile getUser() {
        accessToken token = spotifyService.getAccessToken();
        if (token == null || token.getAccess_token() == null) {
            return null;
        }
        return spotifyService.getProfile(token.getAccess_token());
    }


    @GetMapping("/getplaylists")
    public PlaylistResponse getPlaylists() {
        accessToken token = spotifyService.getAccessToken();
        if (token == null || token.getAccess_token() == null) {
            return null;
        }
        return spotifyService.getUserPlaylists(token.getAccess_token());
    }


    @GetMapping("/getplaylist")
    public SinglePlaylist getPlaylist(@RequestParam String id) {


        accessToken token = spotifyService.getAccessToken();
        if (token == null || token.getAccess_token() == null) {
            return null;
        }
        return spotifyService.getPlaylistItems(token.getAccess_token(),id);
    }

    @GetMapping("/getalbum")
    public AlbumResponse getAlbum(@RequestParam String id) {
        accessToken token = spotifyService.getAccessToken();
        if (token == null || token.getAccess_token() == null) {
            return null;
        }
        return spotifyService.getAlbum(token.getAccess_token(), id);
    }



}
