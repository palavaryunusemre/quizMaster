package quizMaster.quizMaster.business.concretes;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import quizMaster.quizMaster.business.abstracts.GoogleUserService;
import quizMaster.quizMaster.config.PasswordEncoderConfig;
import quizMaster.quizMaster.core.enums.Roles;
import quizMaster.quizMaster.core.utilities.results.DataResult;
import quizMaster.quizMaster.core.utilities.services.JwtService;
import quizMaster.quizMaster.dataAccess.abstracts.GoogleUserDao;
import quizMaster.quizMaster.entities.concretes.GoogleUser;
import quizMaster.quizMaster.entities.dtos.Response.UserResponseDto;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoogleUserManager implements GoogleUserService {
    @Autowired
    private GoogleUserDao googleUserDao;
    @Autowired
    private JwtService jwtService;
    private PasswordEncoderConfig passwordEncoderConfig;
    @Transactional
    public DataResult<String> processOAuthPostLogin(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        String googleId = oAuth2User.getAttribute("sub");
        String password = UUID.randomUUID().toString().replace("-","");
        Optional<GoogleUser> userOptional = googleUserDao.findByGoogleId(googleId);
        GoogleUser user = userOptional.orElseGet(() -> {
            GoogleUser newUser = new GoogleUser();
            newUser.setEmail(email);
            newUser.setName(oAuth2User.getAttribute("name"));
            newUser.setPictureUrl(oAuth2User.getAttribute("picture"));
            newUser.setGoogleId(googleId);
            newUser.setPlatform(oAuth2User.getAttribute("platform"));
            newUser.setRole(Roles.USER);
            newUser.setPassword(passwordEncoderConfig.passwordEncoder().encode(password));
            return googleUserDao.save(newUser);
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
