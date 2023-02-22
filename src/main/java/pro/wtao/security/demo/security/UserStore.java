package pro.wtao.security.demo.security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.RequestMethod;
import pro.wtao.framework.security.model.LoginUser;
import pro.wtao.framework.security.model.UriGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <pre>
 * <b></b>
 * <b>Description:</b>
 * <b>Copyright:</b> Copyright 2022 360humi. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   		Date                    Author               	 Detail
 *   ----------------------------------------------------------------------
 *   1.0   2023/2/22 15:11    Wangtao     new file.
 * </pre>
 *
 * @author Wangtao
 * @since 2023/2/22
 */
public class UserStore {

    public static final List<DemoUser> demoUsers = new ArrayList<>();
    private static final UriGrantedAuthority query = new UriGrantedAuthority(RequestMethod.GET, "/demo/query", "demo");
    private static final UriGrantedAuthority delete = new UriGrantedAuthority(RequestMethod.GET, "/demo/delete", "demo");
    private static final UriGrantedAuthority save = new UriGrantedAuthority(RequestMethod.GET, "/demo/save", "demo");

    static {
        demoUsers.add(new DemoUser("1", "u1", "p1", "m1", "e1", true, Sets.newHashSet(query)));
        demoUsers.add(new DemoUser("2", "u2", "p2", "m2", "e2", true, Sets.newHashSet(query, save)));
    }


    @AllArgsConstructor
    @Data
    public static class DemoUser {
        private String userid;
        private String username;
        private String password;
        private String phone;
        private String email;
        private Boolean enabled;
        private Collection<UriGrantedAuthority> authorities;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class LoginUserExt extends LoginUser {
        private String extInfo;
    }
}
