package quizMaster.quizMaster.business.concretes;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import quizMaster.quizMaster.business.abstracts.AppleUserService;
import quizMaster.quizMaster.config.PasswordEncoderConfig;
import quizMaster.quizMaster.core.enums.Roles;
import quizMaster.quizMaster.core.utilities.results.DataResult;
import quizMaster.quizMaster.core.utilities.services.JwtService;
import quizMaster.quizMaster.dataAccess.abstracts.AppleUserDao;
import quizMaster.quizMaster.entities.concretes.AppleUser;
import quizMaster.quizMaster.entities.dtos.UserResponseDto;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppleUserManager implements AppleUserService {
    private final AppleUserDao appleUserDao;
    private final PasswordEncoderConfig passwordEncoderConfig;
    private final JwtService jwtService;

    @Override
    public DataResult<String> processOAuthPostLogin(OAuth2User oAuth2User) {
        String appleId = oAuth2User.getAttribute("sub");
        String password = UUID.randomUUID().toString().replace("-","");
        Optional<AppleUser> userOptional = appleUserDao.findByAppleId(appleId);
        AppleUser user = userOptional.orElseGet(() -> {
            AppleUser newUser = new AppleUser();
            newUser.setEmail(oAuth2User.getAttribute("email"));
            newUser.setName(oAuth2User.getAttribute("name"));
            newUser.setAppleId(appleId);
            newUser.setRole(Roles.USER);
            newUser.setPassword(passwordEncoderConfig.passwordEncoder().encode(password));
            return appleUserDao.save(newUser);
        });

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setRole(user.getRole().toString());

        String token = jwtService.generateToken(userResponseDto);
        return new DataResult<>(token, true, 200);
    }
}
