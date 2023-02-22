package pro.wtao.security.demo.security;

import lombok.extern.slf4j.Slf4j;
import pro.wtao.framework.security.context.UserVariablesContext;
import pro.wtao.framework.security.model.UserVariable;

/**
 * <pre>
 * <b>Redis缓存用户变量</b>
 * <b>Description:</b>
 * <b>Copyright:</b> Copyright 2022 360humi. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   		Date                    Author               	 Detail
 *   ----------------------------------------------------------------------
 *   1.0   2023/2/22 14:21    Wangtao     new file.
 * </pre>
 *
 * @author Wangtao
 * @since 2023/2/22
 */
@Slf4j
public class DemoUserVariableContext implements UserVariablesContext {


    @Override
    public UserVariable get(String username) {
        UserStore.DemoUser demoUser = UserStore.demoUsers.stream().filter(u -> username.equals(u.getUsername())).findAny().orElse(null);

        return new UserVariable(demoUser.getPhone(), demoUser.getEmail(), demoUser.getAuthorities());
    }

}
