package quizMaster.quizMaster.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import quizMaster.quizMaster.business.abstracts.FacebookUserService;
import quizMaster.quizMaster.business.abstracts.GoogleUserService;
import quizMaster.quizMaster.core.utilities.results.DataResult;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import quizMaster.quizMaster.entities.dtos.FacebookSignInRequestDto;
import quizMaster.quizMaster.entities.dtos.GoogleSignInRequestDto;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/oauth")
@RequiredArgsConstructor
@CrossOrigin
public class OAuthController {
    private final GoogleUserService googleUserService;
    private final FacebookUserService facebookUserService;
    @Value("${spring.security.oauth2.client.registration.google.client-id.web}")
    private String webClientId;
    @Value("${spring.security.oauth2.client.registration.google.client-id.android}")
    private String androidClientId;
    @Value("${spring.security.oauth2.client.registration.google.client-id.ios}")
    private String iosClientId;
    @Value("${spring.security.oauth2.client.registration.facebook.client-id}")
    private String facebookClientId;
    @PostMapping("/google-signin")
    public ResponseEntity<?> handleGoogleSignIn(@RequestBody GoogleSignInRequestDto request) throws GeneralSecurityException, IOException {

        String clientId = getClientId(request.getPlatform());
        if (clientId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unsupported platform");
        }
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList(clientId))
                .build();
        GoogleIdToken idToken = verifier.verify(request.getIdToken());
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String userId = payload.getSubject();
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("sub", userId);
            attributes.put("email", email);
            attributes.put("name", name);
            attributes.put("picture", pictureUrl);
            attributes.put("platform", request.getPlatform());
            OAuth2User oAuth2User = new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                    attributes,
                    "sub"
            );
            DataResult<String> result = googleUserService.processOAuthPostLogin(oAuth2User);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid ID token");
        }
    }
    @PostMapping("/facebook-signin")
    public ResponseEntity<?> handleFacebookSignIn(@RequestBody FacebookSignInRequestDto request) {

        String accessToken = request.getIdToken();
        String fields = "id,name,email,picture";
        String graphApiUrl = String.format("https://graph.facebook.com/me?fields=%s&access_token=%s", fields, accessToken);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(graphApiUrl, Map.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> userData = response.getBody();
            Map<String, Object> attributes = extractFacebookUserAttributes(userData);
            OAuth2User oAuth2User = new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                    attributes,
                    "sub"
            );
            DataResult<String> result = facebookUserService.processOAuthPostLogin(oAuth2User);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid access token");
        }
    }
    private Map<String, Object> extractFacebookUserAttributes(Map<String, Object> userData) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("sub", userData.get("id"));
        attributes.put("name", userData.get("name"));
        attributes.put("email", userData.get("email"));
        Map<String, Object> picture = (Map<String, Object>) userData.get("picture");
        if (picture != null && picture.containsKey("data")) {
            Map<String, Object> pictureData = (Map<String, Object>) picture.get("data");
            attributes.put("picture", pictureData.get("url"));
        }
        return attributes;
    }
    private String getClientId(String platform) {
        switch (platform) {
            case "web":
                return webClientId;
            case "android":
                return androidClientId;
            case "ios":
                return iosClientId;
            default:
                return null;
        }
    }
}
