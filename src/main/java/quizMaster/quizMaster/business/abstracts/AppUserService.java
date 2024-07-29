package quizMaster.quizMaster.business.abstracts;

import quizMaster.quizMaster.core.utilities.results.DataResult;
import quizMaster.quizMaster.core.utilities.results.Result;
import quizMaster.quizMaster.entities.concretes.AppUser;

public interface AppUserService {
    Result createUser(AppUser user);
    DataResult<String> logInUser(String email, String password);
}
