package quizMaster.quizMaster.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import quizMaster.quizMaster.business.abstracts.AdminUserService;
import quizMaster.quizMaster.entities.concretes.AdminUser;
import quizMaster.quizMaster.entities.dtos.Request.AdminUserCreateRequestDto;
import quizMaster.quizMaster.entities.dtos.Request.AppSignInRequestDto;

import java.util.Map;

@RestController
@RequestMapping(value="/api/adminUser")
@CrossOrigin
@RequiredArgsConstructor
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value="/admin/create")
    public ResponseEntity<?> create(@Valid @RequestBody AdminUserCreateRequestDto adminUserCreateRequestDto){
        return ResponseEntity.ok(this.adminUserService.createAdmin(adminUserCreateRequestDto));
    }
    @PostMapping(value="/logIn")
    public ResponseEntity<?> logIn(@Valid @RequestBody AppSignInRequestDto appSignInRequestDto){
        String email = appSignInRequestDto.getEmail();
        String password = appSignInRequestDto.getPassword();
        return ResponseEntity.ok(this.adminUserService.logInAdmin(email,password));
    }
}
