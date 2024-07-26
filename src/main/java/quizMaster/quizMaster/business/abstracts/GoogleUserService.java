package quizMaster.quizMaster.business.abstracts;

import org.springframework.security.oauth2.core.user.OAuth2User;
import quizMaster.quizMaster.core.utilities.results.DataResult;
import quizMaster.quizMaster.entities.concretes.GoogleUser;

public interface GoogleUserService {
    DataResult<String> processOAuthPostLogin(OAuth2User oAuth2User);
}
