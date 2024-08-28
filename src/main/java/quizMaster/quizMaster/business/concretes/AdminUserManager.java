package quizMaster.quizMaster.business.concretes;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import quizMaster.quizMaster.business.abstracts.AdminUserService;
import quizMaster.quizMaster.config.PasswordEncoderConfig;
import quizMaster.quizMaster.core.dataAccess.UserDao;
import quizMaster.quizMaster.core.enums.Roles;
import quizMaster.quizMaster.core.utilities.results.DataResult;
import quizMaster.quizMaster.core.utilities.results.Result;
import quizMaster.quizMaster.core.utilities.services.JwtService;
import quizMaster.quizMaster.dataAccess.abstracts.AdminUserDao;
import quizMaster.quizMaster.entities.concretes.AdminUser;
import quizMaster.quizMaster.entities.dtos.Request.AdminUserCreateRequestDto;
import quizMaster.quizMaster.entities.dtos.Response.UserResponseDto;

@Service
@RequiredArgsConstructor
public class AdminUserManager implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private JwtService jwtService;
    private PasswordEncoderConfig passwordEncoderConfig;
    @Override
    public Result createAdmin(@Valid @RequestBody AdminUserCreateRequestDto user) {
        AdminUser createUser = new AdminUser();
        user.setPassword(passwordEncoderConfig.passwordEncoder().encode(user.getPassword()));
        createUser.setUserName(user.getUserName());
        createUser.setEmail(user.getEmail());
        createUser.setPassword(user.getPassword());
        createUser.setRole(Roles.ADMIN);
        this.adminUserDao.save(createUser);
        return new Result(true,200);
    }


    @Override
    public DataResult<AdminUser> findByEmail(String email) {
        return null;
    }

    @Override
    public DataResult<String> logInAdmin(String email, String password) {
        return userDao.findByEmail(email)
                .filter(user -> user.getRole() == Roles.ADMIN)
                .filter(user -> passwordEncoderConfig.passwordEncoder().matches(password, user.getPassword()))
                .map(user -> {
                    UserResponseDto userDto = new UserResponseDto();
                    userDto.setId(user.getId());
                    userDto.setName(user.getUsername());
                    userDto.setEmail(email);
                    userDto.setRole(user.getRole().toString());
                    String token = jwtService.generateToken(userDto);
                    return new DataResult<>(token, true, 200);
                })
                .orElse(new DataResult<>(null, false, 5000));

    }

}
