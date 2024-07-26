package quizMaster.quizMaster.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import quizMaster.quizMaster.business.abstracts.AdminUserService;
import quizMaster.quizMaster.entities.concretes.AdminUser;

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
    public ResponseEntity<?> create(@Valid @RequestBody AdminUser user){
        return ResponseEntity.ok(this.adminUserService.createAdmin(user));
    }
    @PostMapping(value="/logIn")
    public ResponseEntity<?> logIn(@Valid @RequestBody Map<String, String> userCredentials){
        String email = userCredentials.get("email");
        String password = userCredentials.get("password");
        return ResponseEntity.ok(this.adminUserService.logInAdmin(email,password));
    }
}
