package pro.wtao.security.demo.security;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.wtao.framework.security.component.AbstractUserDetailsService;
import pro.wtao.framework.security.model.LoginUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static pro.wtao.security.demo.security.UserStore.demoUsers;

/**
 * <pre>
 * <b></b>
 * <b>Description:</b>
 *
 * <b>Author:</b> wangtao@360humi.com
 * <b>Date:</b> 2022/9/14 17:24
 * <b>Copyright:</b> Copyright 2022 Wangtao. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   		Date                    Author               	 Detail
 *   ----------------------------------------------------------------------
 *   1.0   2022/9/14 17:24    wangtao@360humi.com     new file.
 * </pre>
 */
@Service
public class UserPasswordUserDetailService extends AbstractUserDetailsService<UsernamePasswordReqVo> {
    @Autowired
    private PasswordEncoder passwordEncoder;



    public UserPasswordUserDetailService() {
    }

    @Override
    public LoginUser loadUserDetails(Authentication authentication, UsernamePasswordReqVo parameter,
                                     HttpServletRequest request, HttpServletResponse response) {
        UserStore.DemoUser user = demoUsers.stream().filter(
                        u -> (u.getUserid().equals(parameter.getUsername())) || (u.getPhone().equals(parameter.getUsername()))
                                || (u.getUsername().equals(parameter.getUsername())) || (u.getEmail().equals(parameter.getUsername())))
                .findFirst().orElseThrow(() -> new UsernameNotFoundException("未找到用户"));

        UserStore.LoginUserExt loginUser = new UserStore.LoginUserExt();
        BeanUtils.copyProperties(user, loginUser);
        loginUser.setExtInfo(String.format("我是%s号用户", loginUser.getUserid()));
        return loginUser;
    }

    @Override
    public boolean verification(LoginUser loginUser, UsernamePasswordReqVo parameter, HttpServletRequest request,
                                HttpServletResponse response) {
        return passwordEncoder.matches(parameter.getPassword(), loginUser.getPassword());
    }

}
