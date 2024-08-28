package quizMaster.quizMaster.business.concretes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quizMaster.quizMaster.business.abstracts.AppUserService;
import quizMaster.quizMaster.config.PasswordEncoderConfig;
import quizMaster.quizMaster.core.dataAccess.UserDao;
import quizMaster.quizMaster.core.enums.Roles;
import quizMaster.quizMaster.core.utilities.results.DataResult;
import quizMaster.quizMaster.core.utilities.results.Result;
import quizMaster.quizMaster.core.utilities.services.JwtService;
import quizMaster.quizMaster.dataAccess.abstracts.AppUserDao;
import quizMaster.quizMaster.entities.concretes.AppUser;
import quizMaster.quizMaster.entities.dtos.Request.AppCreateUserRequestDto;
import quizMaster.quizMaster.entities.dtos.Response.UserResponseDto;

@Service
@RequiredArgsConstructor
public class AppUserManager implements AppUserService {
    private final AppUserDao appUserDao;
    private final JwtService jwtService;
    private final UserDao userDao;
    private final PasswordEncoderConfig passwordEncoderConfig;
    @Override
    public Result createUser(AppCreateUserRequestDto user) {
        user.setPassword(passwordEncoderConfig.passwordEncoder().encode(user.getPassword()));
        AppUser createUser = new AppUser();
        createUser.setEmail(user.getEmail());
        createUser.setPassword(user.getPassword());
        createUser.setBirthday(user.getBirthday());
        createUser.setNickname(user.getNickName());
        createUser.setUsername(user.getUserName());
        createUser.setRole(Roles.USER);
        appUserDao.save(createUser);
        return new Result(true,200);
    }

    @Override
    public DataResult<String> logInUser(String email, String password) {
        return userDao.findByEmail(email)
                .filter(user -> user.getRole() == Roles.USER)
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
