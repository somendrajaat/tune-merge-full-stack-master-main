package com.tunemerge.tunemerge.Service;

import com.tunemerge.tunemerge.Model.accessToken;
import com.tunemerge.tunemerge.Repository.AccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * This class represents a service for interacting with the Spotify API.
 */
@Service
public class SpotifyService {

    private static final String SPOTIFY_API_URL = "https://api.spotify.com/v1/me/playlists";
    @Autowired
    AccessTokenRepository accessTRepository;


    /**
     * Retrieves the user's playlists from Spotify using the provided access token.
     *
     * @param accessToken the access token for authenticating the request
     * @return a string representation of the user's playlists
     */
    public String getUserPlaylists(String accessToken) {
        /**
         * A synchronous client to perform HTTP requests, exposing a simple, template method API over underlying HTTP client libraries such as the JDK {@link HttpURLConnection}, Apache HttpComponents, and others.
         * This class is designed on the same principles as the {@link org.springframework.web.client.RestTemplate}, providing a higher-level, more convenient API for making HTTP requests.
         * It simplifies communication with HTTP servers and enforces RESTful principles.
         */
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(SPOTIFY_API_URL, HttpMethod.GET, entity, String.class);
        if(response.getStatusCode()==HttpStatus.OK){
            return response.getBody();
        }
        else{
            response.getStatusCode();
            return "Error";
        }
    }

    //----------------------------------------------------------somendra---------------------------------------------------------------
    private static final String SPOTIFY_PROFILE_URL = "https://api.spotify.com/v1/me";
    public String getProfile(String accessToken) {

        /*
            yeh wala code spotify ke documentation per tha udher se convert karke chipka diya h
            isse user ki profile mil jayegi

        * */
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(SPOTIFY_PROFILE_URL, HttpMethod.GET, entity, String.class);
        if(response.getStatusCode()==HttpStatus.OK){
            return response.getBody();
        }
        else{
            return "Error";
        }
    }
    public  accessToken getAccessToken() {
        // bhai yeh access token jo database me save hoga udher se uthayega
        return accessTRepository.findTopByOrderByIdDesc();

    }


}
