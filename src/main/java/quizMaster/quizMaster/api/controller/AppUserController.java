package quizMaster.quizMaster.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import quizMaster.quizMaster.business.abstracts.AppUserService;
import quizMaster.quizMaster.entities.concretes.AppUser;
import quizMaster.quizMaster.entities.dtos.Request.AppCreateUserRequestDto;
import quizMaster.quizMaster.entities.dtos.Request.AppSignInRequestDto;

import java.util.Map;

@RestController
@RequestMapping(value="/api/user")
@CrossOrigin
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value="/create")
    public ResponseEntity<?> create(@Valid @RequestBody AppCreateUserRequestDto appCreateUserRequestDto){
        return ResponseEntity.ok(appUserService.createUser(appCreateUserRequestDto));
    }
    @PostMapping(value="/logIn")
    public ResponseEntity<?> logIn(@Valid @RequestBody AppSignInRequestDto appSignInRequestDto){
        String email = appSignInRequestDto.getEmail();
        String password = appSignInRequestDto.getPassword();
        return ResponseEntity.ok(appUserService.logInUser(email,password));
    }

}
