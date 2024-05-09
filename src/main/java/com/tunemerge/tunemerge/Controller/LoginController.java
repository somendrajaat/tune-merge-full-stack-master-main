package com.tunemerge.tunemerge.Controller;

import com.tunemerge.tunemerge.Model.accessToken;
import com.tunemerge.tunemerge.Service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.tunemerge.tunemerge.Service.OAuthUtil;

@RestController

public class LoginController {

    @Autowired
    OAuthUtil oauthUtil;
    @Autowired
    SpotifyService spotifyService;

    @GetMapping("/loginSpotify")
    public RedirectView login() {
        String authURL = oauthUtil.getAuthURL();
        System.out.println("hello");
        return new RedirectView(authURL);
    }

    // ---------------------------------------somendra--------------------------------------------------------------
    @PostMapping("/tune_merge")
    // @ResponseBody
    public accessToken token(@RequestParam("code") String code) {

        // yeh post request karega spotify ki web api ko udher se access token aayega jo
        // database me jayega sidha

        System.out.println("hello");
        return oauthUtil.getAccessToken(code);
    }

    @GetMapping("/getuser")
    public String getUser() {
        System.out.println("hello");
        accessToken token = spotifyService.getAccessToken();
        if (token == null || token.getAccess_token() == null) {
            return "No access token found";
        }
        // return "hello";
        // get profile ko access token chahiye jo ki hum line number 48 ki through le
        // aayenge
        return spotifyService.getProfile(token.getAccess_token());
    }

    @GetMapping("/getplaylists")
    public String getPlaylists() {
        System.out.println("hello");
        accessToken token = spotifyService.getAccessToken();
        if (token == null || token.getAccess_token() == null) {
            return "No access token found";
        }
        return spotifyService.getUserPlaylists(token.getAccess_token());
    }


    @GetMapping("/getplaylist")
    public String getPlaylist() {
        System.out.println("hello");
        accessToken token = spotifyService.getAccessToken();
        if (token == null || token.getAccess_token() == null) {
            return "No access token found";
        }
        return spotifyService.getPlaylistItems(token.getAccess_token(),"4gV9FZJ5s8oBzEwMAuLhuE");
    }






}