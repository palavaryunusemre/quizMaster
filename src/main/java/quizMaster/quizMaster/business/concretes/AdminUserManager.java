package quizMaster.quizMaster.business.concretes;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import quizMaster.quizMaster.business.abstracts.AdminUserService;
import quizMaster.quizMaster.core.enums.Roles;
import quizMaster.quizMaster.core.utilities.results.DataResult;
import quizMaster.quizMaster.core.utilities.results.Result;
import quizMaster.quizMaster.core.utilities.services.JwtService;
import quizMaster.quizMaster.dataAccess.abstracts.AdminUserDao;
import quizMaster.quizMaster.entities.concretes.AdminUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import quizMaster.quizMaster.entities.dtos.UserResponseDto;

@Service
@RequiredArgsConstructor
public class AdminUserManager implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;
    @Autowired
    private JwtService jwtService;
    @Override
    public Result createAdmin(@Valid @RequestBody AdminUser user) {
            user.setPassword(this.passwordEncoder().encode(user.getPassword()));
            user.setRole(Roles.ADMIN);
            this.adminUserDao.save(user);
            return new Result(true,200);
    }


    @Override
    public DataResult<AdminUser> findByEmail(String email) {
        return null;
    }

    @Override
    public DataResult<String> logInAdmin(String email, String password) {
        AdminUser user = this.adminUserDao.findByEmail(email);
        if (user != null && this.passwordEncoder().matches(password, user.getPassword())) {
            UserResponseDto userDto = new UserResponseDto();
            userDto.setId(user.getId());
            userDto.setName(user.getUserName());
            userDto.setEmail(email);
            userDto.setRole(user.getRole().toString());
            var token=jwtService.generateToken(userDto);
            return new DataResult<String>(token, true,200);
        } else {
            return new DataResult<String>(null,false,5000);
        }

    }

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
