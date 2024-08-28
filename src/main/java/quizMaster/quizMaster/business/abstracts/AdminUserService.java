package quizMaster.quizMaster.business.abstracts;

import quizMaster.quizMaster.core.utilities.results.DataResult;
import quizMaster.quizMaster.core.utilities.results.Result;
import quizMaster.quizMaster.entities.concretes.AdminUser;
import quizMaster.quizMaster.entities.dtos.Request.AdminUserCreateRequestDto;


public interface AdminUserService {
    Result createAdmin(AdminUserCreateRequestDto user);
    DataResult<AdminUser> findByEmail(String email);
    DataResult<String> logInAdmin(String email, String password);
}
