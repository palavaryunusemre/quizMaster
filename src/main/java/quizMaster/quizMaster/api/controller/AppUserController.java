package quizMaster.quizMaster.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import quizMaster.quizMaster.business.abstracts.AppUserService;
import quizMaster.quizMaster.entities.concretes.AppUser;

import java.util.Map;

@RestController
@RequestMapping(value="/api/user")
@CrossOrigin
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value="/create")
    public ResponseEntity<?> create(@Valid @RequestBody AppUser user){
        return ResponseEntity.ok(appUserService.createUser(user));
    }
    @PostMapping(value="/logIn")
    public ResponseEntity<?> logIn(@Valid @RequestBody Map<String, String> userCredentials){
        String email = userCredentials.get("email");
        String password = userCredentials.get("password");
        return ResponseEntity.ok(appUserService.logInUser(email,password));
    }

}
