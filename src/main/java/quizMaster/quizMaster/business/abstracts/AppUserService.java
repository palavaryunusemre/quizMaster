package quizMaster.quizMaster.business.abstracts;

import quizMaster.quizMaster.core.utilities.results.DataResult;
import quizMaster.quizMaster.core.utilities.results.Result;
import quizMaster.quizMaster.entities.concretes.AppUser;
import quizMaster.quizMaster.entities.dtos.Request.AppCreateUserRequestDto;

public interface AppUserService {
    Result createUser(AppCreateUserRequestDto user);
    DataResult<String> logInUser(String email, String password);
}
