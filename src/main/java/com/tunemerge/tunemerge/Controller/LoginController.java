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


    @GetMapping("/loginSpotify")
    public RedirectView login() {

        String authURL = oauthUtil.getAuthURL();
        return new RedirectView(authURL);
    }

    // ---------------------------------------somendra--------------------------------------------------------------
    @PostMapping("/tune_merge")
    public accessToken token(@RequestParam("code") String code) {

        // yeh post request karega spotify ki web api ko udher se access token aayega jo
        // database me jayega sidha

        return oauthUtil.getAccessToken(code);
    }







}