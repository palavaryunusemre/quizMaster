package quizMaster.quizMaster.business.abstracts;

import org.springframework.security.oauth2.core.user.OAuth2User;
import quizMaster.quizMaster.core.utilities.results.DataResult;

public interface FacebookUserService {
    DataResult<String> processOAuthPostLogin(OAuth2User oAuth2User);

}
