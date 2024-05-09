/**
    * Generates the authorization URL for Spotify OAuth.
    * 
    * @return The authorization URL.
    */
package com.tunemerge.tunemerge.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.tunemerge.tunemerge.Model.accessToken;
import com.tunemerge.tunemerge.Repository.AccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Utility class for handling OAuth authentication with Spotify.
 */
@Service
public class OAuthUtil {

    private static final String CLIENT_ID = "";
    private static final String CLIENT_SECRET = "";
    private static final String REDIRECT_URI = "http://localhost:8080/tune_merge";

    /**
     * Generates the authorization URL for Spotify OAuth authentication.
     * 
     * @return The authorization URL.
     */
    public static String getAuthURL() {
        String scope = "playlist-read-public playlist-read-private";
        String authURL = "https://accounts.spotify.com/authorize?"
                + "client_id=" + CLIENT_ID
                + "&response_type=code"
                + "&redirect_uri=" + REDIRECT_URI;

        return authURL;
    }

    @Autowired
    accessToken at;
    @Autowired
    AccessTokenRepository accessTokenRepository;

    /**
     * Retrieves the access token from Spotify using the provided authorization
     * code.
     *
     * @param code The authorization code.
     * @return The access token.
     */

    // ------------------------------------------somendra---------------------------------------------------------------------------
    public accessToken getAccessToken(String code) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(CLIENT_ID, CLIENT_SECRET);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("redirect_uri", REDIRECT_URI);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<accessToken> response = restTemplate.postForEntity("https://accounts.spotify.com/api/token",
                request, accessToken.class);
        accessToken token = response.getBody();
        assert token != null;
        accessTokenRepository.save(token);

        return token;
    }
}